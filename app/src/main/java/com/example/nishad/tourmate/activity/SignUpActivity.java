 package com.example.nishad.tourmate.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.database.UsersDataSource;
import com.example.nishad.tourmate.model.User;

import java.util.ArrayList;

 public class SignUpActivity extends AppCompatActivity {

     private EditText userName;
     private EditText email;
     private EditText password;
     private EditText repassword;
     private UsersDataSource usersDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.signUpPassword);
        repassword  = (EditText) findViewById(R.id.signUpRepassword);
        usersDataSource = new UsersDataSource(this);
    }

     public void signUp(View view) {
         String userNameText = userName.getText().toString();
         String emailText = email.getText().toString();
         String passwordText = password.getText().toString();
         String repasswordText = repassword.getText().toString();
         ArrayList<String> emails = usersDataSource.getAllUsers();
         boolean userFound = false;
         User user = null;

         if (!userNameText.equals("") || !emailText.equals("") || !passwordText.equals("") || !repasswordText.equals("")) {
             if (passwordText.equals(repasswordText)) {
                 if (emails.contains(emailText)) {
                     Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                 } else {
                     user = new User(userNameText, emailText, passwordText);
                     usersDataSource.addUser(user);
                     Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(this, TravelEventsActivity.class);
                     intent.putExtra("userEmail", emailText);
                     startActivity(intent);
                 }
             } else {
                 Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
             }
         } else {
             Toast.makeText(this, "Input the information properly", Toast.LENGTH_SHORT).show();
         }
     }
 }
