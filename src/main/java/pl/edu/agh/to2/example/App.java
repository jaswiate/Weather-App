package pl.edu.agh.to2.example;
import javafx.application.Application;
import javafx.stage.Stage;
import io.github.cdimascio.dotenv.Dotenv;
import pl.edu.agh.to2.example.controller.AppController;

public class App extends Application {

    private AppController appController;

    @Override
    public void start(Stage primaryStage) {
        Dotenv dotenv = Dotenv.load();
        String weatherApiKey = dotenv.get("API_KEY");
        this.appController = new AppController(primaryStage);
        this.appController.init(weatherApiKey);
    }
}
