package com.mic.hackinthewoods.vermicompost.integration.http;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.mic.hackinthewoods.vermicompost.integration.dto.Mapper;
import com.mic.hackinthewoods.vermicompost.integration.dto.UserDto;
import com.mic.hackinthewoods.vermicompost.view.entities.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;

public class UserHttpService {


    private static final UserHttpService instance = new UserHttpService();

    public static UserHttpService getInstance() {
        return instance;
    }

    private Gson gson;

    private static final String BASE_URL = "https://584789d9-48ff-466e-828e-ce598e332ef2.mock.pstmn.io/user";
    private static final String PORT = "";

    private SyncHttpClient httpClient = new SyncHttpClient();

    private UserHttpService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    public User getUserById(UUID uuid) throws JSONException, ExecutionException, InterruptedException {
        final List<UserDto> userDto = new ArrayList<>();

        httpClient.get("https://584789d9-48ff-466e-828e-ce598e332ef2.mock.pstmn.io/user", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.v("USER RESPONSE : ", response.toString());
                userDto.add(gson.fromJson(response.toString(), UserDto.class));
                Log.v("USER DTO : ", userDto.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("USER ERROR : ", responseString);
            }
        });

        if(userDto.size() > 0) {
            return Mapper.map(userDto.get(0));
        } else {
            return Mapper.map(new UserDto(null, null, null, null));
        }
    }


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + PORT + relativeUrl;
    }
}
