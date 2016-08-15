package com.example.nishad.tourmate.model;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class Event {

    private String eventName;
    private String budget;
    private String from;
    private String to;

    public Event() {
    }

    public Event(String eventName, String budget, String from, String to) {
        this.eventName = eventName;
        this.budget = budget;
        this.from = from;
        this.to = to;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
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
}
