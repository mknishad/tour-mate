package com.example.nishad.tourmate.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.model.PhotoMoment;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Nishad on 8/25/2016.
 */
public class PhotoMomentAdapter extends ArrayAdapter<PhotoMoment> {
    Context context;
    ArrayList<PhotoMoment> data = new ArrayList<>();

    public PhotoMomentAdapter(Context context, ArrayList<PhotoMoment> data) {
        super(context, R.layout.custom_moment_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.custom_moment_row, parent, false);

            holder = new ImageHolder();
            holder.tvMomentDetails = (TextView) row.findViewById(R.id.tvMomentDetails);
            holder.ivMomentImage = (ImageView) row.findViewById(R.id.ivMomentImage);
            row.setTag(holder);
        } else {
            holder = (ImageHolder) row.getTag();
        }

        PhotoMoment photoMoment = data.get(position);
        holder.tvMomentDetails.setText(photoMoment.getName());
        //convert byte to bitmap take from contact class

        byte[] outImage = photoMoment.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.ivMomentImage.setImageBitmap(theImage);

        return row;
    }

    static class ImageHolder {
        TextView tvMomentDetails;
        ImageView ivMomentImage;
    }

}
