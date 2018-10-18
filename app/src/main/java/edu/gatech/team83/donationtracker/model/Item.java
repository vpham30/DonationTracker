package edu.gatech.team83.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

    private String name;
    private String time;
    private String value;
    private String shortDesc;
    private String longDesc;
    private String category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Item(String name, String time, String value, String shortDesc, String longDesc, String category) {
        this.name = name;
        this.time = time;
        this.value = value;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.category = category;
    }

    public Item() {
        this("","","","","","");
    }

    public Item(Parcel in) {
        this.name = in.readString();
        this.time = in.readString();
        this.value = in.readString();
        this.shortDesc = in.readString();
        this.longDesc = in.readString();
        this.category = in.readString();
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
    }

    public static final Parcelable.Creator<Item> CREATOR
            = new Parcelable.Creator<Item> (){

        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray (int size) {
            return new Item[size];
        }
    };
}
