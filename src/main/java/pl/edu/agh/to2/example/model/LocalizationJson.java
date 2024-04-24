package pl.edu.agh.to2.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizationJson {
    private final Double longitude;
    private final Double latitude;

    @JsonCreator
    public LocalizationJson(@JsonProperty("loc") String localization){
        this.longitude = Double.parseDouble(localization.split(",")[1]);
        this.latitude = Double.parseDouble(localization.split(",")[0]);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
