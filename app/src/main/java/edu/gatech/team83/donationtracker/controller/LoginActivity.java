package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Model;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    private Model model;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        user = findViewById(R.id.usernameField);
        pass = findViewById(R.id.passwordField);
        mAuth = FirebaseAuth.getInstance();
        model = Model.getInstance();
    }

    public void onLoginPressed(View view) {
        mAuth.signInWithEmailAndPassword(user.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            model.updateFromDatabase();
                            model.setCurrentuser(mAuth.getCurrentUser());
                            Intent intent = new Intent(LoginActivity.this, LocationRecyclerActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onCancelPressed(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
