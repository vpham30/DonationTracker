package edu.gatech.team83.donationtracker.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.team83.donationtracker.R;

public class WelcomeActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        db = FirebaseFirestore.getInstance();
    }
        
    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
        
    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void loadData(View view) {
        db.collection("locations").document("count")
                .get().addOnCompleteListener(this, new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Reader in;
                    Map<String, Object> data;
                    long num;
                    DocumentReference count = db.collection("locations").document("count");
                    if (task.getResult().get("num") == null) {
                        num = 0;
                    } else {
                        num = (long) task.getResult().get("num");
                    }
                    try {
                        InputStream is = getAssets().open("LocationData.csv");
                        in = new InputStreamReader(is);
                        Iterable<CSVRecord> locations = CSVFormat.EXCEL.withHeader().parse(in);
                        for (CSVRecord location : locations) {
                            data = new HashMap<>();
                            data.put("id", ++num);
                            data.put("LocationName", location.get("Name"));
                            data.put("LocationType", location.get("Type"));
                            data.put("Longitude", location.get("Longitude"));
                            data.put("Latitude", location.get("Latitude"));
                            data.put("Address", location.get("Street Address"));
                            data.put("PhoneNumber", location.get("Phone"));
                            db.collection("locations").add(data);
                        }
                        Map<String, Object> cdata = new HashMap<>();
                        cdata.put("num", num);
                        db.collection("locations").document("count").set(cdata);
                        Toast.makeText(WelcomeActivity.this, "success", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(WelcomeActivity.this, "file not found", Toast.LENGTH_SHORT).show();
                    } catch (IOException f) {
                        Toast.makeText(WelcomeActivity.this, "IO", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WelcomeActivity.this, "dbase ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
