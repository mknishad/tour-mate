package com.example.nishad.tourmate.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.EventsDataSource;
import com.example.nishad.tourmate.database.UsersDataSource;
import com.example.nishad.tourmate.model.Event;
import com.example.nishad.tourmate.model.User;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private EditText etPlace;
    private EditText etBudget;
    private EditText etEmergency;
    public static TextView tvFromDate;
    public static TextView tvToDate;
    private EventsDataSource eventsDataSource;
    private UsersDataSource usersDataSource;
    private String userEmail;
    private User user;
    public static boolean date = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        findView();
        usersDataSource = new UsersDataSource(this);
        eventsDataSource = new EventsDataSource(this);
        userEmail = getIntent().getStringExtra(Constants.USER_EMAIL);
        user = usersDataSource.getUser(userEmail);
    }

    private void findView() {
        etPlace = (EditText) findViewById(R.id.etPlace);
        etBudget = (EditText) findViewById(R.id.etBudget);
        etEmergency = (EditText) findViewById(R.id.etEmergencyNumber);
        tvFromDate = (TextView) findViewById(R.id.tvFromDate);
        tvToDate = (TextView) findViewById(R.id.tvToDate);
    }

    public void addEvent(View view) {
        String eventName = etPlace.getText().toString();
        String eventBudget = etBudget.getText().toString();
        String emergency = etEmergency.getText().toString();
        String fromDate = tvFromDate.getText().toString();
        String toDate = tvToDate.getText().toString();
        if (!eventName.equals("Enter event") && !eventBudget.equals("Enter budget") && !emergency
                .equals("Enter emergency number") && !fromDate.equals("Select date") && !toDate.equals("Select date")) {
            double b;
            try {
                b = Double.parseDouble(eventBudget);
            } catch (Exception e) {
                Toast.makeText(this, "Enter double value for budget", Toast.LENGTH_SHORT).show();
                etBudget.setText("");
                return;
            }
            Event event = new Event(eventName, b, emergency, fromDate, toDate);
            eventsDataSource.addEvent(user.getUserId(), event);
            Toast.makeText(this, eventName+" added successfully", Toast.LENGTH_SHORT).show();

            // Go to events activity
            Intent intent = new Intent(this, TravelEventsActivity.class);
            intent.putExtra(Constants.LOGIN_SIGNUP_ADD_EVENT, "AddEvent");
            intent.putExtra(Constants.USER_EMAIL, userEmail);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Enter information properly", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view) {
        this.finish();
    }

    public void setDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            if (!date) {
                tvFromDate.setText(day+"/"+month+"/"+year);
                date = !date;
            } else {
                tvToDate.setText(day+"/"+month+"/"+year);
                date = !date;
            }
        }
    }

}
