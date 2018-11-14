package edu.gatech.team83.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;

import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Model;

public class WelcomeActivity extends AppCompatActivity {
    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }
        
    public void onLoginPressed(View view) {
        model.updateFromDatabase();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
        
    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    public void loadData(View view) {
    /**    Task<DocumentSnapshot> documentSnapshotTask = db.collection("counters")
                .document("loccount")
                .get().addOnCompleteListener(this, new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Reader in;
                            long num;
                            DocumentReference count = db.collection("counters")
                                    .document("loccount");
                            if (Objects.requireNonNull(task.getResult()).get("num") == null) {
                                num = 0;
                            } else {
                                num = (long) task.getResult().get("num");
                            }
                            try {
                                InputStream is = getAssets().open("LocationData.csv");
                                in = new InputStreamReader(is);
                                List<Item> inv;
                                Iterable<CSVRecord> locations
                                        = CSVFormat.EXCEL.withHeader().parse(in);
                                for (CSVRecord location : locations) {
                                    Location loc = new Location();
                                    inv = new ArrayList<>();
                                    ++num;
                                    loc.setId(num);
                                    loc.setName(location.get("Name"));
                                    loc.setType(location.get("Type"));
                                    loc.setLongitude(location.get("Longitude"));
                                    loc.setLatitude(location.get("Latitude"));
                                    loc.setAddress(location.get("Street Address"));
                                    loc.setPhonenumber(location.get("Phone"));
                                    inv.add(new Item("test1", "timestamp","value",
                                            "short description","long description",
                                            "category", loc.getName()));
                                    inv.add(new Item("test2", "timestamp","value",
                                            "short description","long description",
                                            "category", loc.getName()));
                                    inv.add(new Item("test3", "timestamp","value",
                                            "short description","long description",
                                            "category", loc.getName()));
                                    loc.setInventory(inv);
                                    db.collection("locations")
                                            .document(location.get("Name") + "#" + num).set(loc);
                                }
                                Map<String, Object> cdata = new HashMap<>();
                                cdata.put("num", num);
                                count.set(cdata);
                                Toast.makeText(WelcomeActivity.this, "success",
                                        Toast.LENGTH_SHORT).show();
                                model.updateFromDatabase();
                            } catch (FileNotFoundException e) {
                                Toast.makeText(WelcomeActivity.this, "file not found",
                                        Toast.LENGTH_SHORT).show();
                            } catch (IOException f) {
                                Toast.makeText(WelcomeActivity.this, "IO",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(WelcomeActivity.this, "dbase ERROR",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });
     */
        model.updateFromDatabase();
    }
}
