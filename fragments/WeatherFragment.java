package com.example.utilisateur92.weather.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.utilisateur92.weather.R;
import com.example.utilisateur92.weather.models.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    final String URL_BASE = "http://api.openweathermap.org/data/2.5/forecast";
    final String URL_COORD = "?lat=44.8079982&lon=-0.6101602";
    final String URL_UNIT = "&units=metric";
    final String URL_API = "&APPID=77a9aa0c33440f409f740a67601c43d5";

    //initialisation view
    private TextView maxTempView;
    private TextView minTempView;
    private TextView statusWeather;
    private TextView cityName;

    private WeatherModel WeatherModel = new WeatherModel();

// 44.8079982,-0.6101602
    public WeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        final String url = URL_BASE + URL_COORD + URL_UNIT + URL_API;

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response){

                try {
                    JSONObject jsonObjectCity = response.getJSONObject("city");
                    String nameCity = jsonObjectCity.getString("name");
                    String country = jsonObjectCity.getString("country");

                    JSONArray list = response.getJSONArray("list");
                    JSONObject jsonListIndex = list.getJSONObject(0);
                    JSONObject jsonObjectMain = jsonListIndex.getJSONObject("main");
                    JSONArray weatherArray = jsonListIndex.getJSONArray("weather");
                    JSONObject weather = weatherArray.getJSONObject(0);


                    //temperature min et max
                    Double temp_min = jsonObjectMain.getDouble("temp_min");
                    Double temp_max = jsonObjectMain.getDouble("temp_max");

                    String weatherStatus = weather.getString("main");

                    // Set Value
                    WeatherModel.setNameCity(nameCity);
                    WeatherModel.setCountry(country);
                    WeatherModel.setTemp_min(temp_min.intValue());
                    WeatherModel.setTemp_max(temp_max.intValue());
                    WeatherModel.setWeatherStatus(weatherStatus);

                    //declaration

                    maxTempView = (TextView) getView().findViewById(R.id.maxTempView);
                    minTempView = (TextView) getView().findViewById(R.id.minTempView);
                    statusWeather =(TextView) getView().findViewById(R.id.weatherStatus);
                    cityName = (TextView) getView().findViewById(R.id.cityName);

                    //dynamic data like API

                    maxTempView.setText(String.valueOf(WeatherModel.getTemp_max())+"°");
                    minTempView.setText(String.valueOf(WeatherModel.getTemp_min()+"°"));
                    statusWeather.setText(WeatherModel.getWeatherStatus());
                    cityName.setText(WeatherModel.getNameCity());



                    Log.i("Response City : ", WeatherModel.getNameCity()+" "+WeatherModel.getCountry());
                } catch (JSONException e) {
                    Log.i("Error",e.getMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error ",error.getLocalizedMessage());

            }
        });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

}
