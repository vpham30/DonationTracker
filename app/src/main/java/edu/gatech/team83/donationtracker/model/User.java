package edu.gatech.team83.donationtracker.model;

public class User {

    private String name;
    private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {return name; }
    public String getPassword() {return password; }
    public void setName(String name) {this.name = name; }
    public void setPassword(String password) {this.password = password; }

    @Override
    public boolean equals(Object o){
        User u = (User) o;
        return u.getName().equals(name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
