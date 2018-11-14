package edu.gatech.team83.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class that contains an donation's name, timestamp, value,
 * short description, long description, and name
 */
public class Item implements Parcelable{

    private String name;
    private String time;
    private String value;
    private String shortDesc;
    private String longDesc;
    private String category;
    private final String locName;

    public static final String[] categories = {"Clothing", "Hat", "Kitchen", "Electronics",
            "Household", "Other"};


    /**
     * @return the name of the donation
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the donation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the time at which the donation was processed
     */
    public CharSequence getTime() {
        return time;
    }

    /**
     * @param time the time at which the donation is processed
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the monetary value of the donation
     */
    public CharSequence getValue() {
        return value;
    }

    /**
     * @param value the monetary value of the donation
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return a short description of the donation
     */
    public CharSequence getShortDesc() {
        return shortDesc;
    }

    /**
     * @param shortDesc a short description
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * @return a long description of the donation
     */
    public CharSequence getLongDesc() {
        return longDesc;
    }

    /**
     * @param longDesc a long description
     */
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public CharSequence getLocName() {
        return locName;
    }

    public Item(String name, String time, String value, String category, String locName) {
        this.name = name;
        this.time = time;
        this.value = value;
        this.shortDesc = "";
        this.longDesc = "";
        this.category = category;
        this.locName = locName;
    }

    public Item() {
        this("","","","", "");
    }

    public Item(Parcel in) {
        this.name = in.readString();
        this.time = in.readString();
        this.value = in.readString();
        this.shortDesc = in.readString();
        this.longDesc = in.readString();
        this.category = in.readString();
        this.locName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(time);
        dest.writeString(value);
        dest.writeString(shortDesc);
        dest.writeString(longDesc);
        dest.writeString(category);
        dest.writeString(locName);
    }

    public static final Parcelable.Creator<Item> CREATOR
            = new Parcelable.Creator<Item> (){

        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray (int size) {
            return new Item[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return (name + time).equals(item.name + item.time);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + time.hashCode();
    }
}
