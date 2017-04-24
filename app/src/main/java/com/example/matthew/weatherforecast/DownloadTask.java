package com.example.matthew.weatherforecast;

import android.os.AsyncTask;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matthew on 21/04/2017.
 */

public class DownloadTask extends AsyncTask<String, Void, String>
{
    /**Downloads the JSON file specified by the URL in params
     *
     * @param params - URL of JSON file
     * @return String representation of JSON file
     */
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

            //Reads everything
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