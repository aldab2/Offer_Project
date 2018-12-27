package com.example.android.offers;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ArrayList<OffersInfoAdapter> OffersInfoAdapters = new ArrayList<OffersInfoAdapter>();

        //OffersInfoAdapters.add(new OffersInfoAdapter(getString(R.string.type), getString(R.string.price), getString(R.string.location)));

        OffersAdapter adapter = new OffersAdapter(getActivity(), OffersInfoAdapters, R.color.blue);

        ListView listView = rootView.findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }

}




