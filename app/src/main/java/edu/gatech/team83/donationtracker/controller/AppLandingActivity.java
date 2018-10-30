package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Model;

public class AppLandingActivity extends AppCompatActivity {
    private Model model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_applanding);
        model = Model.getInstance();
    }

    public void onViewLocPressed (View view) {
        Intent intent = new Intent(this, LocationRecyclerActivity.class);
        intent.putParcelableArrayListExtra("Locations", model.getLocations());
        startActivity(intent);
    }

    public void onViewItemsPressed (View view) {
        Intent intent = new Intent(this, InventoryRecyclerActivity.class);
        intent.putParcelableArrayListExtra("Items", model.getAllItems());
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






}
