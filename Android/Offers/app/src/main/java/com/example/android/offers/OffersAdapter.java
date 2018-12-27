package com.example.android.offers;



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

public class OffersAdapter extends ArrayAdapter<OffersInfoAdapter>  implements Filterable{

    private int colorResourceId;

    public OffersAdapter(@NonNull Context context, @NonNull List<OffersInfoAdapter> info, int ColorId) {
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

        OffersInfoAdapter currentInfo = getItem(position);

        TextView textViewName =  listItemView.findViewById(R.id.text_name);
        textViewName.setText(currentInfo.getName());

        TextView textViewNumber =  listItemView.findViewById(R.id.text_number);
        textViewNumber.setText(currentInfo.getDiscountPrice());

        TextView textViewLocation = listItemView.findViewById(R.id.text_location);
        textViewLocation.setText(currentInfo.getCompany());

        ImageView imageViewimage = listItemView.findViewById(R.id.imageId);
       /* if(currentInfo.hasImage()) {
            imageViewimage.setImageResource(currentInfo.getImage());
            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            imageViewimage.setVisibility(View.VISIBLE);
        }else {
            imageViewimage.setVisibility(View.GONE);

        }
*/
        View textContainer = listItemView.findViewById(R.id.container);
        int color = ContextCompat.getColor(getContext(),colorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }



}

