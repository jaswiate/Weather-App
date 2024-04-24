package pl.edu.agh.to2.example.model.utils;

import javafx.scene.image.Image;

public class IconManager {
    public static Image getIconImage(String type) {
        return switch (type) {
            case "Clear" -> new Image("/icons/clear.png");
            case "Mostly clear" -> new Image("/icons/mostly_clear.png");
            case "Mostly cloudy" -> new Image("/icons/mostly_cloudy.png");
            case "Cloudy" -> new Image("/icons/cloudy.png");
            case "Yes umbrella" -> new Image("/icons/umbrella.png");
            case "No umbrella" -> new Image("/icons/no_umbrella.png");
            case "Yes mask" -> new Image("/icons/mask.png");
            case "No mask" -> new Image("/icons/no_mask.png");
            case "Freezing cold" -> new Image("icons/freezing.png");
            case "Cold" -> new Image("/icons/cold.png");
            case "Cool" -> new Image("/icons/cool.png");
            case "Warm" -> new Image("/icons/warm.png");
            case "Hot" -> new Image("/icons/hot.png");
            default -> new Image("/icons/placeholder.png");
        };
    }
}
