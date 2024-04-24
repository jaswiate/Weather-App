package pl.edu.agh.to2.example.model.utils;

import pl.edu.agh.to2.example.model.categories.CloudCategory;
import pl.edu.agh.to2.example.model.categories.MaskNeed;
import pl.edu.agh.to2.example.model.categories.TemperatureScale;
import pl.edu.agh.to2.example.model.categories.UmbrellaNeed;

public class WeatherInfoValues {

    public TemperatureScale getPerceivedTemperature (double temperature, Float windSpeed) {
        double v = Math.pow(windSpeed, 0.16);
        double temp = (double) Math.round((13.12 + 0.6215 * temperature - 11.37 * v
                + 0.3965 * temperature * v) * 10) / 10;
        if (temp <= -3) {
            return new TemperatureScale(0, "Freezing cold");
        }
        else if (temp <= 3) {
            return new TemperatureScale(1, "Cold");
        }
        else if (temp <= 8) {
            return new TemperatureScale(1, "Cool");
        }
        else if (temp <= 14) {
            return new TemperatureScale(2, "Warm");
        }
        return new TemperatureScale(3, "Hot");
    }

    public CloudCategory getCloudValue(Integer cloud) {
        if (cloud <= 10) {
            return new CloudCategory(0, "Clear");
        }
        else if (cloud <= 40) {
            return new CloudCategory(1, "Mostly clear");
        }
        else if (cloud <= 80) {
            return new CloudCategory(2, "Mostly cloudy");
        }
        return new CloudCategory(3, "Cloudy");
    }

    public UmbrellaNeed getUmbrella(Float precipitation) {
        if (precipitation <= 4) {
            return new UmbrellaNeed(false, "No umbrella");
        }
        return new UmbrellaNeed(true, "Yes umbrella");
    }

    public MaskNeed getMask(Integer defraIndex) {
        if (defraIndex <= 53) {
            return new MaskNeed(false, "No mask");
        }
        return new MaskNeed(true, "No mask");
    }
}
