package com.example.android.offers;


import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.*;

/**
 * Created by aldab2 on 30/11/18.
 */
/*

{
    // Change below settings as per your database ip and credentials
    String ip = "182.50.133.109"; // if you have to add port then it would be like .i.e. 182.50.133.109:1433
    String db = "DatabaseTest"; //Name of Database
    String un = "saadmin"; //Database user
    String password = "123456789"; //Database Password

    @SuppressLint("NewApi")
    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";" + "databaseName=" + db + ";user=" + un + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }

 */
public class MySQLConnector {
    String ip = "sql150.main-hosting.eu:3306"; // if you have to add port then it would be like .i.e. 182.50.133.109:1433
    String db = "u572021306_ytuju"; //Name of Database
    String un = "u572021306_uxyze"; //Database user
    String password = "Root@2018"; //Database Password
    Connection con;
    String Tag="abc";

    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // ConnURL = "jdbc:mysql://"+ip+"/"+db,un,password"";


             conn=DriverManager.getConnection(
                    "jdbc:mysql://"+ip+"/"+db,un,password);
            Log.wtf(Tag,"jdbc:mysql://"+ip+"/"+db);

        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }



}
