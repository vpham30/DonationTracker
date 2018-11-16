package edu.gatech.team83.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a location where items can be donated.
 * Each location has an id, name, type, longitude, latitude, address,
 * phone number, and inventory.
 */

public class Location implements Parcelable {

    private long id;
    private String name;
    private String type;
    private String longitude;
    private String latitude;
    private String address;
    private String phoneNumber;
    private List<Item> inventory;

    /**
     * A constructor that creates a location
     * @param name name of the location
     * @param longitude coordinate of location
     * @param latitude coordinate of location
     * @param address location's address
     * @param phoneNumber location's retail number
     */
    public Location(String name, String longitude, String latitude,
                    String address, String phoneNumber) {
        this.id = -1;
        this.name = name;
        this.type = "";
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        inventory = new ArrayList<>();
    }

    /**
     * A default constructor that sets all variables to ""
     */
    public Location(){
        this("", "","", "", "");
    }

    /**
     * A getter for location ID
     * @return location's ID
     */
    public long getId() { return id; }

    /**
     *  A setter for location ID
     * @param id location's numerical ID
     */
    public void setId(long id) { this.id = id; }

    /**
     * A getter for location's name
     * @return the location's String name
     */
    public String getName() { return name; }

    /**
     * A setter for location's name
     * @param name the location's name
     */
    public void setName(String name) { this.name = name; }

    /**
     * A getter for the item's type in Inventory
     * @return type of item
     */
    public CharSequence getType() { return type; }

    /**
     * A setter for item's type in Inventory
     * @param type type of item
     */
    public void setType(String type) { this.type = type; }

    /**
     * A getter for the location's longitude
     * @return longitude of location
     */
    public String getLongitude() { return longitude; }

    /**
     * A setter for location's longitude
     * @param longitude longitude of location
     */
    public void setLongitude(String longitude) { this.longitude = longitude; }

    /**
     * A getter for location's latitude
     * @return latitude of location
     */
    public String getLatitude() { return latitude; }

    /**
     * A setter for location's latitude
     * @param latitude latitude of location
     */
    public void setLatitude(String latitude) { this.latitude = latitude; }

    /**
     * A getter for the location's address
     * @return location's address
     */
    public CharSequence getAddress() { return address; }

    /**
     * A setter for the location's address
     * @param address location's address
     */
    public void setAddress(String address) { this.address = address; }

    /*
     * A getter for location's phone number
     * @return location's phone number

    public String getPhoneNumber() { return phoneNumber; }

    /**
     * A setter for location's phone number
     * @param phoneNumber location's phone number
     *
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    */
    /**
     * A getter for the location's inventory
     * @return a list of the inventory
     */
    public List<Item> getInventory() { return Collections.unmodifiableList(inventory); }

    /**
     * A setter for location's inventory
     * @param inventory list of location's inventory
     */
    public void setInventory(List<Item> inventory) { this.inventory = new ArrayList<>(inventory); }

    /**
     * A constructor for Location
     * @param in a container for location's variables
     */
    public Location(Parcel in) {
        id = in.readLong();
        name = in.readString();
        type = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        inventory = in.readArrayList(Item.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(longitude);
        dest.writeString(latitude);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeList(inventory);
    }

    public static final Parcelable.Creator<Location> CREATOR
            = new Parcelable.Creator<Location> (){

        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray (int size) {
            return new Location[size];
        }
    };

    @Override
    public boolean equals(Object o){
        if (getClass() != o.getClass()) {
            return false;
        }
        Location loc = (Location) o;
        return loc.id == id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }
}
