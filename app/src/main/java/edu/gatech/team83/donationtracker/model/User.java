package edu.gatech.team83.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String email;
    private long locid;
    private String type;

    public User(String email, long locid, String type) {
        this.email = email;
        this.locid = locid;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLoc() {
        return locid;
    }

    public void setLoc(long locid) {
        this.locid = locid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User(Parcel in) {
        this.email = in.readString();
        this.locid = in.readLong();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeLong(locid);
        dest.writeString(type);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User> (){

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray (int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(Object o){
        User u = (User) o;
        return email.equals(u.getEmail());
    }
}
