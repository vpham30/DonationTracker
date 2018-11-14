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
    private Item item;
    private Model model;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        item = getIntent().getParcelableExtra("Item");
        model = Model.getInstance();

        TextView name = findViewById(R.id.item_name);
        TextView time = findViewById(R.id.item_time);
        TextView value = findViewById(R.id.item_value);
        TextView shortDesc = findViewById(R.id.item_short);
        TextView longDesc = findViewById(R.id.item_full);
        TextView category = findViewById(R.id.item_category);
        TextView item_location = findViewById(R.id.item_location);


        name.setText(item.getName());
        time.setText(item.getTime());
        value.setText(item.getValue());
        shortDesc.setText(item.getShortDesc());
        longDesc.setText(item.getLongDesc());
        category.setText(item.getCategory());
        item_location.setText(item.getLocName());

    }

    public void onBackItemPressed(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, InventoryRecyclerActivity.class);
        if (getIntent().hasExtra("Location")) {
            location = getIntent().getParcelableExtra("Location");
            intent.putExtra("Location", location);
        }
        if (getIntent().hasExtra("Act")
                && "SearchActivity".equals(getIntent().getStringExtra("Act"))) {
            intent.putExtra("Act", "SearchActivity");
            intent.putExtra("Inventory", getIntent().getParcelableArrayListExtra("Inventory"));
        }
        startActivity(intent);
    }

    public void onEditItemPressed(View v) {
        if ("Admin".equals(model.getType())) {
            Context context = v.getContext();
            Intent intent = new Intent(context, ItemEditActivity.class);
            intent.putExtra("Item", item);
            intent.putExtra("Location", location);
            if (getIntent().hasExtra("Act")
                    && "SearchActivity".equals(getIntent().getStringExtra("Act"))) {
                intent.putExtra("Act", "SearchActivity");
            }
            startActivity(intent);
        } else {
            Snackbar failed = Snackbar.make(v, "You are not an admin", Snackbar.LENGTH_SHORT);
            failed.show();
        }

    }

    public void onDeleteItemPressed(View v) {
        //will do something later
        model.updateFromDatabase();
    }
}