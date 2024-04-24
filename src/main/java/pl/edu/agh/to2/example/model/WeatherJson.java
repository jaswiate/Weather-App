package pl.edu.agh.to2.example.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherJson {
    private JSONObject json;

    private JSONObject currentObject;
    private JSONArray forecastArray;
    private boolean today = false;
    private Integer hour = -1;
    public WeatherJson(JSONObject json) {
        this.json = json;
        this.currentObject = json.getJSONObject("current");
        this.forecastArray = json.getJSONObject("forecast").getJSONArray("forecastday");
    }

    public WeatherJson(JSONObject json, boolean today, Integer hour) {
        this.json = json;
        this.currentObject = json.getJSONObject("current");
        this.forecastArray = json.getJSONObject("forecast").getJSONArray("forecastday");
        this.today = today;
        this.hour = hour;
    }

    public String getCountry() {
        JSONObject locationObject = json.getJSONObject("location");
        String country = locationObject.getString("country");

        return country;
    }

    public Double getLatitude() {
        JSONObject locationObject = json.getJSONObject("location");
        Double latitude = locationObject.getDouble("lat");

        return latitude;
    }

    public Double getLongitude() {
        JSONObject locationObject = json.getJSONObject("location");
        Double longitude = locationObject.getDouble("lon");

        return longitude;
    }

    public Float getTemperature() {
        if (hour == -1) {
            return currentObject.getFloat("temp_c");
        } else {
            JSONObject forecastObject = forecastPicker(today, hour);
            return forecastObject.getFloat("temp_c");
        }
    }


    public Float getWindSpeed() {
        if (hour == -1) {
            return currentObject.getFloat("wind_kph");
        } else {
            JSONObject forecastObject = forecastPicker(today, hour);
            return forecastObject.getFloat("wind_kph");
        }
    }

    public Float getPrecipitation() {
        if (hour == -1) {
            return currentObject.getFloat("precip_mm");
        } else {
            JSONObject forecastObject = forecastPicker(today, hour);
            return forecastObject.getFloat("precip_mm");
        }
    }


    public Integer getCloud() {
        if (hour == -1) {
            return currentObject.getInt("cloud");
        } else {
            JSONObject forecastObject = forecastPicker(today, hour);
            return forecastObject.getInt("cloud");
        }
    }

    public Integer getDefraIndex() {
        return currentObject.getJSONObject("air_quality").getInt("gb-defra-index");
    }

    private JSONObject forecastPicker(boolean today, Integer hour) {
        JSONObject dayObject;
        if (today) {
            dayObject = forecastArray.getJSONObject(0);
        } else {
            dayObject = forecastArray.getJSONObject(1);
        }
        JSONArray hourArray = dayObject.getJSONArray("hour");
        JSONObject forecastObject = hourArray.getJSONObject(hour);
        return forecastObject;
    }

    public Integer getHour() {
        return hour;
    }

    public boolean isToday() {
        return today;
    }

    @Override
    public String toString(){
        return json.toString(4);
    }
}
