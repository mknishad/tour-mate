package com.example.nishad.tourmate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "TourMate.db";
    static final int DATABASE_VERSION = 1;

    // Users table
    static final String TABLE_USERS = "Users";
    static final String COL_USER_ID = "UserId";
    static final String COL_USER_NAME = "UserName";
    static final String COL_USER_EMAIL = "Email";
    static final String COL_USER_PASSWORD = "Password";

    // Events table
    static final String TABLE_EVENTS = "Events";
    static final String COL_EVENT_ID = "EventId";
    static final String COL_EVENT_NAME = "EventName";
    static final String COL_EVENT_BUDGET = "Budget";
    static final String COL_EVENT_FROM = "FromDate";
    static final String COL_EVENT_TO = "ToDate";
    static final String COL_USER_ID_FOREIGN = "UserId";

    // Moments table
    static final String TABLE_MOMENTS = "Moments";
    static final String COL_MOMENT_ID = "MomentId";
    static final String COL_MOMENT_TYPE = "MomentType";
    static final String COL_MOMENT_DETAILS = "Details";
    static final String COL_MOMENT_COST = "Cost";
    static final String COL_MOMENT_IMAGE_PATH = "ImagePath";
    static final String COL_EVENT_ID_FOREIGN = "EventId";

    // PhotoMoment table
    static final String TABLE_PHOTO_MOMENT = "PhotoMoment";

    // Contacts Table Columns names
    static final String KEY_ID = "Id";
    static final String KEY_NAME = "Name";
    static final String KEY_IMAGE = "Image";
    static final String KEY_EVENT_ID_FOREIGN = "EventId";

    // Query to create Users table
    private static String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" + COL_USER_ID +
            " INTEGER PRIMARY KEY, " + COL_USER_NAME + " TEXT, " + COL_USER_EMAIL + " TEXT, " +
            COL_USER_PASSWORD + " TEXT);";

    // Query to create Events table
    private static String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + " (" +
            COL_EVENT_ID + " INTEGER PRIMARY KEY, " + COL_EVENT_NAME + " TEXT, " + COL_EVENT_FROM +
            " TEXT, " + COL_EVENT_TO + " TEXT, " + COL_EVENT_BUDGET + " DOUBLE, " + COL_USER_ID_FOREIGN +
            " INTEGER, FOREIGN KEY(" + COL_USER_ID_FOREIGN + ") REFERENCES " + TABLE_USERS + "(" +
            COL_USER_ID + "));";

    /*
    // Query to create Moment table
    private static String CREATE_MOMENTS_TABLE = "CREATE TABLE " + TABLE_MOMENTS + " (" +
            COL_MOMENT_ID + " INTEGER PRIMARY KEY, " + COL_MOMENT_TYPE + " TEXT, " +
            COL_MOMENT_DETAILS + " TEXT, " + COL_MOMENT_COST + " DOUBLE, " + COL_MOMENT_IMAGE_PATH +
            " TEXT, " + COL_EVENT_ID_FOREIGN + " INTEGER, FOREIGN KEY(" + COL_EVENT_ID_FOREIGN +
            ") REFERENCES " + TABLE_EVENTS + "(" + COL_EVENT_ID + "));";
    */

    // Query to create PhotoMoment table
    private static String CREATE_PHOTO_MOMENT_TABLE = "CREATE TABLE " + TABLE_PHOTO_MOMENT + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
            + KEY_IMAGE + " BLOB, " + KEY_EVENT_ID_FOREIGN + " INTEGER, FOREIGN KEY(" +
            KEY_EVENT_ID_FOREIGN + ") REFERENCES " + TABLE_EVENTS + "(" + COL_EVENT_ID + "));";

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE", " constructor: " + " tables created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
//        db.execSQL(CREATE_MOMENTS_TABLE);
        db.execSQL(CREATE_PHOTO_MOMENT_TABLE);
        Log.e("DATABASE", "onCreate: " + " tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_MOMENT);
        onCreate(db);
    }

}
