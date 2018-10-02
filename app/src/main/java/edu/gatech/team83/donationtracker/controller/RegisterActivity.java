package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Admin;
import edu.gatech.team83.donationtracker.model.LocationEmployee;
import edu.gatech.team83.donationtracker.model.Manager;
import edu.gatech.team83.donationtracker.model.Model;
import edu.gatech.team83.donationtracker.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private EditText confirmPass;
    private Spinner type;
    private Model model = Model.getInstance();
    private User u;
    String accTypes[] = {"User", "Location Employee", "Manager", "Admin"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPassword);
        type = (Spinner) findViewById(R.id.accountType);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, accTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }

    public void onRegisterPressed(View v) {
        //pass word doesn't match
        u = new User(user.getText().toString(), pass.getText().toString());

        if (!pass.getText().toString().equals(confirmPass.getText().toString())) {
            Snackbar failed = Snackbar.make(v, "Password confirmation does not match!", Snackbar.LENGTH_SHORT);
            failed.show();
        //user already exists
        } else if (model.usernameCheck(u.getName())) {
            Snackbar failed = Snackbar.make(v, "User already exists!", Snackbar.LENGTH_SHORT);
            failed.show();
        //It worked
        } else {
            switch (type.getSelectedItem().toString()) {
                case "Admin":
                    u = new Admin(user.getText().toString(), pass.getText().toString());
                    break;
                case "Manager":
                    u = new Manager(user.getText().toString(), pass.getText().toString());
                    break;
                case "Location Employee":
                    u = new LocationEmployee(user.getText().toString(), pass.getText().toString());
                    break;
                default:
                    u = new User(user.getText().toString(), pass.getText().toString());
                    break;
            }
            model.addUser(u);
            Intent intent = new Intent(this,LoggedIn.class);
            startActivity(intent);
        }
    }

    public void onCancelPressed(View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
