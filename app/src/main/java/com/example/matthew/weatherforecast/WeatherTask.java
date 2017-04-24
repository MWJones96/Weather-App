package com.example.matthew.weatherforecast;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.Image;
import android.support.annotation.IntDef;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by Matthew on 22/04/2017.
 */

public class WeatherTask
{
    FiveDayForecast f;
    TwoDayForecast t;
    JSONObject obj;
    Activity act;

    public WeatherTask(JSONObject obj, Activity act)
    {
        this.f = new FiveDayForecast();
        this.t = new TwoDayForecast();
        this.obj = obj;
        this.act = act;
    }
}