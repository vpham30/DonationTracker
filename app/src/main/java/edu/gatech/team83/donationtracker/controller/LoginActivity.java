package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Model;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button login;
    Button cancel;
    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        user = (EditText) findViewById(R.id.usernameField);
        pass = (EditText) findViewById(R.id.passwordField);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.cancel);
    }

    public void onLoginPressed(View view) {
        if (model.validateUser(user.getText().toString(), pass.getText().toString())) {
            Intent intent = new Intent(this,LoggedIn.class);
            startActivity(intent);
        } else {
            Snackbar failed = Snackbar.make(view, "Invalid Login", Snackbar.LENGTH_SHORT);
            failed.show();
        }
    }

    public void onCancelPressed(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
