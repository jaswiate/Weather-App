package pl.edu.agh.to2.example.model.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.example.model.WeatherJson;

import java.util.ArrayList;

public class WeatherInfoParser {

    public static ObservableList<String> getWeatherInfo(ArrayList<WeatherJson> weatherData) {
        ObservableList<String> weatherInfo = FXCollections.observableArrayList();
        WeatherInfoValues weatherValues = new WeatherInfoValues();

        float minTemperature = Float.MAX_VALUE;
        float maxWindSpeed = 0.0f;
        float maxPrecipitation = 0.0f;
        int maxCloud = 0;
        int maxAirPollution = 0;

        for(WeatherJson data : weatherData) {
            minTemperature = Math.min(minTemperature, data.getTemperature());
            maxWindSpeed = Math.max(maxWindSpeed, data.getWindSpeed());
            maxPrecipitation = Math.max(maxPrecipitation, data.getPrecipitation());
            maxCloud = Math.max(maxCloud, data.getCloud());
            maxAirPollution = Math.max(maxAirPollution, data.getDefraIndex());
        }

        weatherInfo.add(weatherValues.getPerceivedTemperature(minTemperature, maxWindSpeed).toString());
        weatherInfo.add(weatherValues.getCloudValue(maxCloud).toString());
        weatherInfo.add(weatherValues.getUmbrella(maxPrecipitation).toString());
        weatherInfo.add(weatherValues.getMask(maxAirPollution).toString());

        return weatherInfo;
    }

    public static ObservableList<String> getWeatherFeatures() {
        ObservableList<String> weatherFeatures = FXCollections.observableArrayList();

        weatherFeatures.add("Perceived temperature: ");
        weatherFeatures.add("Cloud: ");
        weatherFeatures.add("Umbrella: ");
        weatherFeatures.add("Mask: ");

        return weatherFeatures;
    }
}
