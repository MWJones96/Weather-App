package com.example.matthew.weatherforecast;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matthew on 21/04/2017.
 */

public class DownloadTask extends AsyncTask<String, Void, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        String result = "";
        URL url = null;
        HttpURLConnection conn = null;

        try
        {
            url = new URL(params[0]);
            conn = (HttpURLConnection)url.openConnection();
            InputStreamReader i = new InputStreamReader(conn.getInputStream());
            int data = i.read();

            while(data != -1)
            {
                result += (char)data;
                data = i.read();
            }

            return result;
        }
        catch(Exception e){ e.printStackTrace(); }

        return null;
    }

}