package com.mic.hackinthewoods.vermicompost.integration.dto;

public class CompostDto {

    private String Id;

    private String latitude;

    private String longitude;

    public CompostDto(String id, String latitude, String longitude) {
        Id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return Id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
