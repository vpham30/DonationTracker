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
    private ArrayList<Item> firstItemList;
    private ArrayList<Item> validFirstItemList;
    private ArrayList<Location> firstLocationList;

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
        firstItemList = firstLocation.getInventory();
        validFirstItemList = new ArrayList<>();
        validFirstItemList.add(firstItemList.get(0));
        firstLocationList = new ArrayList<>();
        firstLocationList.add(firstLocation);
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

    @Test(timeout = TIMEOUT)
    public void testValidItemSearch() {
        ArrayList<Item> list = model.itemSearch("test1", "Hat", firstLocation);
        assertTrue("Did not return valid item list", list.equals(validFirstItemList));
    }

    @Test(timeout = TIMEOUT)
    public void testInvalidItemSearch() {
        ArrayList<Item> list = model.itemSearch("test9", "Hat", firstLocation);
        assertTrue("Did not return valid item list", list.equals(emptyItemList));
    }

    @Test(timeout = TIMEOUT)
    public void testNullItemSearch() {
        ArrayList<Item> list = model.itemSearch("", "", null);
        assertTrue("Did not return valid item list", list.equals(allItems));
    }

    @Test(timeout = TIMEOUT)
    public void testLocationSearch() {
        ArrayList<Location> list = model.locSearch("AFD Station 4#1");
        assertTrue("Did not return valid item list", list.equals(firstLocationList));
    }

    @Test(timeout = TIMEOUT)
    public void testAllLocationSearch() {
        ArrayList<Location> list = model.locSearch("");
        assertTrue("Did not return valid item list", list.equals(allLocation));
    }

    @Test(timeout = TIMEOUT)
    public void testEmptyLocationSearch() {
        ArrayList<Location> list = model.locSearch("CATDOG");
        assertTrue("Did not return valid item list", list.equals(emptyLocationList));
    }
}
