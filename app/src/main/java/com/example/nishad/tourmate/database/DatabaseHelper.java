package com.example.nishad.tourmate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = " TourMate.db";
    static final int DATABASE_VERSION = 1;

    // Users table
    static final String TABLE_USERS = "Users";
    static final String COL_USER_ID = "UserId";
    static final String COL_USER_NAME = "UserName";
    static final String COL_EMAIL = "Email";
    static final String COL_PASSWORD= "Password";

    // Events table
    static final String TABLE_EVENTS = "Events";
    static final String COL_EVENT_ID = "EventId";
    static final String COL_EVENT_NAME = "EventName";
    static final String COL_BUDGET = "Budget";
    static final String COL_FROM = "From";
    static final String COL_TO = "To";
    static final String COL_USER_ID_FOREIGN = "UserId";

    // Moments table
    static final String TABLE_MOMENTS = "Moments";
    static final String COL_MOMENT_ID = "MomentId";
    static final String COL_MOMENT_TYPE = "MomentType";
    static final String COL_DETAILS = "Details";
    static final String COL_COST = "Cost";
    static final String COL_IMAGE_PATH = "ImagePath";
    static final String COL_EVENT_ID_FOREIGN = "EventId";

    // Query to create Users table
    private static String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" + COL_USER_ID + " INTEGER PRIMARY KEY, " + COL_USER_NAME + " TEXT, " + COL_EMAIL + " TEXT, " + COL_PASSWORD + " TEXT);";

    // Query to create Events table
    private static String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + " (" + COL_EVENT_ID + " INTEGER PRIMARY KEY, " + COL_EVENT_NAME + " TEXT, " + COL_FROM + " TEXT, " + COL_BUDGET + " DOUBLE, " + COL_TO + " TEXT, " + COL_USER_ID_FOREIGN + " INTEGER, FOREIGN KEY(" + COL_USER_ID_FOREIGN + ") REFERENCES " + TABLE_USERS + "(" + COL_USER_ID + "));";

    // Query to create Moment table
    private static String CREATE_MOMENTS_TABLE = "CREATE TABLE " + TABLE_MOMENTS + " (" + COL_MOMENT_ID + " INTEGER PRIMARY KEY, " + COL_MOMENT_TYPE + " TEXT, " + COL_DETAILS + " TEXT, " + COL_COST + " DOUBLE, " + COL_IMAGE_PATH + " TEXT, " + COL_EVENT_ID_FOREIGN + " INTEGER, FOREIGN KEY(" + COL_EVENT_ID_FOREIGN + ") REFERENCES " + TABLE_EVENTS + "(" + COL_EVENT_ID + "));";

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
        db.execSQL(CREATE_MOMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOMENTS);
        onCreate(db);
    }

}
