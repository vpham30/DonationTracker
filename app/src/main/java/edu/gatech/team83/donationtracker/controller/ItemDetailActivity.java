package edu.gatech.team83.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;
import edu.gatech.team83.donationtracker.R;

public class ItemDetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView time;
    private TextView value;
    private TextView shortDesc;
    private TextView longDesc;
    private TextView category;
    private TextView item_location;
    private Item item;
    private Model model;
    private Location location;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        item = getIntent().getParcelableExtra("Item");
        location = getIntent().getParcelableExtra("Location");
        model = Model.getInstance();

        name = findViewById(R.id.item_name);
        time = findViewById(R.id.item_time);
        value = findViewById(R.id.item_value);
        shortDesc = findViewById(R.id.item_short);
        longDesc = findViewById(R.id.item_full);
        category = findViewById(R.id.item_category);
        item_location = findViewById(R.id.item_location);


        name.setText(item.getName());
        time.setText(item.getTime());
        value.setText(item.getValue());
        shortDesc.setText(item.getShortDesc());
        longDesc.setText(item.getLongDesc());
        category.setText(item.getCategory());
        item_location.setText(location.getName());

    }

    public void onBackItemPressed(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, InventoryRecyclerActivity.class);
        intent.putExtra("Location", location);
        startActivity(intent);
    }

    public void onEditItemPressed(View v) {
        if (model.getType().equals("Admin")) {
            Context context = v.getContext();
            Intent intent = new Intent(context, ItemEditActivity.class);
            intent.putExtra("Item", item);
            intent.putExtra("Location", location);
            startActivity(intent);
        } else {
            Snackbar failed = Snackbar.make(v, "You are not an admin", Snackbar.LENGTH_SHORT);
            failed.show();
        }

    }
}