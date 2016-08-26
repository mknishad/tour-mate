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
    static final String COL_EVENT_EMERGENCY = "EmergencyNumber";
    static final String COL_EVENT_FROM = "FromDate";
    static final String COL_EVENT_TO = "ToDate";
    static final String COL_EVENT_USER_ID_FOREIGN = "UserId";

    // PhotoMoment table
    static final String TABLE_PHOTO_MOMENT = "PhotoMoments";
    // Contacts Table Columns names
    static final String COL_PHOTO_MOMENT_ID = "MomentId";
    static final String COL_PHOTO_MOMENT_CAPTION = "Caption";
    static final String COL_PHOTO_MOMENT_IMAGE = "Image";
    static final String COL_PHOTO_MOMENT_EVENT_ID_FOREIGN = "EventId";

    // Expense moment
    static final String TABLE_EXPENSE_MOMENT = "Expense";
    static final String COL_EXPENSE_MOMENT_ID = "ExpenseId";
    static final String COL_EXPENSE_MOMENT_TITLE = "Title";
    static final String COL_EXPENSE_MOMENT_AMOUNT = "Amount";
    static final String COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN = "EventId";

    // Query to create Users table
    private static String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" + COL_USER_ID +
            " INTEGER PRIMARY KEY, " + COL_USER_NAME + " TEXT, " + COL_USER_EMAIL + " TEXT, " +
            COL_USER_PASSWORD + " TEXT);";

    // Query to create Events table
    private static String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + " (" +
            COL_EVENT_ID + " INTEGER PRIMARY KEY, " + COL_EVENT_NAME + " TEXT, " + COL_EVENT_FROM +
            " TEXT, " + COL_EVENT_TO + " TEXT, " + COL_EVENT_BUDGET + " DOUBLE, " +
            COL_EVENT_EMERGENCY + " TEXT, " + COL_EVENT_USER_ID_FOREIGN + " INTEGER, FOREIGN KEY" +
            "(" + COL_EVENT_USER_ID_FOREIGN + ") REFERENCES " + TABLE_USERS + "(" +
            COL_USER_ID + "));";

    // Query to create PhotoMoment table
    private static String CREATE_PHOTO_MOMENTS_TABLE = "CREATE TABLE " + TABLE_PHOTO_MOMENT + "("
            + COL_PHOTO_MOMENT_ID + " INTEGER PRIMARY KEY, " + COL_PHOTO_MOMENT_CAPTION + " TEXT, "
            + COL_PHOTO_MOMENT_IMAGE + " BLOB, " + COL_PHOTO_MOMENT_EVENT_ID_FOREIGN + " INTEGER, FOREIGN KEY(" +
            COL_PHOTO_MOMENT_EVENT_ID_FOREIGN + ") REFERENCES " + TABLE_EVENTS + "(" + COL_EVENT_ID + "));";

    // Query to create Expense table
    private static String CREATE_EXPENSE_MOMENTS_TABLE = "CREATE TABLE " + TABLE_EXPENSE_MOMENT + "("
            + COL_EXPENSE_MOMENT_ID + " INTEGER PRIMARY KEY, " + COL_EXPENSE_MOMENT_TITLE + " " +
            "TEXT, " + COL_EXPENSE_MOMENT_AMOUNT + " DOUBLE, " +
            COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN + " INTEGER, " +
            "FOREIGN KEY(" + COL_PHOTO_MOMENT_EVENT_ID_FOREIGN + ") REFERENCES " + TABLE_EVENTS + "(" +
            COL_EVENT_ID + "));";

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE", " constructor: " + " tables created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
        db.execSQL(CREATE_PHOTO_MOMENTS_TABLE);
        db.execSQL(CREATE_EXPENSE_MOMENTS_TABLE);
        Log.e("DATABASE", "onCreate: " + " tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_MOMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_MOMENT);
        onCreate(db);
    }

}
