package pl.edu.agh.to2.example.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.edu.agh.to2.example.exception.LayoutLoaderException;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.presenter.WeatherPanel;
import pl.edu.agh.to2.example.model.utils.IconManager;
import pl.edu.agh.to2.example.model.services.WeatherDownloader;
import pl.edu.agh.to2.example.model.utils.WeatherInfoParser;
import pl.edu.agh.to2.example.model.WeatherJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    private Stage primaryStage;

    private String weatherApiKey;

    private WeatherPanel weatherPanel;

    public WeatherPanel getWeatherPanel() {
        return weatherPanel;
    }

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init(String weatherApiKey) {
        this.primaryStage.setTitle("Programex Weather App");
        this.weatherApiKey = weatherApiKey;
        primaryStage.setScene(loadScene());
        primaryStage.show();
    }

    private Scene loadScene() {
        Scene scene;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("/view/WeatherPanel.fxml"));
            BorderPane layout = loader.load();
            layout.setBackground(new Background(new BackgroundFill(Color.rgb(218, 249, 213), CornerRadii.EMPTY, Insets.EMPTY)));
            weatherPanel = loader.getController();
            weatherPanel.setAppController(this);
            scene = new Scene(layout, 750, 500);
        } catch (IOException e) {
            throw new LayoutLoaderException("Error loading FXML layout", e);
        }
        return scene;
    }

    public void getWeatherInfo(List<Location> locationList) {
        WeatherDownloader downloader = new WeatherDownloader(weatherApiKey);
        ArrayList<WeatherJson> weatherData = new ArrayList<>();
        for (Location location : locationList) {
            weatherData.add(downloader.downloadWeatherInformation(location));
        }

        ObservableList<String> weatherInfo = WeatherInfoParser.getWeatherInfo(weatherData);
        ObservableList<String> weatherFeatures = WeatherInfoParser.getWeatherFeatures();

        int i = 0;
        weatherPanel.setTemperatureTile(IconManager.getIconImage(weatherInfo.get(i)), weatherFeatures.get(i));
        weatherPanel.setCloudTile(IconManager.getIconImage(weatherInfo.get(++i)), weatherFeatures.get(i));
        weatherPanel.setUmbrellaTile(IconManager.getIconImage(weatherInfo.get(++i)), weatherFeatures.get(i));
        weatherPanel.setMaskTile(IconManager.getIconImage(weatherInfo.get(++i)), weatherFeatures.get(i));
    }

    public void setWeatherPanel(WeatherPanel weatherPanel) {
        this.weatherPanel = weatherPanel;
    }
}
