package pl.edu.agh.to2.example.model.services;

import org.json.JSONObject;
import org.json.JSONTokener;
import pl.edu.agh.to2.example.exception.WeatherApiUrlException;
import pl.edu.agh.to2.example.exception.WeatherDownloadException;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.model.WeatherJson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherDownloader {
    private static final String BASE_URL = "https://api.weatherapi.com/v1/forecast.json";
    private static final String CHARSET = StandardCharsets.UTF_8.name();
    private String apiKey;


    public WeatherDownloader(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherJson downloadWeatherInformation(Double latitude, Double longitude){
        WeatherJson jsonResponse;
        try {
            String endpoint = createUrl(latitude, longitude);
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            jsonResponse = new WeatherJson(jsonObject);

            connection.disconnect();
        } catch (IOException e) {
            throw new WeatherDownloadException("An error occurred while downloading weather data.", e);
        }
        return jsonResponse;
    }

    public WeatherJson downloadWeatherInformation(Location location){
        WeatherJson jsonResponse;
        try {
            String endpoint = createUrl(location.latitude(), location.longitude());
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            jsonResponse = new WeatherJson(jsonObject, location.today(), location.hour());

            connection.disconnect();
        } catch (IOException e) {
            throw new WeatherDownloadException("An error occurred while downloading weather data.", e);
        }
        return jsonResponse;
    }

    private String createUrl(Double latitude, Double longitude) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            urlBuilder.append("?key=").append(URLEncoder.encode(apiKey, CHARSET));
            urlBuilder.append("&q=").append(URLEncoder.encode(String.valueOf(latitude), CHARSET));
            urlBuilder.append(",").append(URLEncoder.encode(String.valueOf(longitude), CHARSET));
            urlBuilder.append("&days=2");
            urlBuilder.append("&aqi=yes");
        } catch (IOException e) {
            throw new WeatherApiUrlException("Error occurred during creation of WeatherAPI Url", e);
        }
        return urlBuilder.toString();
    }
}
