package pl.edu.agh.to2.example;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import pl.edu.agh.to2.example.model.WeatherJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class WeatherJsonTest {

    private static final WeatherJson weatherJson;

    static {
        String text = "";
        try {
            text = Files.readString(Paths.get("src/test/java/pl/edu/agh/to2/example/sampleData.json"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(text);
        weatherJson = new WeatherJson(json);
    }



    @Test
    void testGetCountry() {
        assertEquals("Poland", weatherJson.getCountry());
    }

    @Test
    void testGetLatitude() {
        assertEquals(50.04, weatherJson.getLatitude());
    }

    @Test
    void testGetLongitude() {
        assertEquals(19.94, weatherJson.getLongitude());
    }


}