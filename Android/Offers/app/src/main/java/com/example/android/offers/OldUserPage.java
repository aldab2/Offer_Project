package com.example.android.offers;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OldUserPage extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    UserFragmentAdapter fragmentAdapter;
    public ArrayList<OffersInfoAdapter> electronicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        fragmentAdapter = new UserFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }
    public ArrayList<OffersInfoAdapter> getElectronicList(){
        return electronicList;
    }

    public class DataExctraction extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            electronicList = new ArrayList<>();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://sql150.main-hosting.eu:3306/u572021306_ytuju", "u572021306_uxyze", "Root@2018");
                //here project is database name, root is username and password is ics324
                Statement stmt1 = con.createStatement();
                String electronicQuery = "select proID,DeviceName,brand,normalPriceRange from Electronics e JOIN Offer o ON e.proID = o.ProductID GROUP by proID\n";
                ResultSet electronics = stmt1.executeQuery(electronicQuery);

                while (electronics.next()){
                    String name = electronics.getString(2);
                    String price = electronics.getString(4)+ " SR";
                    String brand = electronics.getString(3);
                   // electronicList.add(new OffersInfoAdapter(name,price,brand));
                }
                con.close();
            } catch (Exception e) {
                Log.wtf("FoodFragment", e.getMessage() + "XXXXXXXXXXXXXXXXXX");

            }

            return null;
        }


    }
}
