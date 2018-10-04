package edu.gatech.team83.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.AccountType;


public class RegisterActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private EditText confirmPass;
    private Spinner type;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);
        type = findViewById(R.id.accountType);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }

    public void onRegisterPressed(View v) {
        if (!pass.getText().toString().equals(confirmPass.getText().toString())) {
            Snackbar failed = Snackbar.make(v, "Password confirmation does not match!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            mAuth.createUserWithEmailAndPassword(user.getText().toString().trim(), pass.getText().toString().trim())
                    //Once it's done
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //if the user was added successfully add type to the db
                                Map<String, String> data = new HashMap<>();
                                data.put("type", ((AccountType) type.getSelectedItem()).name());
                                db.collection("users").document(mAuth.getCurrentUser().getUid())
                                        .set(data)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(RegisterActivity.this, "Registered!", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                Intent intent = new Intent(RegisterActivity.this, LoggedIn.class);
                                startActivity(intent);
                            } else {
                                //else, return exception
                                Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }); {
            }
        }
    }

    public void onCancelPressed(View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
