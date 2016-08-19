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
        values.put(DatabaseHelper.COL_EVENT_BUDGET, event.getBudget());
        values.put(DatabaseHelper.COL_EVENT_FROM, event.getFrom());
        values.put(DatabaseHelper.COL_EVENT_TO, event.getTo());

        long inserted = database.insert(DatabaseHelper.TABLE_EVENTS, null, values);
        this.close();

        return inserted>0 ? true:false;
    }

    // Get a single event
    public com.example.nishad.tourmate.model.Event getEvent(int eventId) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EVENTS, new String[] {DatabaseHelper.COL_EVENT_ID, DatabaseHelper.COL_EVENT_NAME, DatabaseHelper.COL_EVENT_BUDGET, DatabaseHelper.COL_EVENT_FROM, DatabaseHelper.COL_EVENT_TO, DatabaseHelper.COL_USER_ID_FOREIGN}, DatabaseHelper.COL_EVENT_ID + " = " + eventId + ";", null, null, null, null);

        com.example.nishad.tourmate.model.Event event = createEvent(cursor);

        cursor.close();
        this.close();

        return event;
    }

    // Get all events of an user
    public ArrayList<com.example.nishad.tourmate.model.Event> getAllEvents(int userId) {
        ArrayList<com.example.nishad.tourmate.model.Event> events = new ArrayList<>();

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EVENTS, new String[] {DatabaseHelper.COL_EVENT_ID, DatabaseHelper.COL_EVENT_NAME, DatabaseHelper.COL_EVENT_BUDGET, DatabaseHelper.COL_EVENT_FROM, DatabaseHelper.COL_EVENT_TO, DatabaseHelper.COL_USER_ID_FOREIGN}, DatabaseHelper.COL_USER_ID + " = " + userId + ";", null, null, null, null);

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

    // Update an event
    public boolean updateEvent(int eventId, com.example.nishad.tourmate.model.Event event) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EVENT_NAME, event.getEventName());
        values.put(DatabaseHelper.COL_EVENT_BUDGET, event.getBudget());
        values.put(DatabaseHelper.COL_EVENT_FROM, event.getFrom());
        values.put(DatabaseHelper.COL_EVENT_TO, event.getTo());

        int updated = database.update(DatabaseHelper.TABLE_EVENTS, values, DatabaseHelper.COL_EVENT_ID + " = " + eventId, null);
        this.close();

        return updated>0 ? true:false;
    }

    // Delete an event from the database
    public boolean deleteEvent(int eventId) {
        this.open();

        int deleted = database.delete(DatabaseHelper.TABLE_EVENTS, DatabaseHelper.COL_EVENT_ID + " = " + eventId, null);
        this.close();

        return deleted>0 ? true:false;
    }

    private com.example.nishad.tourmate.model.Event createEvent(Cursor cursor) {
        int eventId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_ID));
        String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
        double budget = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_BUDGET));
        String from = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_FROM));
        String to = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_TO));
        int userIdForeign = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_ID_FOREIGN));

        com.example.nishad.tourmate.model.Event event = new com.example.nishad.tourmate.model.Event(eventId, eventName, budget, from, to, userIdForeign);

        return event;
    }

}
