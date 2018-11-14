package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class AppLandingActivity extends AppCompatActivity {
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_applanding);
        model = Model.getInstance();
    }

    public void onViewLocPressed (View view) {
        Intent intent = new Intent(this, LocationRecyclerActivity.class);
        intent.putParcelableArrayListExtra("Locations", (ArrayList<Location>) model.getLocations());
        startActivity(intent);
    }

    public void onViewItemsPressed (View view) {
        Intent intent = new Intent(this, InventoryRecyclerActivity.class);
        startActivity(intent);
    }

    public void onSearchPressed (View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void onLogOutPressed (View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        model.signout();
        startActivity(intent);
    }

    public void onMapPressed (View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }






}
