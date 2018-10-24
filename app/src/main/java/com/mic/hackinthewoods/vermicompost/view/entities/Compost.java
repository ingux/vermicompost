package com.mic.hackinthewoods.vermicompost.view.entities;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compost {

    private String id;

    private LatLng geoLocation;

    private List<SensorData> temperatureData = new ArrayList<>();

    private List<SensorData> airQualityData = new ArrayList<>();

    private List<SensorData> pressureData = new ArrayList<>();

    private List<SensorData> humidityData = new ArrayList<>();


    public Compost(String uuid, LatLng geoLocation) {
        this.id = id;
        this.geoLocation = geoLocation;
    }
}
