package com.example.loadersapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TemplateAdapterModel extends ArrayAdapter<DataContainerModel> {
    public TemplateAdapterModel(Context context, ArrayList<DataContainerModel> data) {
        super(context, 0, data);
    }

    ;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListViewItem = convertView;
        if (ListViewItem == null) {
            ListViewItem = LayoutInflater.from(getContext()).inflate(R.layout.template, parent, false);
        }
        ;

        //positioning the data in the right place
        DataContainerModel currentData = getItem(position);
        //play with the data now
        TextView MagStrength = (TextView) ListViewItem.findViewById(R.id.mag);
        MagStrength.setText(currentData.getMagStrength());
        //done with the mag Strength
        TextView Place = (TextView) ListViewItem.findViewById(R.id.Place);
        Place.setText(currentData.getPlace());
        //end playing with the place data
        //finally play with the time
        Date QuakesDate = new Date(currentData.getTime());
        TextView Time = (TextView) ListViewItem.findViewById(R.id.time);
        Time.setText(FormattedTime(QuakesDate));
        //end playing with the date
        return ListViewItem;
    }

    ;

    String FormattedTime(Date date) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat("LLL dd ,yyy 'at' h:mm a");
        return simpleDateFormate.format(date);
    }

    ;

};
