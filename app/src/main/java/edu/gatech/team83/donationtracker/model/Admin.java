package edu.gatech.team83.donationtracker.model;

public class Admin extends Manager {

    public Admin(String name, String password){
        super(name, password);
    }

    public static String getType() {
        return "Admin";
    }
}
