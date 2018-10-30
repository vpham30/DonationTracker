package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class SearchActivity extends AppCompatActivity {

    private EditText itemField;
    private Spinner category;
    private EditText locField;
    private Model model = Model.getInstance();
    private List<Item> inv;
    private List<Location> locs;
    private List<String> cats;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        itemField = findViewById(R.id.editText2);
        category = findViewById(R.id.LocSpinner);
        locField = findViewById(R.id.editText);

        cats = Arrays.asList(Item.categories);
        cats.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        if (getIntent().hasExtra("Act") && getIntent().getStringExtra("Act").equals("InventoryRecyclerActivity")) {
            inv = getIntent().getParcelableArrayListExtra("Inventory");
            locs = getIntent().getParcelableArrayListExtra("Locations");
            itemField.setText(inv.get(0).getName());
            category.setSelection(cats.indexOf(inv.get(0).getCategory()));
            locField.setText(inv.get(0).getLocName());
        } else if (getIntent().hasExtra("Act") && getIntent().getStringExtra("Act").equals("LocationRecyclerActivity")) {
            locs = getIntent().getParcelableArrayListExtra("Locations");
            itemField.setText("");
            locField.setText(inv.get(0).getLocName());
        } else {
            itemField.setText("");
            locField.setText("");
            category.setSelection(0);
        }
    }

    public void onBackPressed(View v) {
        Intent intent = new Intent(this, AppLandingActivity.class);
        startActivity(intent);
    }

    public void onSearchPressed(View v) {
        Intent intent = new Intent(this, InventoryRecyclerActivity.class);
        inv = model.itemSearch(itemField.getText().toString(), category.getSelectedItem().toString(), locs.get(0));
        if (inv.size() == 0) {
            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            intent.putParcelableArrayListExtra("Inventory", (ArrayList<? extends Parcelable>) inv);
            intent.putExtra("Act", "SearchActivity");
            startActivity(intent);
        }
    }

    public void onSearchLocPressed(View v) {
        Intent intent = new Intent(this, LocationRecyclerActivity.class);
        locs = model.locSearch(locField.getText().toString());
        if (locs.size() == 0) {
            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            intent.putParcelableArrayListExtra("Locations", (ArrayList<? extends Parcelable>) locs);
            intent.putExtra("Act", "SearchActivity");
            startActivity(intent);
        }
    }
}
