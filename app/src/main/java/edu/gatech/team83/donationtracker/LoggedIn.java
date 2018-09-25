package edu.gatech.team83.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedIn extends AppCompatActivity {
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        logout = findViewById(R.id.log_out_button);
    }

    public void onLogoutPressed(View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
