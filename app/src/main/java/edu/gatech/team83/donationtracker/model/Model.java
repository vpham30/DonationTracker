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


public class Model {

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static ArrayList<Location> locations;
    private static FirebaseUser currentuser;
    private static long count;
    private static String usertype;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }
    private Model(){
        currentuser = null;
        locations = new ArrayList<>();
        locations.add(new Location());
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void updateFromDatabase() {
        db.collection("locations").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    locations = (ArrayList<Location>) task.getResult().toObjects(Location.class);
                }
            }
        });
        db.collection("counters").document("loccount").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    count = task.getResult().getLong("num");
                }
            }
        });
    }

    public void setCurrentuser(FirebaseUser user) {
        currentuser = user;
        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    usertype = task.getResult().getString("type");
                }
            }
        });
    }


    public long getCount() {
        return count;
    }

    public void addLocation(Location loc) {
        loc.setId(++count);
        db.collection("locations").document(loc.getName() + "#" + count).set(loc);
        db.collection("counters").document("loccount").update("num", count).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    public void editLocation(Location toedit, Location loc) {
        loc.setId(toedit.getId());
        db.collection("locations").document(toedit.getName() + "#" + toedit.getId()).delete();
        db.collection("locations").document(loc.getName() + "#" + toedit.getId()).set(loc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    public void addDonation(Location loc, Item dono) {
        ArrayList<Item> newinv = loc.getInventory();
        newinv.add(dono);
        db.collection("locations").document(loc.getName() + "#" + loc.getId()).update("inventory", newinv).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateFromDatabase();
            }
        });
    }

    public String getType() {
        return usertype;
    }

    public void signout() {
        mAuth.signOut();
    }

}
