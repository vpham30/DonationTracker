package edu.gatech.team83.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class LocationDetailActivity extends AppCompatActivity {
    private Location location;
    private TextView locationName;
    private TextView locationType;
    private TextView longitude;
    private TextView latitude;
    private TextView address;
    private TextView phone;
    private Model model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);


        location = (Location) getIntent().getParcelableExtra("Location");
        model = Model.getInstance();


        locationName = findViewById(R.id.location_name);
        locationType = findViewById(R.id.location_type);
        longitude = findViewById(R.id.location_longitude);
        latitude = findViewById(R.id.location_latitude);
        address = findViewById(R.id.location_address);
        phone = findViewById(R.id.location_phone_number);

        locationName.setText(location.getName());
        locationType.setText(location.getType());
        longitude.setText(location.getLongitude());
        latitude.setText(location.getLatitude());
        address.setText(location.getAddress());
        phone.setText(location.getPhonenumber());
    }

    public void onBackPressed(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, LocationRecyclerActivity.class);
        startActivity(intent);
    }

    public void onEditLocationPressed(View v) {
        if (model.getType().equals("Admin")) {
            Context context = v.getContext();
            Intent intent = new Intent(context, LocationEditActivity.class);
            intent.putExtra("Location", location);
            startActivity(intent);
        } else {
            Snackbar failed = Snackbar.make(v, "You are not an admin", Snackbar.LENGTH_SHORT);
            failed.show();
        }
    }

    public void onViewInventoryPressed(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, InventoryRecyclerActivity.class);
        intent.putExtra("Location", location);
        startActivity(intent);
    }
}
