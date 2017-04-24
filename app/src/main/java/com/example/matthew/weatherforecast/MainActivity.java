package com.example.matthew.weatherforecast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    WeatherTask w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Double lat;
        Double lon;

        if (getIntent().getExtras() == null)
        {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String provider = lm.getBestProvider(new Criteria(), false);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            Location loc = lm.getLastKnownLocation(provider);
            lat = loc.getLatitude();
            lon = loc.getLongitude();
        }
        else
        {
            lat = getIntent().getExtras().getDouble("lat", 0);
            lon = getIntent().getExtras().getDouble("lon", 0);
        }

        try {
            this.w = new WeatherTask(new JSONObject(new DownloadTask().execute("http://api.openweathermap.org/data/2.5/forecast?lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lon) + "&APPID=6486d182030fe7dd3d831fc0a831bac3").get()), this);
        }
        catch(Exception e){}
    }

    public void FiveDayClick(View view)
    {
        FragmentManager f = getFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.replace(R.id.fragment_switch, w.f);
        t.addToBackStack(null);
        t.commit();
    }

    public void TwoDayClick(View view)
    {
        FragmentManager f = getFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.replace(R.id.fragment_switch, w.t);
        t.addToBackStack(null);
        t.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.map:
                Intent i = new Intent(this, MapsActivity.class);
                startActivity(i);
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}