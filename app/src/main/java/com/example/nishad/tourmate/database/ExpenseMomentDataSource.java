package com.example.nishad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nishad.tourmate.model.ExpenseMoment;

import java.util.ArrayList;

/**
 * Created by Nishad on 8/25/2016.
 */
public class ExpenseMomentDataSource {


    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private ExpenseMoment expenseMoment;

    public ExpenseMomentDataSource(Context context) {
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

    public boolean addExpenseMoment(ExpenseMoment expenseMoment) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EXPENSE_MOMENT_TITLE, expenseMoment.getName());
        values.put(DatabaseHelper.COL_EXPENSE_MOMENT_AMOUNT, expenseMoment.getAmount());
        values.put(DatabaseHelper.COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN, expenseMoment.getEventIdForeign());

        // Inserting Row
        long inserted = database.insert(DatabaseHelper.TABLE_EXPENSE_MOMENT, null, values);
        this.close(); // Closing database connection

        return inserted > 0 ? true : false;
    }

    // Getting single expense moment
    public ExpenseMoment getExpenseMoment(int id) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EXPENSE_MOMENT, new String[]{
                DatabaseHelper.COL_EXPENSE_MOMENT_ID, DatabaseHelper.COL_EXPENSE_MOMENT_TITLE, DatabaseHelper
                .COL_EXPENSE_MOMENT_AMOUNT, DatabaseHelper.COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN}, DatabaseHelper
                .COL_EXPENSE_MOMENT_ID + " = " + id, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ExpenseMoment expenseMoment = createExpenseMoment(cursor);

        cursor.close();
        this.close();

        return expenseMoment;
    }

    // Getting All ExpenseMoments
    public ArrayList<ExpenseMoment> getAllPhotoMoments(int eventId) {
        ArrayList<ExpenseMoment> expenseMoments = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EXPENSE_MOMENT, new String[]
                {DatabaseHelper.COL_EXPENSE_MOMENT_ID, DatabaseHelper.COL_EXPENSE_MOMENT_TITLE,
                        DatabaseHelper.COL_EXPENSE_MOMENT_AMOUNT,
                        DatabaseHelper.COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN},
                DatabaseHelper.COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN + " = " + eventId + ";", null, null, null, null);

        Log.e("EXPENSE_MOMENTS_DATA", "getAllExpenseMoments: " + cursor.getCount());

        // looping through all rows and adding to list
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                expenseMoment = createExpenseMoment(cursor);
                expenseMoments.add(expenseMoment);
                cursor.moveToNext();
            }
        }
        cursor.close();
        // close inserting data from database
        this.close();

        return expenseMoments;
    }

    private ExpenseMoment createExpenseMoment(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EXPENSE_MOMENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EXPENSE_MOMENT_TITLE));
        double amount = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COL_EXPENSE_MOMENT_AMOUNT));
        int eventIdForeign = cursor.getInt(cursor.getColumnIndex(DatabaseHelper
                .COL_EXPENSE_MOMENT_EVENT_ID_FOREIGN));

        ExpenseMoment expenseMoment = new ExpenseMoment(id, name, amount, eventIdForeign);

        return expenseMoment;
    }

}
