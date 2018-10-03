package edu.gatech.team83.donationtracker.model;

public class User {

    private String name;
    private String password;
    private AccountType type;

    public User(String name, String password, AccountType type){
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getName() {return name; }
    public String getPassword() {return password; }
    public AccountType getType() {return type; }
    public void setName(String name) {this.name = name; }
    public void setPassword(String password) {this.password = password; }
    public void setType(AccountType type) {this.type = type;}

    @Override
    public boolean equals(Object o){
        User u = (User) o;
        return u.getName().equals(name);
    }

    public boolean validate(User u){
        return u.getPassword().equals(password);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

}
