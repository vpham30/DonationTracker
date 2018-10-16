package edu.gatech.team83.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class LocationDetailActivity extends AppCompatActivity {
    private Model model;
    private ArrayList<Location> locations;
    private TextView locationName;
    private TextView locationType;
    private TextView longitude;
    private TextView latitude;
    private TextView address;
    private TextView phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);

        // TODO need to access location


    }

    public void onBackPressed(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, LocationRecyclerActivity.class);
        startActivity(intent);
    }

    public void onEditLocationPressed(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, LocationEditActivity.class);
        startActivity(intent);
    }
}
