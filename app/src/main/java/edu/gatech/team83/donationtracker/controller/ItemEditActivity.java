package edu.gatech.team83.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class ItemEditActivity extends AppCompatActivity {

    private EditText name;
    private EditText time;
    private EditText value;
    private EditText shortDescription;
    private EditText longDescription;
    private EditText category;
    private Model model;
    private Location location;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_edit);
        name = findViewById(R.id.item_name_input);
        time = findViewById(R.id.time_stamp_input);
        value = findViewById(R.id.item_value_input);
        shortDescription = findViewById(R.id.item_short_description_input);
        longDescription = findViewById(R.id.item_full_description_input);
        category = findViewById(R.id.item_category_input);
        EditText item_location = findViewById(R.id.item_location_input);
        model = Model.getInstance();

        location = getIntent().getParcelableExtra("Location");

        if(getIntent().hasExtra("Item")) {
            item = getIntent().getParcelableExtra("Item");


            name.setText(item.getName());
            time.setText(item.getTime());
            value.setText(item.getValue());
            shortDescription.setText(item.getShortDesc());
            longDescription.setText(item.getLongDesc());
            category.setText(item.getCategory());
            item_location.setText(location.getName());
        }

        else {
            name.setText("");
            time.setText("");
            value.setText("");
            shortDescription.setText("");
            longDescription.setText("");
            category.setText("");
            item_location.setText(location.getName());

        }

    }

    public void onSubmitPressed(View v) {
        Item newItem = new Item();
        newItem.setName(name.getText().toString());
        newItem.setTime(time.getText().toString());
        newItem.setValue(value.getText().toString());
        newItem.setShortDesc(shortDescription.getText().toString());
        newItem.setLongDesc(longDescription.getText().toString());
        newItem.setCategory(category.getText().toString());

        if (getIntent().hasExtra("Item")) {
            model.editDonation(location, item, newItem);
        } else {
            model.addDonation(location, newItem);
        }
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("Item", newItem);
        intent.putExtra("Location", location);
        startActivity(intent);
    }

    public void onCancelPressed(View v) {
        if (getIntent().hasExtra("Item")) {
            Intent intent = new Intent(this, ItemDetailActivity.class);
            intent.putExtra("Location", location);
            intent.putExtra("Item", item);
            if (getIntent().hasExtra("Act")
                    && "SearchActivity".equals(getIntent().getStringExtra("Act"))) {
                intent.putExtra("Act", "SearchActivity");
            }
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, InventoryRecyclerActivity.class);
            intent.putExtra("Location", location);
            startActivity(intent);
        }
    }
}


