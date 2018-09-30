package edu.gatech.team83.donationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener {

    String accTypes[] = {"User", "Location Employee", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Spinner accountTypes = (Spinner) findViewById(R.id.accountType);
        accountTypes.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, accTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypes.setAdapter(aa);

    }


    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), accTypes[position], Toast.LENGTH_LONG).show();
    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
