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
import edu.gatech.team83.donationtracker.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    EditText confirmPass;
    Button cancel;
    Button register;
    Model model = Model.getInstance();
    User u;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = (EditText) findViewById(R.id.usernameField);
        pass = (EditText) findViewById(R.id.passwordField);
        confirmPass = (EditText) findViewById(R.id.confirmPasswordField);
        register = (Button) findViewById(R.id.register);
        cancel = (Button) findViewById(R.id.cancel);
        u = new User(user.getText().toString(), pass.getText().toString());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterPressed(v);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelPressed(v);
            }
        });
    }

    public void onRegisterPressed(View v) {
        //pass word doesn't match
        if (!pass.getText().toString().equals(confirmPass.getText().toString())) {
            Snackbar failed = Snackbar.make(v, "Password confirmation does not match!", Snackbar.LENGTH_SHORT);
            failed.show();
        //user already exists
        } else if (!model.usernameCheck(u.getName())) {
            Snackbar failed = Snackbar.make(v, "User already exists!", Snackbar.LENGTH_SHORT);
            failed.show();
        //It worked
        } else {
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
