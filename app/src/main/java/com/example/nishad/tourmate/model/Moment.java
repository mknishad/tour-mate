package com.example.nishad.tourmate.model;

/**
 * Created by Nishad on 15-Aug-16.
 */
public class Moment {

    private String momentType;
    private String detail;
    private double cost;
    private String imagePath;

    public Moment() {
    }

    public Moment(String momentType, String detail) {
        this.momentType = momentType;
        this.detail = detail;
    }

    public Moment(String detail, String momentType, double cost) {
        this.detail = detail;
        this.momentType = momentType;
        this.cost = cost;
    }

    public Moment(String imagePath, String detail, String momentType) {
        this.imagePath = imagePath;
        this.detail = detail;
        this.momentType = momentType;
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
