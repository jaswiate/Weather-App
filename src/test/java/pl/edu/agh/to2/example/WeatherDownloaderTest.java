package pl.edu.agh.to2.example;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.services.WeatherDownloader;


import static org.junit.jupiter.api.Assertions.*;

class WeatherDownloaderTest {



    @Test
    void testDownloadWeatherInformationWithInvalidApiKey() {
        String invalidApiKey = "870781976bcf4c3e8be151602232611";

        WeatherDownloader invalidWeatherDownloader = new WeatherDownloader(invalidApiKey);

        Double latitude = 37.7749;
        Double longitude = -122.4194;

        assertThrows(RuntimeException.class,
                () -> invalidWeatherDownloader.downloadWeatherInformation(latitude, longitude));
    }
}
