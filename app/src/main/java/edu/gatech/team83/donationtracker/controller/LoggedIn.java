package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.team83.donationtracker.R;

public class LoggedIn extends AppCompatActivity {
    private TextView welcome;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        welcome = findViewById(R.id.textView_logged_in);
        mAuth = FirebaseAuth.getInstance();
        welcome.setText(mAuth.getCurrentUser().getEmail());
    }

    public void onLogoutPressed(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
