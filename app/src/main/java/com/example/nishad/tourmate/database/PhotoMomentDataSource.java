package com.example.nishad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nishad.tourmate.model.PhotoMoment;

import java.util.ArrayList;

/**
 * Created by Nishad on 8/25/2016.
 */
public class PhotoMomentDataSource {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private PhotoMoment photoMoment;

    public PhotoMomentDataSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
        Log.e("PHOTO_MOMENT_DATA", "db created");
    }

    // Open the database for operation
    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    // Close the database
    public void close() {
        database.close();
    }


    public boolean addPhotoMoment(PhotoMoment photoMoment) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_PHOTO_MOMENT_CAPTION, photoMoment.getName()); // Contact Name
        values.put(DatabaseHelper.COL_PHOTO_MOMENT_IMAGE, photoMoment.getImage()); // Contact Photo
        values.put(DatabaseHelper.COL_PHOTO_MOMENT_EVENT_ID_FOREIGN, photoMoment.get_userIdForeign());

        // Inserting Row
        long inserted = database.insert(DatabaseHelper.TABLE_PHOTO_MOMENT, null, values);
        this.close(); // Closing database connection

        return inserted>0 ? true:false;
    }

    // Getting single PhotoMoment
    public PhotoMoment getPhotoMoment(int id) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_PHOTO_MOMENT, new String[] {
                DatabaseHelper.COL_PHOTO_MOMENT_ID, DatabaseHelper.COL_PHOTO_MOMENT_CAPTION, DatabaseHelper.COL_PHOTO_MOMENT_IMAGE, DatabaseHelper.COL_PHOTO_MOMENT_EVENT_ID_FOREIGN}, DatabaseHelper.COL_PHOTO_MOMENT_ID + " = " + id, null, null, null, null);
        if (cursor != null)

            cursor.moveToFirst();

        PhotoMoment photoMoment = createPhotoMoment(cursor);

        cursor.close();
        this.close();

        // return contact
        return photoMoment;

    }

    // Getting All PhotoMoments
    public ArrayList<PhotoMoment> getAllPhotoMoments(int eventId) {
        ArrayList<PhotoMoment> photoMomentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_PHOTO_MOMENT, new String[]
                {DatabaseHelper.COL_PHOTO_MOMENT_ID, DatabaseHelper.COL_PHOTO_MOMENT_CAPTION, DatabaseHelper.COL_PHOTO_MOMENT_IMAGE,
                        DatabaseHelper.COL_PHOTO_MOMENT_EVENT_ID_FOREIGN}, DatabaseHelper.COL_PHOTO_MOMENT_EVENT_ID_FOREIGN
                + " = " + eventId + ";", null, null, null, null);

        Log.e("PHOTO_MOMENTS_DATA", "getAllPhotoMoments: " + cursor.getCount());

        // looping through all rows and adding to list
        if (cursor!=null && cursor.getCount()>0) {
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++) {
                photoMoment = createPhotoMoment(cursor);
                photoMomentList.add(photoMoment);
                cursor.moveToNext();
            }
        }
        cursor.close();
        // close inserting data from database
        this.close();

        return photoMomentList;
    }

    private PhotoMoment createPhotoMoment(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_PHOTO_MOMENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHOTO_MOMENT_CAPTION));
        byte[] image = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COL_PHOTO_MOMENT_IMAGE));
        int eventIdForeign = cursor.getInt(cursor.getColumnIndex(DatabaseHelper
                .COL_PHOTO_MOMENT_EVENT_ID_FOREIGN));

        PhotoMoment photoMoment = new PhotoMoment(id, name, image, eventIdForeign);

        return photoMoment;
    }

}
