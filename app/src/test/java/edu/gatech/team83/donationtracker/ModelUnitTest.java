package edu.gatech.team83.donationtracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

import static com.google.android.gms.common.api.CommonStatusCodes.TIMEOUT;

public class ModelUnitTest {

    private ArrayList<Item> emptyItemList;
    private ArrayList<Location> emptyLocationList;
    private List<Item> allItems;
    private ArrayList<Item> allItemInAFD;
    private List<Location> allLocation;
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
        model.loadDefault();
        allLocation = model.getLocations();
        allItems = model.getAllItems();
        allItemInAFD = new ArrayList<>();
        allItemInAFD.addAll(allLocation.get(0).getInventory());
        firstLocation = allLocation.get(0);
        List<Item> firstItemList = firstLocation.getInventory();
        validFirstItemList = new ArrayList<>();
        validFirstItemList.add(firstItemList.get(0));
        firstLocationList = new ArrayList<>();
        firstLocationList.add(firstLocation);
    }

    @Test(timeout = TIMEOUT)
    public void blankStringInputItem() {
        ArrayList<Item> list = (ArrayList<Item>) model.itemSearch(blankString, blankString, firstLocation);
        Assert.assertTrue("Did not give all Items in location", list.equals(allItemInAFD));
    }

    @Test(timeout = TIMEOUT)
    public void blankLocationInputItem() {
        ArrayList<Item> list = (ArrayList<Item>) model.itemSearch(blankString, blankString, blankLocation);
        Assert.assertTrue("Did not return blank list", list.equals(emptyItemList));
    }

    @Test(timeout = TIMEOUT)
    public void mismatchedInputItem() {
        ArrayList<Item> list =(ArrayList<Item>) model.itemSearch("loc21", "", firstLocation);
        Assert.assertTrue("Did not return blank list", list.equals(emptyItemList));
    }

    @Test(timeout = TIMEOUT)
    public void testValidItemSearch() {
        ArrayList<Item> list = (ArrayList<Item>)model.itemSearch("test1", "Hat", firstLocation);
        Assert.assertTrue("Did not return valid item list", list.equals(validFirstItemList));
    }

    @Test(timeout = TIMEOUT)
    public void testInvalidItemSearch() {
        ArrayList<Item> list = (ArrayList<Item>)model.itemSearch("test9", "Hat", firstLocation);
        Assert.assertTrue("Did not return valid item list", list.equals(emptyItemList));
    }

    @Test(timeout = TIMEOUT)
    public void testNullItemSearch() {
        ArrayList<Item> list = (ArrayList<Item>) model.itemSearch("", "", null);
        Assert.assertTrue("Did not return valid item list", list.equals(allItems));
    }

    @Test(timeout = TIMEOUT)
    public void testLocationSearch() {
        ArrayList<Location> list = model.locSearch("Loc1");
        Assert.assertTrue("Did not return valid item list" + list.size(), list.equals(firstLocationList));
    }

    @Test(timeout = TIMEOUT)
    public void testAllLocationSearch() {
        ArrayList<Location> list = model.locSearch("");
        Assert.assertTrue("Did not return valid item list", list.equals(allLocation));
    }

    @Test(timeout = TIMEOUT)
    public void testEmptyLocationSearch() {
        ArrayList<Location> list = model.locSearch("CATDOG");
        Assert.assertTrue("Did not return valid item list", list.equals(emptyLocationList));
    }
}