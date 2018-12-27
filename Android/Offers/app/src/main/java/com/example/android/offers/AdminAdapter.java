package com.example.android.offers;

/**
 * Created by aldab2 on 11/12/18.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdminAdapter extends ArrayAdapter<AdminInfoAdapter> implements Filterable {

    private int colorResourceId;

    public AdminAdapter(@NonNull Context context, @NonNull List<AdminInfoAdapter> info, int ColorId) {
        super(context, 0, info);
        colorResourceId = ColorId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_row, parent, false);
        }

        AdminInfoAdapter currentInfo = getItem(position);

        TextView textViewName =  listItemView.findViewById(R.id.text_name);
        textViewName.setText(currentInfo.getName());

        TextView textViewNumber =  listItemView.findViewById(R.id.text_number);
        textViewNumber.setText(currentInfo.getNormalPriceRange());

        TextView textViewLocation = listItemView.findViewById(R.id.text_location);
        textViewLocation.setText(currentInfo.getDiscountPriceRange());

        ImageView imageViewimage = listItemView.findViewById(R.id.imageId);
      /*  if(currentInfo.hasImage()) {
            imageViewimage.setImageResource(currentInfo.getImage());
            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            imageViewimage.setVisibility(View.VISIBLE);
        }else {
            imageViewimage.setVisibility(View.GONE);

        }*/

        View textContainer = listItemView.findViewById(R.id.container);
        int color = ContextCompat.getColor(getContext(),colorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }



}


