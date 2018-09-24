package edu.gatech.team83.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button login;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.usernameField);
        pass = (EditText) findViewById(R.id.passwordField);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.cancel);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginPressed(v);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelPressed(v);
            }
        });
    }

    public void onLoginPressed(View view) {
        if (user.getText().toString().equals("user") && pass.getText().toString().equals("pass")) {
            Intent intent = new Intent(this, WelcomeActivity.class);
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
