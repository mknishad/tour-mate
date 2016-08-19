package com.example.nishad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.nishad.tourmate.model.Moment;

/**
 * Created by Nishad on 16-Aug-16.
 */
public class MomentsDataSource {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    // Initialize database helper in constructor
    public MomentsDataSource(Context context) {
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

    // Add a status moment
    public boolean addStatusMoment(Moment moment) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_MOMENT_TYPE, moment.getMomentType());
        values.put(DatabaseHelper.COL_MOMENT_DETAILS, moment.getDetail());

        long inserted = database.insert(DatabaseHelper.TABLE_MOMENTS, null, values);
        this.close();

        return inserted>0 ? true:false;
    }

    // Add a photo moment
    public boolean addPhotoMoment(Moment moment) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_MOMENT_TYPE, moment.getMomentType());
        values.put(DatabaseHelper.COL_MOMENT_DETAILS, moment.getDetail());
        values.put(DatabaseHelper.COL_MOMENT_IMAGE_PATH, moment.getImagePath());

        long inserted = database.insert(DatabaseHelper.TABLE_MOMENTS, null, values);
        this.close();

        return inserted>0 ? true:false;
    }

    // Add a expense moment
    public boolean addExpenseMoment(Moment moment) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_MOMENT_TYPE, moment.getMomentType());
        values.put(DatabaseHelper.COL_MOMENT_DETAILS, moment.getDetail());
        values.put(DatabaseHelper.COL_MOMENT_COST, moment.getCost());

        long inserted = database.insert(DatabaseHelper.TABLE_MOMENTS, null, values);
        this.close();

        return inserted>0 ? true:false;
    }

    // Delete a moment from the database
    public boolean deleteMoment(int momentId) {
        this.open();

        int deleted = database.delete(DatabaseHelper.TABLE_MOMENTS, DatabaseHelper.COL_MOMENT_ID + " = " + momentId, null);
        this.close();

        return deleted>0 ? true:false;
    }

}
