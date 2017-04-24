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
import java.util.Date;

/**
 * Created by Matthew on 21/04/2017.
 */

/**Represents the two-day forecast used by our program
 *
 */
public class TwoDayForecast extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.two_day, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void update()
    {
        //Populates fragment with the relevant data
        try {
            JSONObject city = new JSONObject(((MainActivity) getActivity()).w.obj.getString("city"));
            ((TextView) ((MainActivity) getActivity()).findViewById(R.id.location)).setText(city.getString("name") + ", " + city.getString("country"));

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            ((TextView) ((MainActivity) getActivity()).findViewById(R.id.date)).setText(sdf.format(date));

            JSONArray list = ((MainActivity)getActivity()).w.obj.getJSONArray("list");

            JSONObject day1 = list.getJSONObject(0);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Temp2day)).setText(Long.toString(Math.round(Float.parseFloat(day1.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            String icon = day1.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day1Img2day)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Desc2day)).setText(day1.getJSONArray("weather").getJSONObject(0).getString("description"));
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Humid2day)).setText(day1.getJSONObject("main").getString("humidity") + "% humidity");
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day1Wind2day)).setText(day1.getJSONObject("wind").getString("speed") + "mph wind");

            JSONObject day2 = list.getJSONObject(8);
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Temp2day)).setText(Long.toString(Math.round(Float.parseFloat(day2.getJSONObject("main").getString("temp")) - 273.15f)) + "°C");
            icon = day2.getJSONArray("weather").getJSONObject(0).getString("icon");
            ((ImageView)((MainActivity)getActivity()).findViewById(R.id.day2Img2day)).setImageResource(((MainActivity)getActivity()).getResources().getIdentifier(((icon.contains("d")) ? "day" : "night") + icon.substring(0, 2), "drawable", ((MainActivity)getActivity()).getPackageName()));
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Desc2day)).setText(day2.getJSONArray("weather").getJSONObject(0).getString("description"));
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Humid2day)).setText(day2.getJSONObject("main").getString("humidity") + "% humidity");
            ((TextView)((MainActivity)getActivity()).findViewById(R.id.day2Wind2day)).setText(day2.getJSONObject("wind").getString("speed") + "mph wind");

        }
        catch(Exception e){}
    }
}
