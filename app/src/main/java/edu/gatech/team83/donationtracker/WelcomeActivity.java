package edu.gatech.team83.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    
    login = (Button) findViewById(R.id.login);
    register = (Button) findViewById(R.id.register);
    
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onLoginPressed(v);
        }
    });
    
    register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterPressed(v);
            }
    });
    
        
        public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
        
        public void onRegisterPressed(View view) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
}
