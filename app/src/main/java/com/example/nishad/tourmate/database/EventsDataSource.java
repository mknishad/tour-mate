package com.example.nishad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nishad.tourmate.model.Event;

import java.util.ArrayList;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class EventsDataSource {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Event event;

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
    public boolean addEvent(int userId, Event event) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EVENT_NAME, event.getEventName());
        values.put(DatabaseHelper.COL_EVENT_BUDGET, event.getBudget());
        values.put(DatabaseHelper.COL_EVENT_EMERGENCY, event.getEmergencyNumber());
        values.put(DatabaseHelper.COL_EVENT_FROM, event.getFromDate());
        values.put(DatabaseHelper.COL_EVENT_TO, event.getToDate());
        values.put(DatabaseHelper.COL_EVENT_USER_ID_FOREIGN, userId);

        long inserted = database.insert(DatabaseHelper.TABLE_EVENTS, null, values);
        this.close();

        return inserted>0 ? true:false;
    }

    // Get a single event
    public Event getEvent(int eventId) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EVENTS, new String[] {DatabaseHelper.COL_EVENT_ID,
                DatabaseHelper.COL_EVENT_NAME, DatabaseHelper.COL_EVENT_BUDGET,
                DatabaseHelper.COL_EVENT_EMERGENCY, DatabaseHelper.COL_EVENT_FROM,
                DatabaseHelper.COL_EVENT_TO, DatabaseHelper.COL_EVENT_USER_ID_FOREIGN},
                DatabaseHelper.COL_EVENT_ID + " = " + eventId + ";", null, null, null, null);

        cursor.moveToFirst();
        Event event = createEvent(cursor);

        cursor.close();
        this.close();

        return event;
    }

    // Get all events of an user
    public ArrayList<Event> getAllEvents(int userId) {
        ArrayList<Event> events = new ArrayList<>();

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EVENTS, new String[] {
                DatabaseHelper.COL_EVENT_ID, DatabaseHelper.COL_EVENT_NAME,
                DatabaseHelper.COL_EVENT_BUDGET, DatabaseHelper.COL_EVENT_EMERGENCY ,
                DatabaseHelper.COL_EVENT_FROM, DatabaseHelper.COL_EVENT_TO,
                DatabaseHelper.COL_EVENT_USER_ID_FOREIGN}, DatabaseHelper.COL_EVENT_USER_ID_FOREIGN
                + " = " + userId + ";", null, null, null, null);

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
    public boolean updateEvent(int eventId, Event event) {
        this.open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EVENT_NAME, event.getEventName());
        values.put(DatabaseHelper.COL_EVENT_BUDGET, event.getBudget());
        values.put(DatabaseHelper.COL_EVENT_EMERGENCY, event.getEmergencyNumber());
        values.put(DatabaseHelper.COL_EVENT_FROM, event.getFromDate());
        values.put(DatabaseHelper.COL_EVENT_TO, event.getToDate());

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

    private Event createEvent(Cursor cursor) {
        Log.e("createEvent", "cursor.getcount() "+cursor.getCount());
        int eventIdKey = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_ID));
        String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
        double budget = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_BUDGET));
        String emergencyNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_EMERGENCY));
        String from = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_FROM));
        String to = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_TO));
        int userIdForeign = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_USER_ID_FOREIGN));

        Event event = new Event(eventIdKey, eventName, budget, emergencyNumber, from, to, userIdForeign);

        return event;
    }

}
