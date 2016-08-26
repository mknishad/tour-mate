package com.example.nishad.tourmate.model;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class Event {

    private int eventId;
    private String eventName;
    private double budget;
    private String emergencyNumber;
    private String fromDate;
    private String toDate;
    private int userIdForeign;

    // Constructor with id and foreign key
    public Event(int eventId, String eventName, double budget, String emergencyNumber, String fromDate, String toDate, int userIdForeign) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.budget = budget;
        this.emergencyNumber = emergencyNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.userIdForeign = userIdForeign;
    }

    // Constructor without id and foreign key
    public Event(String eventName, double budget, String emergencyNumber, String fromDate, String
            toDate) {
        this.eventName = eventName;
        this.budget = budget;
        this.emergencyNumber = emergencyNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getUserIdForeign() {
        return userIdForeign;
    }

    public void setUserIdForeign(int userIdForeign) {
        this.userIdForeign = userIdForeign;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }
}
