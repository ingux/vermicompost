package com.mic.hackinthewoods.vermicompost.view.entities;

import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

public class User {

    private UUID uuid;

    private String username;

    private LatLng geoLocation;

    public User(UUID uuid, String username, LatLng geoLocation) {
        this.uuid = uuid;
        this.username = username;
        this.geoLocation = geoLocation;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public LatLng getGeoLocation() {
        return geoLocation;
    }
}
