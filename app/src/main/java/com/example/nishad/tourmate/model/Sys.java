package com.example.nishad.tourmate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nishad on 8/26/2016.
 */
public class Sys {

    @SerializedName("population")
    @Expose
    private Integer population;

    /**
     *
     * @return
     * The population
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     * The population
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

}
