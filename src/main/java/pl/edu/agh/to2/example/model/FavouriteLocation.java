package pl.edu.agh.to2.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public record FavouriteLocation(double latitude, double longitude, int hour, int minutes,
                                String name) implements Serializable {
    @JsonCreator
    public FavouriteLocation(@JsonProperty("latitude") double latitude,
                             @JsonProperty("longitude") double longitude,
                             @JsonProperty("hour") int hour,
                             @JsonProperty("minutes") int minutes,
                             @JsonProperty("name") String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.hour = hour;
        this.minutes = minutes;
        this.name = name;
    }

//    public static FavouriteLocation getExistingLocation(String name, Iterable<FavouriteLocation> collection) {
//        for (FavouriteLocation location : collection) {
//            if (location.name().equals(name)) {
//                return location;
//            }
//        }
//        return null;
//    }
    public static Optional<FavouriteLocation> getExistingLocation(String name, List<FavouriteLocation> locations) {
        return locations.stream().filter(location -> location.name().equals(name)).findFirst();
    }
}