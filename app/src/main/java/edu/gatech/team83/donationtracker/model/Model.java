package edu.gatech.team83.donationtracker.model;

import android.support.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Singleton instance of Model which handles database
 */
public final class Model {

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ArrayList<Location> locations;
    private ArrayList<Item> allItems;
    private long count;
    private String usertype;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    /** Singleton instance */
    private static final Model _instance = new Model();

    /**
     * function to get an Instance of the model
     * @return an instance of the model
     */
    public static Model getInstance() { return _instance; }
    private Model() {
        locations = new ArrayList<>();
        locations.add(new Location());
        allItems = new ArrayList<>();
        allItems.add(new Item());
    }

    /**
     * gets the list of locations in the model
     * @return the list of locations
     */
    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }


    /**
     * updates the list of locations and items in the model from Firebase
     */
    public void updateFromDatabase() {
        db.collection("locations").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    locations = (ArrayList<Location>) Objects.requireNonNull(task.getResult()).toObjects(Location.class);
                }
            }
        });
        allItems = new ArrayList<>();
        for (Location loc : locations) {
            allItems.addAll(loc.getInventory());
        }
        db.collection("counters").document("loccount").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    count = Objects.requireNonNull(task.getResult()).getLong("num");
                }
            }
        });
    }

    /**
     * gets the data(type/locked/etc) for the current user and adds it to the model.
     * @param user the user gotten from mAuth.getCurrentUser()
     */
    public void setCurrentuser(FirebaseUser user) {
        db.collection("users").document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    usertype = Objects.requireNonNull(task.getResult()).getString("type");
                }
            }
        });
    }

    /**
     * adds the location to the database and sets the id  of the location to (count + 1)
     * and then updates the location list
     * @param loc the location you wish to add
     */
    public void addLocation(Location loc) {
        ++count;
        loc.setId(count);
        db.collection("locations").document(loc.getName() + "#" + count).set(loc);
        db.collection("counters").document("loccount").update("num", count)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    /**
     * Updates the info in the database for one location to match the data in a second location
     * and then updates the location list
     * @param toedit the location you wish to update in the database
     * @param loc the location which holds the data to update
     */
    public void editLocation(Location toedit, Location loc) {
        loc.setId(toedit.getId());
        db.collection("locations").document(toedit.getName() + "#" + toedit.getId()).delete();
        db.collection("locations").document(loc.getName() + "#" + toedit.getId()).set(loc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    /**
     * Adds an Item to a given Location in the database
     * and then updates the item list
     * @param loc the location you wish to add to
     * @param dono the item you wish to add
     */public void addDonation(Location loc, Item dono) {
        ArrayList<Item> inv = loc.getInventory();
        inv.add(dono);
        loc.setInventory(inv);
        db.collection("locations").document(loc.getName() + "#" + loc.getId()).set(loc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    /**
     * Updates the info in the database for one item in a given location to match a second
     * @param loc the location where the item is located
     * @param toedit the item you wish to update
     * @param newItem the item which holds the data to update
     */
    public void editDonation(Location loc, Item toedit, Item newItem) {
        ArrayList<Item> inv = loc.getInventory();
        inv.remove(toedit);
        inv.add(newItem);
        loc.setInventory(inv);
        db.collection("locations").document(loc.getName() + "#" + loc.getId()).set(loc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    /**
     * gets the users type as a string
     * @return the users type
     */
    public String getType() {
        return usertype;
    }

    /**
     * signs out the current user
     */
    public void signout() {
        mAuth.signOut();
    }


    /**
     * Item search method
     *
     * @param searchField the String typed into the search bar ("" if all items)
     * @param category the category you are searching for ("" if all categories)
     * @param loc the location you are searching in (null if all locations)
     * @return an arraylist containing all items matching the search query
     */
    public Iterable<Item> itemSearch(String searchField, String category, Location loc) {
        Iterable<Item> toSearch;
        Collection<Item> ret = new ArrayList<>();
        toSearch = (loc == null) ? allItems : loc.getInventory();
        for (Item i: toSearch) {
            if (i.getName().toLowerCase().contains(searchField.toLowerCase())
                    && i.getCategory().toLowerCase().contains(category.toLowerCase())) {
                ret.add(i);
            }
        }
        return ret;
    }

    /**
     * Location search method
     *
     * @param searchField the String typed into the search bar ("" if all items)
     * @return an arraylist containing all items matching the search query
     */
    public ArrayList<Location> locSearch(String searchField) {
        ArrayList<Location> ret = new ArrayList<>();
        for (Location l: locations) {
            if (l.getName().toLowerCase().contains(searchField.toLowerCase())) {
                ret.add(l);
            }
        }
        return ret;
    }

    /**
     * gets the list of all items in the model
     * @return the list of all items
     */
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(allItems);
    }

}
