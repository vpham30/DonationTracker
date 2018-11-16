package edu.gatech.team83.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class LocationEditActivity extends AppCompatActivity {

    private EditText name;
    private EditText type;
    private EditText longitude;
    private EditText latitude;
    private EditText address;
    private Location location;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_edit);
        name = findViewById(R.id.name_input);
        type = findViewById(R.id.location_type_input);
        longitude = findViewById(R.id.longitude_input);
        latitude = findViewById(R.id.latitude_input);
        address = findViewById(R.id.address_input);
        EditText phoneNumber = findViewById(R.id.phone_input);
        model = Model.getInstance();

        if(getIntent().hasExtra("Location")) {
            location = getIntent().getParcelableExtra("Location");

            name.setText(location.getName());
            type.setText(location.getType());
            longitude.setText(location.getLongitude());
            latitude.setText(location.getLatitude());
            address.setText(location.getAddress());
            //phoneNumber.setText(location.getPhonenumber());
        }

        else {
            name.setText("");
            type.setText("");
            longitude.setText("");
            latitude.setText("");
            address.setText("");
            phoneNumber.setText("");
        }

    }

    public void onLocationSubmitPressed(View v) {
        Location loc = new Location();
        loc.setName(name.getText().toString());
        loc.setType(type.getText().toString());
        loc.setLongitude(longitude.getText().toString());
        loc.setLatitude(latitude.getText().toString());
        loc.setAddress(address.getText().toString());
        //loc.setPhonenumber(phoneNumber.getText().toString());

        if (getIntent().hasExtra("Location")) {
            model.editLocation(location, loc);
        } else {
            model.addLocation(loc);
        }
        Intent intent = new Intent(this, LocationRecyclerActivity.class);
        startActivity(intent);
    }

    public void onLocationCancelPressed(View v) {
        if (getIntent().hasExtra("Location")) {
            Intent intent = new Intent(this, LocationDetailActivity.class);
            intent.putExtra("Location", location);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LocationRecyclerActivity.class);
            startActivity(intent);
        }
    }
}


