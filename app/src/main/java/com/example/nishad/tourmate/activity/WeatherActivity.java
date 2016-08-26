package com.example.nishad.tourmate.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.interfaceapi.WeatherApi;
import com.example.nishad.tourmate.model.Main;
import com.example.nishad.tourmate.model.Weather;
import com.example.nishad.tourmate.model.WeatherCollectionResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    private WeatherApi weatherApi;
    private WeatherCollectionResponse weatherCollectionResponse;

    private java.util.List<com.example.nishad.tourmate.model.List> responseList;
    private ArrayList<Main> mains;
    private ArrayList<Double> temperaturesDouble;
    private ArrayList<Integer> temperaturesInteger;
    private ArrayList<Weather> weatherList;

    private TextView tvDay;
    private TextView tvDate;
    private TextView tvCityName;
    private TextView tvTemperature;
    private TextView tvCondition;
    private ImageView ivCondition;
    private TextView tvDayOneDate;
    private TextView tvDayOneTemp;
    private ImageView ivDayOneCond;
    private TextView tvDayTwoDate;
    private TextView tvDayTwoTemp;
    private ImageView ivDayTwoCond;
    private TextView tvDayThreeDate;
    private TextView tvDayThreeTemp;
    private ImageView ivDayThreeCond;
    private TextView tvDayFourDate;
    private TextView tvDayFourTemp;
    private ImageView ivDayFourCond;

    private String dayOfTheWeek;
    private String formattedDate;
    private String temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initializeViews();
        //initializeNetworkingLibrary();
        //getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            getData();
        }

        return true;
    }

    @Override
    protected void onStart() {
        initializeNetworkingLibrary();
        getData();

        super.onStart();
    }

    private void initializeViews() {
        responseList = new ArrayList<>();
        mains = new ArrayList<>();
        temperaturesDouble = new ArrayList<>();
        temperaturesInteger = new ArrayList<>();
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        tvTemperature = (TextView) findViewById(R.id.tvTemperature);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        ivCondition = (ImageView) findViewById(R.id.ivCondition);
        tvDayOneDate = (TextView) findViewById(R.id.tvDayOneDate);
        tvDayOneTemp = (TextView) findViewById(R.id.tvDayOneTemp);
        ivDayOneCond = (ImageView) findViewById(R.id.ivDayOneCond);
        tvDayTwoDate = (TextView) findViewById(R.id.tvDayTwoDate);
        tvDayTwoTemp = (TextView) findViewById(R.id.tvDayTwoTemp);
        ivDayTwoCond = (ImageView) findViewById(R.id.ivDayTwoCond);
        tvDayThreeDate = (TextView) findViewById(R.id.tvDayThreeDate);
        tvDayThreeTemp = (TextView) findViewById(R.id.tvDayThreeTemp);
        ivDayThreeCond = (ImageView) findViewById(R.id.ivDayThreeCond);
        tvDayFourDate = (TextView) findViewById(R.id.tvDayFourDate);
        tvDayFourTemp = (TextView) findViewById(R.id.tvDayFourTemp);
        ivDayFourCond = (ImageView) findViewById(R.id.ivDayFourCond);
    }

    private void getData() {
        Call<WeatherCollectionResponse> weatherCollectionResponseCall = weatherApi.getWeather
                (TravelEventsActivity.LATITUDE, TravelEventsActivity.LONGITUDE, Constants.API_KEY);
//        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        weatherCollectionResponseCall.enqueue(new Callback<WeatherCollectionResponse>() {
            @Override
            public void onResponse(Call<WeatherCollectionResponse> call, Response<WeatherCollectionResponse> response) {
                weatherCollectionResponse = response.body();

                responseList = weatherCollectionResponse.getList();
//                Toast.makeText(HomeActivity.this, ""+responseList.size(), Toast.LENGTH_SHORT).show();

                weatherList = (ArrayList<Weather>) responseList.get(0).getWeather();

                for (int i = 0; i < responseList.size(); i++) {
                    mains.add(responseList.get(i).getMain());
                }

                for (int i = 0; i < mains.size(); i++) {
                    temperaturesDouble.add(mains.get(i).getTemp() - 273.0);
                    temperaturesInteger.add(temperaturesDouble.get(i).intValue());
//                    Toast.makeText(HomeActivity.this, ""+temperaturesInteger.get(i), Toast.LENGTH_SHORT).show();
                }

                // value of day text view
                SimpleDateFormat df = new SimpleDateFormat("EEEE");
                Date d = new Date();
                dayOfTheWeek = df.format(d);
//        Toast.makeText(this, "Day " + dayOfTheWeek, Toast.LENGTH_SHORT).show();

                // value of date text view
                Calendar c = Calendar.getInstance();
                df = new SimpleDateFormat("dd-MMM-yyyy");
                formattedDate = df.format(c.getTime());
//        Toast.makeText(this, "Date " + formattedDate, Toast.LENGTH_SHORT).show();

                // value of temperature text view
                temperature = "" + temperaturesInteger.get(0);

                // value of condition text view
                String description = weatherList.get(0).getDescription();

                // set values
                tvDay.setText(dayOfTheWeek);
                tvDate.setText(formattedDate);
                tvCityName.setText(TravelEventsActivity.CITY);
                tvTemperature.setText(temperature);
                tvCondition.setText(description);

                // Set forecast dates
                // Day one
                Date dt = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(dt);
                cal.add(Calendar.DATE, 1);
                //dt = cal.getTime();
                formattedDate = df.format(cal.getTime());
                tvDayOneDate.setText(formattedDate);
                // Day two
                cal.add(Calendar.DATE, 1);
                formattedDate = df.format(cal.getTime());
                tvDayTwoDate.setText(formattedDate);
                // Day three
                cal.add(Calendar.DATE, 1);
                formattedDate = df.format(cal.getTime());
                tvDayThreeDate.setText(formattedDate);
                // Day four
                cal.add(Calendar.DATE, 1);
                formattedDate = df.format(cal.getTime());
                tvDayFourDate.setText(formattedDate);

                // Set forecast temperatures
                tvDayOneTemp.setText(temperaturesInteger.get(8) + "°C");
                tvDayTwoTemp.setText(temperaturesInteger.get(16) + "°C");
                tvDayThreeTemp.setText(temperaturesInteger.get(24) + "°C");
                tvDayFourTemp.setText(temperaturesInteger.get(32) + "°C");

                // set icons
                Log.e("onResponse: ", "weatherlist " + weatherList.size() + " " + weatherList.get(0)
                        .getIcon());
                ivCondition.setImageResource(getIconID(weatherList.get(0).getIcon()));

                weatherList = (ArrayList<Weather>) responseList.get(8).getWeather();
                Log.e("onResponse: ", "weatherlist " + weatherList.size() + " " + weatherList.get(0)
                        .getIcon());
                ivDayOneCond.setImageResource(getIconID(weatherList.get(0).getIcon()));

                weatherList = (ArrayList<Weather>) responseList.get(16).getWeather();
                Log.e("onResponse: ", "weatherlist " + weatherList.size() + " " + weatherList.get(0)
                        .getIcon());
                ivDayTwoCond.setImageResource(getIconID(weatherList.get(0).getIcon()));

                weatherList = (ArrayList<Weather>) responseList.get(24).getWeather();
                Log.e("onResponse: ", "weatherlist " + weatherList.size() + " " + weatherList.get(0)
                        .getIcon());
                ivDayThreeCond.setImageResource(getIconID(weatherList.get(0).getIcon()));

                weatherList = (ArrayList<Weather>) responseList.get(32).getWeather();
                Log.e("onResponse: ", "weatherlist " + weatherList.size() + " " + weatherList.get(0)
                        .getIcon());
                ivDayFourCond.setImageResource(getIconID(weatherList.get(0).getIcon()));
            }

            @Override
            public void onFailure(Call<WeatherCollectionResponse> call, Throwable t) {
                Toast.makeText(WeatherActivity.this, " getData failed", Toast.LENGTH_SHORT).show();
                Log.e("getdata", "onFailure: " + t.getMessage());
            }
        });

        Log.e("url ", "getData " + weatherCollectionResponseCall.request().url());
    }

    private void initializeNetworkingLibrary() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    private int getIconID(String s)
    {
        Resources res = getResources();
        int resID = res.getIdentifier("i"+s,"drawable", getPackageName());
        return resID;
    }

}
