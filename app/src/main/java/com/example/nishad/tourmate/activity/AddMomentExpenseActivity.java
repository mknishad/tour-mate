package com.example.nishad.tourmate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.ExpenseMomentDataSource;
import com.example.nishad.tourmate.model.ExpenseMoment;

public class AddMomentExpenseActivity extends AppCompatActivity {

    private ExpenseMomentDataSource expenseMomentDataSource;
    private int eventId;
    private EditText etCostTitle;
    private EditText etCostAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moment_cost);

        expenseMomentDataSource = new ExpenseMomentDataSource(this);
        eventId = getIntent().getIntExtra(Constants.EVENT_ID, 0);

        findView();
    }

    private void findView() {
        etCostTitle = (EditText) findViewById(R.id.etCostTitle);
        etCostAmount = (EditText) findViewById(R.id.etCostAmount);
    }

    public void save(View view) {
        String title = etCostTitle.getText().toString();
        String amount = etCostAmount.getText().toString();

        if (!title.equals("") && !amount.equals("")) {
            boolean inserted = expenseMomentDataSource.addExpenseMoment(new ExpenseMoment(title,
                    Double.parseDouble(amount), eventId));
            if (inserted) {
                Toast.makeText(this, "Your moment inserted successfully", Toast.LENGTH_SHORT)
                        .show();
                this.finish();
            } else {
                Toast.makeText(this, "Moment insertion failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter inputs properly", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view) {
        this.finish();
    }
}
