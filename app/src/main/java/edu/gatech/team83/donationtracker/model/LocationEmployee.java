package edu.gatech.team83.donationtracker.model;

public class LocationEmployee extends User {

    public LocationEmployee(String name, String password){
        super(name, password);
    }

    public static String getType() {
        return "Location Employee";
    }
}
