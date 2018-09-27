package edu.gatech.team83.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistration();
            }
        });
    }

    public void openRegistration() {
        Intent intent = new Intent (this, Registration.class);
        startActivity(intent);
    }
        
    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
        
    public void onRegisterPressed(View view) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
    }
}
