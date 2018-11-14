package edu.gatech.team83.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * class that represents the User
 */
public class User implements Parcelable{
    private final String email;
    private final long locationId;
    private final String type;

    /**
     * constructor for the user
     * @param email the user's email
     * @param locationId the id of the location where the user works
     * @param type the user's account type
     */
    public User(String email, long locationId, String type) {
        this.email = email;
        this.locationId = locationId;
        this.type = type;
    }

    private String getEmail() {
        return email;
    }
    private User(Parcel in) {
        this.email = in.readString();
        this.locationId = in.readLong();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeLong(locationId);
        dest.writeString(type);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User> (){

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray (int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(Object o){
        if (getClass() != o.getClass()) {
            return false;
        }
        User u = (User) o;
        return email.equals(u.getEmail());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
