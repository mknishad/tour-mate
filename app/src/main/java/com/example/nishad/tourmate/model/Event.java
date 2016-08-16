package com.example.nishad.tourmate.model;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class Event {

    private int eventId;
    private String eventName;
    private double budget;
    private String from;
    private String to;
    private int userIdForeign;

    // Constructor with id and foreign key
    public Event(int eventId, String eventName, double budget, String from, String to, int userIdForeign) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.budget = budget;
        this.from = from;
        this.to = to;
        this.userIdForeign = userIdForeign;
    }

    // Constructor without id and foreign key
    public Event(String eventName, double budget, String from, String to) {
        this.eventName = eventName;
        this.budget = budget;
        this.from = from;
        this.to = to;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getUserIdForeign() {
        return userIdForeign;
    }

    public void setUserIdForeign(int userIdForeign) {
        this.userIdForeign = userIdForeign;
    }
}
