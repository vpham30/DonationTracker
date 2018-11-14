package edu.gatech.team83.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Location implements Parcelable {

    private long id;
    private String name;
    private String type;
    private String longitude;
    private String latitude;
    private String address;
    private String phonenumber;
    private List<Item> inventory;

    public Location(int id, String type, String name, String longitude, String latitude,
                    String address, String phonenumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phonenumber = phonenumber;
        inventory = new ArrayList<>();
    }

    public Location(){
        this(0, "","", "","", "", "");
    }


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CharSequence getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public CharSequence getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public List<Item> getInventory() { return Collections.unmodifiableList(inventory); }
    public void setInventory(List<Item> inventory) { this.inventory = new ArrayList<>(inventory); }


    public Location(Parcel in) {
        id = in.readLong();
        name = in.readString();
        type = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        address = in.readString();
        phonenumber = in.readString();
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
        dest.writeString(phonenumber);
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
        Location loc = (Location) o;
        return loc.id == id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }
}
