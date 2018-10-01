package edu.gatech.team83.donationtracker.model;

public class Manager extends LocationEmployee {

    public Manager(String name, String password){
        super(name, password);
    }

    public static String getType() {
        return "Manager";
    }
}
