package com.example.nishad.tourmate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nishad.tourmate.R;

import java.util.ArrayList;

/**
 * Created by Nishad on 8/24/2016.
 */
public class CustomListAdapterDialog extends BaseAdapter {

    private ArrayList<String> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapterDialog(Context context, ArrayList<String> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_dialog, null);
            holder = new ViewHolder();
            holder.tvOption = (TextView) convertView.findViewById(R.id.tvOption);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvOption.setText(listData.get(position));

        return convertView;
    }

    static class ViewHolder {
        TextView tvOption;
    }

}
