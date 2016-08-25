package com.example.nishad.tourmate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.adapter.ExpenseListAdapter;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.EventsDataSource;
import com.example.nishad.tourmate.database.ExpenseMomentDataSource;
import com.example.nishad.tourmate.model.Event;
import com.example.nishad.tourmate.model.ExpenseMoment;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {

    private EventsDataSource eventsDataSource;
    private ExpenseMomentDataSource expenseMomentDataSource;
    private Event event;
    private int eventId;
    private String eventName;
    private String eventFrom;
    private String eventTo;
    private double eventBudget;
    private TextView tvEventName;
    private TextView tvEventFrom;
    private TextView tvEventTo;
    private TextView tvEventBudgetAmount;
    private ListView lvExpenseList;
    private ExpenseListAdapter expenseListAdapter;
    private ArrayList<ExpenseMoment> expenseMoments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        eventId = getIntent().getIntExtra(Constants.EVENT_ID, 0);
        eventsDataSource = new EventsDataSource(this);
        expenseMomentDataSource = new ExpenseMomentDataSource(this);

        findView();
    }

    @Override
    protected void onStart() {
        getEvent();
        getExpenseMoments();
        populateExpenseMomentActivity();
        super.onStart();
    }

    private void populateExpenseMomentActivity() {
        tvEventName.setText(eventName);
        tvEventFrom.setText(eventFrom);
        tvEventTo.setText(eventTo);
        tvEventBudgetAmount.setText("$"+eventBudget);
        Log.e("EXPENSE_ACTIVITY", "populate" + expenseMoments.toString() );
        expenseListAdapter = new ExpenseListAdapter(this, expenseMoments);
        lvExpenseList.setAdapter(expenseListAdapter);
    }

    private void getExpenseMoments() {
        expenseMoments = expenseMomentDataSource.getAllPhotoMoments(eventId);
    }

    private void getEvent() {
        event = eventsDataSource.getEvent(eventId);
        eventName = event.getEventName();
        eventFrom = event.getFromDate();
        eventTo = event.getToDate();
        eventBudget = event.getBudget();
    }

    private void findView() {
        tvEventName = (TextView) findViewById(R.id.tvExpenseEventName);
        tvEventFrom = (TextView) findViewById(R.id.tvExpenseEventFrom);
        tvEventTo = (TextView) findViewById(R.id.tvExpenseEventTo);
        tvEventBudgetAmount = (TextView) findViewById(R.id.tvExpenseEventBudgetAmount);
        lvExpenseList = (ListView) findViewById(R.id.lvExpenseList);
    }
}
