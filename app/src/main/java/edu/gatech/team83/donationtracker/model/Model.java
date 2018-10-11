package edu.gatech.team83.donationtracker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static ArrayList<Location> locations;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private Model(){
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

}
