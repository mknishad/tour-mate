package com.example.nishad.tourmate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.model.ExpenseMoment;

import java.util.ArrayList;

/**
 * Created by Nishad on 8/25/2016.
 */
public class ExpenseListAdapter extends ArrayAdapter<ExpenseMoment> {

    Context context;
    ArrayList<ExpenseMoment> expenseMoments;

    public ExpenseListAdapter(Context context, ArrayList<ExpenseMoment> expenseMoments) {
        super(context, R.layout.custom_expense_row, expenseMoments);
        this.context = context;
        this.expenseMoments = expenseMoments;
    }

    private static class ViewHolder {
        TextView tvExpenseTitle;
        TextView tvExpenseAmount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_expense_row, null, true);

            // Initialize variables
            viewHolder.tvExpenseTitle = (TextView) convertView.findViewById(R.id.tvExpenseTitle);
            viewHolder.tvExpenseAmount = (TextView) convertView.findViewById(R.id.tvExpenseAmount);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the views
        viewHolder.tvExpenseTitle.setText(expenseMoments.get(position).getName());
        viewHolder.tvExpenseAmount.setText(String.valueOf(expenseMoments.get(position).getAmount()));

        return convertView;
    }
}
