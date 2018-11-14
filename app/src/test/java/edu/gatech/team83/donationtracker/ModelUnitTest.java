package edu.gatech.team83.donationtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

import static com.google.android.gms.common.api.CommonStatusCodes.TIMEOUT;
import static org.junit.Assert.*;

public class ModelUnitTest {

    private ArrayList<Item> emptyItemList;
    private ArrayList<Location> emptyLocationList;
    private ArrayList<Item> allItems;
    private ArrayList<Item> allItemInAFD;
    private ArrayList<Location> allLocation;

    private String blankString;
    private Location blankLocation;
    private Location firstLocation;

    private Model model;

    @Before
    public void setUp() {
        emptyItemList = new ArrayList<>();
        emptyLocationList = new ArrayList<>();
        blankString = "";
        blankLocation = new Location();
        model = Model.getInstance();
        model.updateFromDatabase();
        allItems = model.getAllItems();
        allItemInAFD = new ArrayList<>();
        allItemInAFD.add(allItems.get(0));
        allLocation = model.getLocations();
        firstLocation = allLocation.get(0);
    }

    @Test(timeout = TIMEOUT)
    public void blankStringInputItem() {
        ArrayList<Item> list = model.itemSearch(blankString, blankString, firstLocation);
        assertTrue("Did not give all Items in location", list.equals(allItemInAFD));
    }

    @Test(timeout = TIMEOUT)
    public void blankLocationInputItem() {
        ArrayList<Item> list = model.itemSearch(blankString, blankString, blankLocation);
        assertTrue("Did not return blank list", list.equals(emptyItemList));
    }

    @Test(timeout = TIMEOUT)
    public void mismatchedInputItem() {
        ArrayList<Item> list = model.itemSearch("test1", "", firstLocation);
        assertTrue("Did not return blank list", list.equals(emptyItemList));
    }
}
