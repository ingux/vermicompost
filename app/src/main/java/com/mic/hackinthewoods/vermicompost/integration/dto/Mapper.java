package com.mic.hackinthewoods.vermicompost.integration.dto;

import com.google.android.gms.maps.model.LatLng;
import com.mic.hackinthewoods.vermicompost.view.entities.Compost;
import com.mic.hackinthewoods.vermicompost.view.entities.User;

public class Mapper {

    public static User map(UserDto userDto) {
        LatLng geoloc = new LatLng(Double.valueOf(userDto.getLatitude()), Double.valueOf(userDto.getLongitude()));
        return new User(userDto.getUuid(), userDto.getUsername(), geoloc);
    }

    public static UserDto map (User user) {
        return new UserDto(user.getUuid(), user.getUsername(), String.valueOf(user.getGeoLocation().latitude), String.valueOf(user.getGeoLocation().longitude));
    }


    public static Compost map(CompostDto compostDto) {
        LatLng geoloc = new LatLng(Double.valueOf(compostDto.getLatitude()), Double.valueOf(compostDto.getLongitude()));
        return new Compost(compostDto.getId(), geoloc);
    }
}
