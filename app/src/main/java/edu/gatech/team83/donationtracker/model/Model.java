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

    public FirebaseUser getCurrentuser() {
        return currentuser;
    }


}
