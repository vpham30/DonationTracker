package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class SearchActivity extends AppCompatActivity {

    private EditText itemField;
    private Spinner category;
    private EditText locField;
    private final Model model = Model.getInstance();
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        itemField = findViewById(R.id.editText2);
        category = findViewById(R.id.LocSpinner);
        locField = findViewById(R.id.editText);

        ArrayList<String> cats = new ArrayList<>(Arrays.asList(Item.categories));
        cats.add(0, "");
        ArrayAdapter<String> adapter
                = new ArrayAdapter(this,android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        if (getIntent().hasExtra("Act")
                && "LocationRecyclerActivity".equals(getIntent().getStringExtra("Act"))) {
            loc = getIntent().getParcelableExtra("Location");
            itemField.setText("");
            locField.setText(loc.getName());
        } else {
            itemField.setText("");
            locField.setText("");
            category.setSelection(0);
        }
    }

    public void onBackSearchButton(View v) {
        Intent intent = new Intent(this, AppLandingActivity.class);
        startActivity(intent);
    }

    public void onSearchPressed(View v) {
        Intent intent = new Intent(this, InventoryRecyclerActivity.class);
        ArrayList<Item> inv = (ArrayList<Item>) model.itemSearch(itemField.getText().toString(),
                category.getSelectedItem().toString(), loc);
        if (inv.isEmpty()) {
            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            intent.putParcelableArrayListExtra("Inventory", inv);
            intent.putExtra("Act", "SearchActivity");
            startActivity(intent);
        }
    }

    public void onSearchLocPressed(View v) {
        Intent intent = new Intent(this, LocationRecyclerActivity.class);
        ArrayList<Location> locations = model.locSearch(locField.getText().toString());
        if (locations.isEmpty()) {
            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            intent.putParcelableArrayListExtra("Locations",  locations);
            intent.putExtra("Act", "SearchActivity");
            startActivity(intent);
        }
    }
}
