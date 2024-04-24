package pl.edu.agh.to2.example;

import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.utils.WeatherInfoParser;
import pl.edu.agh.to2.example.model.WeatherJson;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherInfoParserTest {

    private WeatherJson createWeatherJson(double temperature, double windSpeed, float precipitation, int cloud, int defraIndex) {
        JSONObject json = new JSONObject();

        JSONObject locationObject = new JSONObject();
        locationObject.put("country", "SampleCountry");
        locationObject.put("lat", 42.0);
        locationObject.put("lon", -71.0);
        locationObject.put("name", "SampleCity");

        json.put("location", locationObject);

        JSONObject currentObject = new JSONObject();
        currentObject.put("last_updated", "2023-12-12");
        currentObject.put("temp_c", temperature);
        currentObject.put("feelslike_c", temperature - 5.0); // Assume perceived temperature is 5 degrees lower
        currentObject.put("is_day", 1);
        currentObject.put("wind_kph", windSpeed);
        currentObject.put("wind_dir", "N");
        currentObject.put("pressure_mb", 1013.25);
        currentObject.put("precip_mm", precipitation);
        currentObject.put("humidity", 70);
        currentObject.put("cloud", cloud);

        JSONObject airQualityObject = new JSONObject();
        airQualityObject.put("gb-defra-index", defraIndex);

        currentObject.put("air_quality", airQualityObject);

        json.put("current", currentObject);

        JSONObject forecastObject = new JSONObject();
        List<Integer> day = new ArrayList<>();
        forecastObject.put("forecastday", day);
        json.put("forecast", forecastObject);

        return new WeatherJson(json);
    }


    @Test
    public void testGetWeatherInfoMultiple() {
        WeatherJson firstWeatherData = createWeatherJson(20.0, 10.0, 10.0F, 40, 40);
        WeatherJson secondWeatherData = createWeatherJson(25.0, 20.0, (float) 0.8, 20, 150);
        ArrayList<WeatherJson> weatherData = new ArrayList<>();
        weatherData.add(firstWeatherData);
        weatherData.add(secondWeatherData);

        ObservableList<String> result = WeatherInfoParser.getWeatherInfo(weatherData);

        assertNotNull(result);
        assertEquals(4, result.size());

        assertEquals("Hot",result.get(0));
        assertEquals("Mostly clear",result.get(1));
        assertEquals("Yes umbrella",result.get(2));
        assertEquals("No mask",result.get(3));
    }


}