package com.example.nishad.tourmate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.adapter.EventListAdapter;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.EventsDataSource;
import com.example.nishad.tourmate.database.UsersDataSource;
import com.example.nishad.tourmate.model.User;

import java.util.ArrayList;

public class TravelEventsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lvTravelEvents;
    private EventsDataSource eventsDataSource;
    private UsersDataSource usersDataSource;
    private String email;
    private User user;
    private int userId;
    private String loginOrSignUp;
    private ArrayList<com.example.nishad.tourmate.model.Event> events;
    private ArrayAdapter<com.example.nishad.tourmate.model.Event> eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvTravelEvents = (ListView) findViewById(R.id.lvEventList);
        eventsDataSource = new EventsDataSource(this);
        usersDataSource = new UsersDataSource(this);

        loginOrSignUp = getIntent().getStringExtra(Constants.LOGIN_SIGNUP_ADD_EVENT);
        email = getIntent().getStringExtra(Constants.USER_EMAIL);
        user = usersDataSource.getUser(email);
        userId = user.getUserId();
        events = eventsDataSource.getAllEvents(userId);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TravelEventsActivity.this, AddEventActivity.class);
                intent.putExtra(Constants.USER_EMAIL, email);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        if (loginOrSignUp.equals("Login")) {
            if (events.size() == 0) {
                View parentLayout = findViewById(R.id.lvEventList);
                Snackbar.make(parentLayout, "No event added",
                        Snackbar.LENGTH_LONG).show();
            } else {
                // Populate events list view
                eventsAdapter = new EventListAdapter(this, events);
                lvTravelEvents.setAdapter(eventsAdapter);
            }
        } else if (loginOrSignUp.equals("SignUp")){
            /*email = getIntent().getStringExtra(Constants.USER_EMAIL);
            user = usersDataSource.getUser(email);
            userId = user.getUserId();
            events = eventsDataSource.getAllEvents(userId);*/

            // Populate event list view
            View parentLayout = findViewById(R.id.lvEventList);
            Snackbar.make(parentLayout, "No event added", Snackbar.LENGTH_LONG).show();
        } else if (loginOrSignUp.equals("AddEvent")) {
            if (events.size() == 0) {
                View parentLayout = findViewById(R.id.lvEventList);
                Snackbar.make(parentLayout, "No event added",
                        Snackbar.LENGTH_LONG).show();
            } else {
                // Populate events list view
                eventsAdapter = new EventListAdapter(this, events);
                lvTravelEvents.setAdapter(eventsAdapter);
            }
        }

        lvTravelEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TravelEventsActivity.this, MomentsActivity.class);
                intent.putExtra(Constants.USER_EMAIL, email);
                intent.putExtra("eventId", events.get(position).getEventId());
                startActivity(intent);
            }
        });

        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.travel_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
