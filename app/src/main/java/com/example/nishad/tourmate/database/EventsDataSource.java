package com.example.nishad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class EventsDataSource {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    com.example.nishad.tourmate.model.Event event;

    // Initialize database helper in constructor
    public EventsDataSource(Context context) {
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

    // Add an event to Events table
    public boolean addEvent(com.example.nishad.tourmate.model.Event event) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EVENT_NAME, event.getEventName());
        values.put(DatabaseHelper.COL_BUDGET, event.getBudget());
        values.put(DatabaseHelper.COL_FROM, event.getFrom());
        values.put(DatabaseHelper.COL_TO, event.getTo());

        long inserted = database.insert(DatabaseHelper.TABLE_EVENTS, null, values);
        this.close();

        return inserted>0 ? true:false;
    }

    public ArrayList<com.example.nishad.tourmate.model.Event> getEvents(int userId) {
        ArrayList<com.example.nishad.tourmate.model.Event> events = new ArrayList<>();

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EVENTS, new String[] {DatabaseHelper.COL_EVENT_ID, DatabaseHelper.COL_EVENT_NAME, DatabaseHelper.COL_BUDGET, DatabaseHelper.COL_FROM, DatabaseHelper.COL_TO, DatabaseHelper.COL_USER_ID_FOREIGN}, DatabaseHelper.COL_USER_ID + " = " + userId + ";", null, null, null, null);

        if(cursor!=null && cursor.getCount()>0) {
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++) {
                event = createEvent(cursor);
                events.add(event);
                cursor.moveToNext();
            }
        }

        cursor.close();
        this.close();

        return events;
    }

    private com.example.nishad.tourmate.model.Event createEvent(Cursor cursor) {
        int eventId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_ID));
        String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
        double budget = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COL_BUDGET));
        String from = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FROM));
        String to = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TO));
        int userIdForeign = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_ID_FOREIGN));

        com.example.nishad.tourmate.model.Event event = new com.example.nishad.tourmate.model.Event(eventId, eventName, budget, from, to, userIdForeign);

        return event;
    }

}
