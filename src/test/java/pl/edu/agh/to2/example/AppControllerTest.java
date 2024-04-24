package pl.edu.agh.to2.example;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import pl.edu.agh.to2.example.controller.AppController;
import pl.edu.agh.to2.example.presenter.WeatherPanel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppControllerTest extends ApplicationTest {

    private AppController appController;

    @Override
    public void start(Stage stage) {
        appController = new AppController(stage);
    }

    @Test
    public void testInit() {
        WeatherPanel weatherPanelMock = mock(WeatherPanel.class);
        when(weatherPanelMock.getAppController()).thenReturn(appController);

        appController.setWeatherPanel(weatherPanelMock);

        assertNotNull(appController.getWeatherPanel());
        assertNotNull(appController.getWeatherPanel().getAppController());
    }
}