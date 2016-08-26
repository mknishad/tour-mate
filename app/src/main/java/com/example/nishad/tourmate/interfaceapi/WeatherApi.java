package com.example.nishad.tourmate.interfaceapi;

import com.example.nishad.tourmate.model.WeatherCollectionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nishad on 8/26/2016.
 */

public interface WeatherApi {

    //    String get = "data/2.5/forecast?lat={lat}&lon={lon}&APPID=e5cd837473d78b88cd21ef4e3e43e5dd";
    @GET("data/2.5/forecast?")
    Call<WeatherCollectionResponse> getWeather(
            @Query("lat") int lat,
            @Query("lon") int lon,
            @Query("appid") String appid);

}

