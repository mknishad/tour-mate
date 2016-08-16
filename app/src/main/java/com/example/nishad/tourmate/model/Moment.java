package com.example.nishad.tourmate.model;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class Moment {

    private int momentId;
    private String momentType;
    private String detail;
    private double cost;
    private String imagePath;
    private int eventIdForeign;

    public Moment() {
    }

    // Constructor for status moment without id and foreign key
    public Moment(String momentType, String detail) {
        this.momentType = momentType;
        this.detail = detail;
    }

    // Constructor for status moment with id and foreign key
    public Moment(int momentId, String momentType, String detail, int eventIdForeign) {
        this.momentId = momentId;
        this.momentType = momentType;
        this.detail = detail;
        this.eventIdForeign = eventIdForeign;
    }

    // Constructor for expense moment without id and foreign key
    public Moment(String detail, String momentType, double cost) {
        this.detail = detail;
        this.momentType = momentType;
        this.cost = cost;
    }

    // Constructor for expense moment with id and foreign key
    public Moment(int momentId, String detail, String momentType, double cost, int eventIdForeign) {
        this.momentId = momentId;
        this.detail = detail;
        this.momentType = momentType;
        this.cost = cost;
        this.eventIdForeign = eventIdForeign;
    }

    // Constructor for photo moment without id and foreign key
    public Moment(String imagePath, String detail, String momentType) {
        this.imagePath = imagePath;
        this.detail = detail;
        this.momentType = momentType;
    }

    // Constructor for photo moment with id and foreign key
    public Moment(int momentId, String imagePath, String detail, String momentType, int eventIdForeign) {
        this.momentId = momentId;
        this.imagePath = imagePath;
        this.detail = detail;
        this.momentType = momentType;
        this.eventIdForeign = eventIdForeign;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    public String getMomentType() {
        return momentType;
    }

    public void setMomentType(String momentType) {
        this.momentType = momentType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
