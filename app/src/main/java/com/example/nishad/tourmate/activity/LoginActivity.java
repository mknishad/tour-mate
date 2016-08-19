package com.example.nishad.tourmate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.database.UsersDataSource;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private UsersDataSource usersDataSource;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usersDataSource = new UsersDataSource(this);
        etEmail = (EditText) findViewById(R.id.loginEmail);
        etPassword = (EditText) findViewById(R.id.loginPassword);
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        ArrayList<String> emails = usersDataSource.getAllUserEmails();

        if (!email.equals("") && !password.equals("")) {
            if (emails.contains(email)) {
                String userPassword = usersDataSource.getUserPassword(email);
                if (password.equals(userPassword)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, TravelEventsActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter information properly", Toast.LENGTH_SHORT).show();
        }
    }
}
