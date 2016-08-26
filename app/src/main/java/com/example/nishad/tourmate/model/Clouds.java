package com.example.nishad.tourmate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nishad on 8/26/2016.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

    /**
     * @return The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * @param all The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

}
