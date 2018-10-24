package com.mic.hackinthewoods.vermicompost.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.mic.hackinthewoods.vermicompost.R;
import com.mic.hackinthewoods.vermicompost.integration.dto.CompostDto;
import com.mic.hackinthewoods.vermicompost.integration.dto.Mapper;
import com.mic.hackinthewoods.vermicompost.view.entities.SensorData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.mic.hackinthewoods.vermicompost.integration.utils.Constants.*;

public class DashBoardActivity extends AppCompatActivity {

    private Gson gson;

    private SyncHttpClient httpClient = new SyncHttpClient();

    private String temperatureData;
    private String humidityData;
    private String pressureData;
    private String airQualityData;

    private String score;

    private TextView scoreTextView;

    private TextView temperatureValueTextView;
    private TextView humidityValueTextView;
    private TextView pressureValueTextView;
    private TextView airQualityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_activicty);

        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();

        temperatureValueTextView = findViewById(R.id.temperature_value_textView);
        humidityValueTextView = findViewById(R.id.humidity_value_textView);
        pressureValueTextView = findViewById(R.id.pressure_value_textView);
        airQualityTextView = findViewById(R.id.airQuality_value_textView);

        scoreTextView = findViewById(R.id.score_textview);

        Button updateDataBtn = findViewById(R.id.update_data_btn);
        updateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        updateData();
                    }
                }).start();
                temperatureValueTextView.setText(temperatureData);
                humidityValueTextView.setText(humidityData);
                airQualityTextView.setText(airQualityData);
                pressureValueTextView.setText(pressureData);
                scoreTextView.setText(score);
            }
        });

    }

    private void updateData() {
        final List<SensorData> responses = new ArrayList<>();

        final List<String> temperature = new ArrayList<>();
        final List<String> humidity = new ArrayList<>();
        final List<String> pressure = new ArrayList<>();
        final List<String> airQuality = new ArrayList<>();

        final List<String> score = new ArrayList<>();


        httpClient.get("http://vermicompost.azurewebsites.net/api/vermi/1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    temperature.add(response.getJSONObject(0).getString("SensorValue"));
                    humidity.add(response.getJSONObject(1).getString("SensorValue"));
                    pressure.add(response.getJSONObject(2).getString("SensorValue"));
                    airQuality.add(response.getJSONObject(3).getString("SensorValue"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("SCORE ERROR", responseString);
            }
        });

//        httpClient.get("http://vermicompost.azurewebsites.net/api/score/1", new JsonHttpResponseHandler() {
//            @Override
//                public void onSuccess(int statusCode, Header[] headers, String response) {
//                score.add(response);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//                Log.e("SCORE ERROR", responseString);
//            }
//        });

        this.score = "4.34";

        if(temperature.size()>0) {
            this.temperatureData = temperature.get(0);
        } else {
            this.temperatureData = "No Data";
        }

        if(humidity.size()>0) {
            this.humidityData = humidity.get(0);
        } else {
            this.humidityData = "No Data";
        }

       if(pressure.size()>0) {
            this.pressureData = pressure.get(0);
        } else {
            this.pressureData = "No Data";
        }

       if(airQuality.size()>0) {
            this.airQualityData = airQuality.get(0);
        } else {
            this.airQualityData = "No Data";
        }
    }
}

