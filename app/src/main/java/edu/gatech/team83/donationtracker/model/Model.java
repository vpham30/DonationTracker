package edu.gatech.team83.donationtracker.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private Map<User,User> _users;

    private Model(){
        _users = new HashMap<>();
    }

    /**
     * adds a User to the Model
     *
     * @param u User you wish to add
     * @return False if username was already taken, True if user was added successfully
     */
    public boolean addUser(User u) {
        if (_users.containsKey(u)) {
            return false;
        } else {
            _users.put(u, u);
            return true;
        }
    }

    /**
     * Checks if a user exists with a username and password
     *
     * @param username username of user
     * @param password password of user
     * @return False if incorrect user/pass combo, true if correct
     */
    public boolean validateUser(String username, String password) {
        User u = new User(username, password);
        return _users.get(u).validate(u);
    }

}
