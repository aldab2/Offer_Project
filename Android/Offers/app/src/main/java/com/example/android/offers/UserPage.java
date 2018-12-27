package com.example.android.offers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;
//import com.squareup.picasso.Picasso;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserPage extends AppCompatActivity {

    private static ArrayList<OffersInfoAdapter> foodItemArrayList;  //Lists food items Array
    private static ArrayList<OffersInfoAdapter> electronicItemArrayList;//Lists electronics items Array
    private static ArrayList<OffersInfoAdapter>[] allItemsList;// 1--> Food 0--> electronics 2--> Games(not yet set)
    //private MyAppAdapter myAppAdapter; //Array Adapter
    private static ListView listView; // Listview
    private boolean success = false; // boolean
    public static ArrayList<String> sth;// To be filled
    private static SwipeRefreshLayout swipeLayout0;
    private static SwipeRefreshLayout swipeLayout1;
    public static SyncData orderData;
    public static RefreshData[] refreshData;
    public FloatingActionButton searchButton;
    public static EditText searchEditText;
    public static LinearLayout searchLayout;
    public RadioGroup searchRadioGroup;
    private MySQLConnector connectionClass; //Connection Class Variable
    ViewPager viewPager;
    TabLayout tabLayout;
    UserFragmentAdapter fragmentAdapter;
    static OffersAdapter adapter = null;
    final String TAG = "abc";
    public static int refreshCnt = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_user_page);


        listView = (ListView) findViewById(android.R.id.list); //Listview Declaration
        connectionClass = new MySQLConnector(); // Connection Class Initialization

        allItemsList = new ArrayList[5];
        //foodItemArrayList = new ArrayList<OffersInfoAdapter>(); // Arraylist Initialization
        allItemsList[1] = new ArrayList<OffersInfoAdapter>(); // Food Initialization
        //electronicItemArrayList = new ArrayList<OffersInfoAdapter>();
        allItemsList[0] = new ArrayList<OffersInfoAdapter>(); // Electronic Initialization

        viewPager = findViewById(R.id.view_pager);


        fragmentAdapter = new UserFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        // Calling Async Task
        orderData = new SyncData();
        orderData.execute("");
        refreshData= new RefreshData[50];
        for(int i=0;i<refreshData.length;i++)
            refreshData[i] = new RefreshData();

     /*   swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });*/
       /* swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);*/





        searchRadioGroup = (RadioGroup) findViewById(R.id.rgSearch) ;
        searchEditText = (EditText) findViewById(R.id.etSearch) ;
        searchButton = (FloatingActionButton) findViewById(R.id.search_button);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchEditText.isShown()){
                    searchRadioGroup.setVisibility(View.GONE);
                    searchEditText.setVisibility(View.GONE);

                    viewPager.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);
                    searchButton.setImageResource(R.drawable.sharp_search_black_36);

                }
                else {
                    searchRadioGroup.setVisibility(View.VISIBLE);
                    searchEditText.setVisibility(View.VISIBLE);                    viewPager.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.GONE);
                    searchButton.setImageResource(R.drawable.back_arrow);
                }

            }
        });

    }


    // Async Task has three overrided methods,
    private class SyncData extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;


        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(UserPage.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try {
                //Connection conn = connectionClass.CONN(); //Connection Object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://sql150.main-hosting.eu:3306/u572021306_ytuju", "u572021306_uxyze", "Root@2018");
                if (conn == null) {
                    success = false;
                } else {
                    // Change below query according to your own database.
                    String electronicQuery = "SELECT e.DeviceName,o.normalPriceRange,o.discountPrice,o.startDate,o.enddate,e.DeviceDescription,e.color,e.brand,c.name from Electronics e join Offer o JOIN Product p JOIN Company c on o.ProductID = p.proID and  e.proID = p.proID and c.compID=p.compID";
                    String foodQuery = "SELECT f.name,o2.normalPriceRange,o2.discountPrice,o2.startDate,o2.enddate,c2.name from Food f JOIN Offer o2 JOIN Product p2 JOIN Company c2 on o2.ProductID = p2.proID and  f.proID = p2.proID and c2.compID=p2.compID";
                    Statement stmt1 = conn.createStatement();
                    Statement stmt2 = conn.createStatement();
                    ResultSet foodRs = stmt1.executeQuery(foodQuery);
                    ResultSet electronicRs = stmt2.executeQuery(electronicQuery);
                    if (foodRs != null && electronicRs != null) // if resultset not null, I add items to itemArraylist using class created
                    {
                        while (foodRs.next() || electronicRs.next()) {
                            try {
                                if (!foodRs.isAfterLast()) {
                                    allItemsList[1].add(new OffersInfoAdapter(foodRs.getString(1), foodRs.getString(2), foodRs.getString(3), foodRs.getDate(4), foodRs.getDate(5), foodRs.getString(6)));
                                }
                                if (!electronicRs.isAfterLast()) {
                                    allItemsList[0].add(new OffersInfoAdapter(electronicRs.getString(1), electronicRs.getString(2), electronicRs.getString(3), electronicRs.getDate(4), electronicRs.getDate(5), electronicRs.getString(6), electronicRs.getString(7), electronicRs.getString(8), electronicRs.getString(9)));
                                }
                                // itemArrayList.add(new OffersInfoAdapter(rs.getString("Fname"),rs.getString("Phone"),rs.getString("Mail")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        success = true;
                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my listview
        {
            progress.dismiss();
            Toast.makeText(UserPage.this, msg + "....Loading Data", Toast.LENGTH_LONG).show();
            if (success == false) {
            } else {
                try {
                    adapter.notifyDataSetChanged();
                    fragmentAdapter.notifyDataSetChanged();

                } catch (Exception ex) {

                }

            }
            swipeLayout0.setRefreshing(false);
            swipeLayout1.setRefreshing(false);

        }
    }

    /*public class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
    {
        public class ViewHolder {
            TextView textName;
            //  ImageView imageView;
        }

        public List<OffersInfoAdapter> parkingList;

        public Context context;
        ArrayList<OffersInfoAdapter> arraylist;

        private MyAppAdapter(List<OffersInfoAdapter> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<OffersInfoAdapter>();
            //arraylist.addAll(parkingList);
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
        {

            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.demo_list_conetent, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
                //0 viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // here setting up names and images
            // viewHolder.textName.setText(parkingList.get(position).getName()+"\t"+parkingList.get(position).getMobileNumber()+"\t"+parkingList.get(position).getPlaceLocation());
            // Picasso.with(context).load("http://"+parkingList.get(position).getImg()).into(viewHolder.imageView);

            return rowView;
        }
    }*/

    public static class DemoFoodFragment extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

          /*  View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            final ArrayList<OffersInfoAdapter> OffersInfoAdapters = new ArrayList<OffersInfoAdapter>();

            OffersInfoAdapters.add(new OffersInfoAdapter(getString(R.string.type), getString(R.string.price), getString(R.string.location)));

            OffersAdapter adapter = new OffersAdapter(getActivity(), OffersInfoAdapters, R.color.blue);

            ListView listView = rootView.findViewById(android.R.id.list);
            listView.setAdapter(adapter);

            return rootView;*/


            inflater = getLayoutInflater();
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            final ArrayList<OffersInfoAdapter> OffersInfoAdapters = new ArrayList<OffersInfoAdapter>();


            adapter = new OffersAdapter(getActivity(), allItemsList[1], R.color.blue);

            listView = rootView.findViewById(android.R.id.list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            swipeLayout1 = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
            swipeLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //swipeLayout.setRefreshing(tru);
                    int currentCnt= refreshCnt;
                    refreshData[refreshCnt].execute();


                    return;
                }

            });
            // listView.deferNotifyDataSetChanged();

            return rootView;
        }
    }


    public static class DemoElectronicFragment extends Fragment {
        View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            inflater = getLayoutInflater();
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            final ArrayList<OffersInfoAdapter> OffersInfoAdapters = new ArrayList<OffersInfoAdapter>();

            adapter = new OffersAdapter(getActivity(), allItemsList[0], R.color.blue);

            listView = rootView.findViewById(android.R.id.list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            swipeLayout0 = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
            swipeLayout0.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //swipeLayout.setRefreshing(tru);
            int currentCnt= refreshCnt;
                    refreshData[refreshCnt].execute();


                    return;
                }

            });


            return rootView;
        }


    }

    public class RefreshData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String msg = new String();
            {
                try {


                    //Connection conn = connectionClass.CONN(); //Connection Object
                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://sql150.main-hosting.eu:3306/u572021306_ytuju", "u572021306_uxyze", "Root@2018");
                    if (conn == null) {
                        success = false;
                    } else {
                        // Change below query according to your own database.
                        String electronicQuery = "SELECT e.DeviceName,o.normalPriceRange,o.discountPrice,o.startDate,o.enddate,e.DeviceDescription,e.color,e.brand,c.name from Electronics e join Offer o JOIN Product p JOIN Company c on o.ProductID = p.proID and  e.proID = p.proID and c.compID=p.compID";
                        String foodQuery = "SELECT f.name,o2.normalPriceRange,o2.discountPrice,o2.startDate,o2.enddate,c2.name from Food f JOIN Offer o2 JOIN Product p2 JOIN Company c2 on o2.ProductID = p2.proID and  f.proID = p2.proID and c2.compID=p2.compID";
                        Statement stmt1 = conn.createStatement();
                        Statement stmt2 = conn.createStatement();
                        ResultSet foodRs = stmt1.executeQuery(foodQuery);
                        ResultSet electronicRs = stmt2.executeQuery(electronicQuery);
                        allItemsList[0].clear();
                        allItemsList[1].clear();
                        if (foodRs != null && electronicRs != null) // if resultset not null, I add items to itemArraylist using class created
                        {
                            while (foodRs.next() || electronicRs.next()) {
                                try {
                                    if (!foodRs.isAfterLast())
                                        allItemsList[1].add(new OffersInfoAdapter(foodRs.getString(1), foodRs.getString(2), foodRs.getString(3), foodRs.getDate(4), foodRs.getDate(5), foodRs.getString(6)));
                                    if (!electronicRs.isAfterLast())
                                        allItemsList[0].add(new OffersInfoAdapter(electronicRs.getString(1), electronicRs.getString(2), electronicRs.getString(3), electronicRs.getDate(4), electronicRs.getDate(5), electronicRs.getString(6), electronicRs.getString(7), electronicRs.getString(8), electronicRs.getString(9)));

                                    // itemArrayList.add(new OffersInfoAdapter(rs.getString("Fname"),rs.getString("Phone"),rs.getString("Mail")));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            msg = "Found";
                            success = true;
                        } else {
                            msg = "No Data found!";
                            success = false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));
                    msg = writer.toString();
                    success = false;
                }


                return null;
            }



        }


        @Override
        protected void onPostExecute(Void aVoid) {
            swipeLayout0.setRefreshing(false);
            Log.wtf(TAG,"Refreshed Page");
            swipeLayout1.setRefreshing(false);
            refreshCnt++;
            adapter.notifyDataSetChanged();

        }
    }


}
