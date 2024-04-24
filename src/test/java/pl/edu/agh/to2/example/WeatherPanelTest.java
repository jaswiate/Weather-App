package pl.edu.agh.to2.example;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import pl.edu.agh.to2.example.controller.AppController;
import pl.edu.agh.to2.example.presenter.WeatherPanel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WeatherPanelTest extends ApplicationTest {

    private WeatherPanel weatherPanel;
    private AppController appController;

    @Override
    public void start(Stage stage) throws Exception {

        appController = new AppController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WeatherPanel.fxml"));
        BorderPane root = loader.load();
        weatherPanel = mock(WeatherPanel.class);
        when(weatherPanel.getAppController()).thenReturn(appController);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Test
    public void testHandleShowWeatherActionUserLocation() {
        weatherPanel.handleShowWeatherAction(new ActionEvent(weatherPanel.getShowWeatherButtonUserLocation(),null));

        assertNotNull(weatherPanel.getAppController());

    }

}