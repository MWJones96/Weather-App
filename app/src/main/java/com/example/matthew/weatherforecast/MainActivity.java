package com.example.matthew.weatherforecast;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.json.JSONObject;

/**Main activity representing the weather forecast view
 *
 */
public class MainActivity extends AppCompatActivity
{
    WeatherTask w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Double lat;
        Double lon;

        //By default, will get the weather at the user's current location
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
        //If coming back from the Map activity, changes location based on the lat and lon values returned
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

    /**Run when the 5-day forecast button is pressed, will replace what is in the fragment box with the 5-day view
     *
     * @param view
     */
    public void FiveDayClick(View view)
    {
        FragmentManager f = getFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.replace(R.id.fragment_switch, w.f);
        t.addToBackStack(null);
        t.commit();
    }

    /**Run when the 2-day forecast button is pressed, will replace what is in the fragment box with the 2-day view
     *
     * @param view
     */
    public void TwoDayClick(View view)
    {
        FragmentManager f = getFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.replace(R.id.fragment_switch, w.t);
        t.addToBackStack(null);
        t.commit();
    }

    /**Creates an options menu with one option: Change Location, which opens the map activity
     *
     * @param menu - Our options menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**Checks which option is selected (in this case 1)
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.map:
                //Opens map activity to select location
                Intent i = new Intent(this, MapsActivity.class);
                startActivity(i);
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}