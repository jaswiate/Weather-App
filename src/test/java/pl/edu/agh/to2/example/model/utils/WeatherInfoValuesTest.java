package pl.edu.agh.to2.example.model.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.categories.CloudCategory;
import pl.edu.agh.to2.example.model.categories.MaskNeed;
import pl.edu.agh.to2.example.model.categories.TemperatureScale;
import pl.edu.agh.to2.example.model.categories.UmbrellaNeed;


class WeatherInfoValuesTest {

    @Test
    public void testGetPerceivedTemperature() {

        WeatherInfoValues weatherInfoValues = new WeatherInfoValues();


        assertEquals(new TemperatureScale(0, "Freezing cold"), weatherInfoValues.getPerceivedTemperature(-10, 5.0f));
        assertEquals(new TemperatureScale(1, "Cold"), weatherInfoValues.getPerceivedTemperature(1, 10.0f));
        assertEquals(new TemperatureScale(1, "Cool"), weatherInfoValues.getPerceivedTemperature(7, 15.0f));
        assertEquals(new TemperatureScale(2, "Warm"), weatherInfoValues.getPerceivedTemperature(12, 8.0f));
        assertEquals(new TemperatureScale(3, "Hot"), weatherInfoValues.getPerceivedTemperature(25, 20.0f));
    }

    @Test
    public void testGetCloudValue() {

        WeatherInfoValues weatherInfoValues = new WeatherInfoValues();


        assertEquals(new CloudCategory(0, "Clear"), weatherInfoValues.getCloudValue(5));
        assertEquals(new CloudCategory(1, "Mostly clear"), weatherInfoValues.getCloudValue(30));
        assertEquals(new CloudCategory(2, "Mostly cloudy"), weatherInfoValues.getCloudValue(70));
        assertEquals(new CloudCategory(3, "Cloudy"), weatherInfoValues.getCloudValue(90));
    }

    @Test
    public void testGetUmbrella() {

        WeatherInfoValues weatherInfoValues = new WeatherInfoValues();


        assertEquals(new UmbrellaNeed(false, "No umbrella"), weatherInfoValues.getUmbrella(2.5f));
        assertEquals(new UmbrellaNeed(true, "Yes umbrella"), weatherInfoValues.getUmbrella(10.0f));
    }

    @Test
    public void testGetMask() {

        WeatherInfoValues weatherInfoValues = new WeatherInfoValues();


        assertEquals(new MaskNeed(false, "No mask"), weatherInfoValues.getMask(50));
        assertEquals(new MaskNeed(true, "No mask"), weatherInfoValues.getMask(60));
    }
}