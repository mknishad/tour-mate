package com.example.nishad.tourmate.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.UsersDataSource;
import com.example.nishad.tourmate.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private UsersDataSource usersDataSource;
    private EditText etEmail;
    private EditText etPassword;
    private ArrayList<User> users;
    SharedPreferences sharedPreferences;
    public static final String LOGINPREF = "Login";

    private ArrayList<String> emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usersDataSource = new UsersDataSource(this);
        etEmail = (EditText) findViewById(R.id.loginEmail);
        etPassword = (EditText) findViewById(R.id.loginPassword);

        sharedPreferences = getSharedPreferences(LOGINPREF, Context.MODE_PRIVATE);
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        String emailText = sharedPreferences.getString(Constants.USER_EMAIL, "empty");
        if (!emailText.equals("empty")) {
            Intent intent = new Intent(this, TravelEventsActivity.class);
            intent.putExtra(Constants.LOGIN_SIGNUP_ADD_EVENT, "Login");
            intent.putExtra(Constants.USER_EMAIL, emailText);
            startActivity(intent);
            this.finish();
        }
        users = usersDataSource.getAllUsers();
        Log.e("Login ", "onStart users.size() "+users.size());
        super.onStart();
    }

    @Override
    protected void onResume() {
        emails = new ArrayList<>();
        for (int i=0; i<users.size(); i++) {
            emails.add(users.get(i).getEmail());
        }
        super.onResume();
    }

    public void login(View view) {
        String emailText = etEmail.getText().toString();
        String passwordText = etPassword.getText().toString();

        if (!emailText.equals("") && !passwordText.equals("")) {
            if (emails.contains(emailText)) {
                String userPassword = usersDataSource.getUserPassword(emailText);
                if (passwordText.equals(userPassword)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.USER_EMAIL, emailText);
                    editor.commit();
                    Intent intent = new Intent(this, TravelEventsActivity.class);
                    intent.putExtra(Constants.LOGIN_SIGNUP_ADD_EVENT, "Login");
                    intent.putExtra(Constants.USER_EMAIL, emailText);
                    startActivity(intent);
                } else {
//                    Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            } else {
//                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "User not found "+users.size(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter information properly", Toast.LENGTH_SHORT).show();
        }
    }
}
