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

public class SignUpActivity extends AppCompatActivity {

    private EditText userName;
    private EditText email;
    private EditText password;
    private EditText rePassword;
    private UsersDataSource usersDataSource;
    private ArrayList<User> users;
    private ArrayList<String> emails;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.signUpPassword);
        rePassword = (EditText) findViewById(R.id.signUpRepassword);
        usersDataSource = new UsersDataSource(this);

        sharedPreferences = getSharedPreferences(LoginActivity.LOGINPREF, Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        users = usersDataSource.getAllUsers();
        Log.e("SignUp ", "onStart "+ users.size());
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

    public void signUp(View view) {
        String userNameText = userName.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String rePasswordText = rePassword.getText().toString();
        User user = null;

        if (!userNameText.equals("") && !emailText.equals("") && !passwordText.equals("") &&
                !rePasswordText.equals("")) {
            if (passwordText.length() > 5 && rePasswordText.length() > 5) {
                if (passwordText.equals(rePasswordText)) {
                    if (emails.contains(emailText)) {
                        Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        user = new User(userNameText, emailText, passwordText);
                        usersDataSource.addUser(user);
                        Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Constants.USER_EMAIL, emailText);
                        editor.commit();
                        Intent intent = new Intent(this, TravelEventsActivity.class);
                        intent.putExtra(Constants.LOGIN_SIGNUP_ADD_EVENT, "SignUp");
                        intent.putExtra(Constants.USER_EMAIL, emailText);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter at least 6 characters as password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter information properly", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToSignIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
