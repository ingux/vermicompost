package com.mic.hackinthewoods.vermicompost.integration.dto;

import java.util.UUID;

public class UserDto {

    private UUID uuid;

    private String username;

    private String latitude;

    private String longitude;


    public UserDto(UUID uuid, String username, String latitude, String longitude) {
        this.uuid = uuid;
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
