package com.example.nishad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nishad.tourmate.model.User;

import java.util.ArrayList;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class UsersDataSource {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    // Initialize database helper in constructor
    public UsersDataSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // Open the database for operation
    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    // Close the database
    public void close() {
        database.close();
    }

    // Add an user to Users table
    public boolean addUser(User user) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USER_NAME, user.getUserName());
        values.put(DatabaseHelper.COL_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COL_PASSWORD, user.getPassword());

        long inserted = database.insert(DatabaseHelper.TABLE_USERS, null, values);

        this.close();

        return inserted>0 ? true:false;
    }

    // Get user from database by email
    public User getUser(String email) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS, new String[] {DatabaseHelper.COL_USER_ID, DatabaseHelper.COL_USER_NAME, DatabaseHelper.COL_EMAIL, DatabaseHelper.COL_PASSWORD}, DatabaseHelper.COL_EMAIL + " = '" + email + "';", null, null, null, null);

        cursor.moveToFirst();
        User user = createUser(cursor);
        cursor.close();
        this.close();

        return user;
    }

    // Get all users
    public ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<>();
        this.open();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USERS + ";", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String mEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));
            users.add(mEmail);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();

        return users;
    }

    // Create an user from cursor data
    private User createUser(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_NAME));
        String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));
        String pass= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PASSWORD));

        User u = new User(id, name, email, pass);

        return u;
    }
}
