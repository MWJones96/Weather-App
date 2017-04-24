package com.example.matthew.weatherforecast;

import android.app.Activity;
import org.json.JSONObject;

/**
 * Created by Matthew on 22/04/2017.
 */

/**Stores the two fragments and the JSONObject to be used by the other classes
 *
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