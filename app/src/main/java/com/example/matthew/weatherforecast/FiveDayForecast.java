package com.example.matthew.weatherforecast;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matthew on 21/04/2017.
 */

/**Fragment that represents the 5-day forecast
 *
 */
public class FiveDayForecast extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.five_day, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void update()
    {
        /**Populates items with relevant weather data
         */
        try
        {
            JSONObject city = new JSONObject(((MainActivity)getActivity()).w.obj.getString("city"));

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.location)).setText(city.getString("name") + ", " + city.getString("country"));

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.date)).setText(sdf.format(date));

            sdf = new SimpleDateFormat("dd/MM");

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Date)).setText(sdf.format(date));

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Date)).setText(sdf.format(date));

            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day3Date)).setText(sdf.format(date));

            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day4Date)).setText(sdf.format(date));

            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day5Date)).setText(sdf.format(date));

            JSONArray list = ((MainActivity)getActivity()).w.obj.getJSONArray("list");

            JSONObject day1 = list.getJSONObject(0);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Temp)).setText(Long.toString(Math.round(Float.parseFloat(day1.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            String icon = day1.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day1Img)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));
            //((ImageView)act.findViewById(R.id.day1Img2day)).setImageResource(act.getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", act.getPackageName()));

            JSONObject day2 = list.getJSONObject(8);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Temp)).setText(Long.toString(Math.round(Float.parseFloat(day2.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            icon = day2.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day2Img)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));

            JSONObject day3 = list.getJSONObject(16);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day3Temp)).setText(Long.toString(Math.round(Float.parseFloat(day3.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            icon = day3.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day3Img)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));

            JSONObject day4 = list.getJSONObject(24);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day4Temp)).setText(Long.toString(Math.round(Float.parseFloat(day4.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            icon = day4.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day4Img)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));

            JSONObject day5 = list.getJSONObject(32);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day5Temp)).setText(Long.toString(Math.round(Float.parseFloat(day5.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            icon = day5.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day5Img)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));

            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Prec)).setText(day1.getJSONObject("main").getString("humidity") + "%");
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Prec)).setText(day2.getJSONObject("main").getString("humidity") + "%");
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day3Prec)).setText(day3.getJSONObject("main").getString("humidity") + "%");
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day4Prec)).setText(day4.getJSONObject("main").getString("humidity") + "%");
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day5Prec)).setText(day5.getJSONObject("main").getString("humidity") + "%");
        }
        catch(Exception e){}
    }
}