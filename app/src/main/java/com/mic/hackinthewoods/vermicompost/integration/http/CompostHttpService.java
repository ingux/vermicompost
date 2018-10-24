package com.mic.hackinthewoods.vermicompost.integration.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.mic.hackinthewoods.vermicompost.integration.dto.CompostDto;
import com.mic.hackinthewoods.vermicompost.integration.dto.Mapper;
import com.mic.hackinthewoods.vermicompost.integration.dto.UserDto;
import com.mic.hackinthewoods.vermicompost.view.entities.Compost;
import com.mic.hackinthewoods.vermicompost.view.entities.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;
import static com.mic.hackinthewoods.vermicompost.integration.utils.Constants.*;

public class CompostHttpService {

    private static final CompostHttpService instance = new CompostHttpService();

    public static CompostHttpService getInstance() {
        return instance;
    }

    private Gson gson;

    private SyncHttpClient httpClient = new SyncHttpClient();

    private CompostHttpService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    public Compost getCompostById(String id) {
        final List<CompostDto> compostDtos = new ArrayList<>();

        httpClient.get(getAbsoluteUrl("/" + id), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.v("COMPOST RESPONSE ", response.toString());
                compostDtos.add(gson.fromJson(response.toString(), CompostDto.class));
                Log.v("COMPOST DTO ", compostDtos.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("COMPOST ERROR : ", responseString);
            }
        });

        if(compostDtos.size() > 0) {
            return Mapper.map(compostDtos.get(0));
        } else {
            return Mapper.map(new CompostDto(null, null, null));
        }
    }


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + COMPOST_URL + relativeUrl;
    }
}