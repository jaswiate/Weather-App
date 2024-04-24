package pl.edu.agh.to2.example.presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.time.LocalTime;
import pl.edu.agh.to2.example.controller.AppController;
import pl.edu.agh.to2.example.model.FavouriteLocation;
import pl.edu.agh.to2.example.model.LocalizationJson;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.model.services.JsonFileHandler;
import pl.edu.agh.to2.example.model.services.LocalizationDownloader;
import pl.edu.agh.to2.example.model.utils.IconManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WeatherPanel {
    private AppController appController;

    private final ArrayList<Location> locationList = new ArrayList<>();

    private final ObservableList<FavouriteLocation> favoriteLocations = FXCollections.observableArrayList();

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    private final JsonFileHandler jsonFileHandler = new JsonFileHandler();

    @FXML
    public void initialize() {
        List<FavouriteLocation> storedLocations = jsonFileHandler.readLocationsFromFile();
        if (storedLocations != null) {
            favoriteLocations.addAll(storedLocations);
        }
        favoritesBox.setItems(favoriteLocations.stream().map(FavouriteLocation::name).collect(Collectors.toCollection(FXCollections::observableArrayList)));

        deleteFavoriteButton.setDisable(true);
        favoritesBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> deleteFavoriteButton.setDisable(newValue == null));

        addToFavorite.setDisable(true);
        favoritePlaceName.textProperty().addListener((observable, oldValue, newValue) -> addToFavorite.setDisable(newValue.trim().isEmpty()));

        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        hourSpinner.getValueFactory().setValue(hour);
        minuteSpinner.getValueFactory().setValue(minute);
    }

    @FXML
    public void handleShowWeatherAction(ActionEvent event) {
        appController.getWeatherInfo(locationList);
    }

    @FXML
    public void handleAddWeather() {
        Text placeToAdd = new Text();
        Location locationToAdd = new Location(
                Double.parseDouble(userLatitude.getText()),
                Double.parseDouble(userLongitude.getText()),
                hourSpinner.getValue(),
                minuteSpinner.getValue(),
                !tomorrowBox.isSelected()
        );
        locationList.add(locationToAdd);
        String day;
        if (locationToAdd.today()) { day = "today"; }
        else { day = "tomorrow"; }
        placeToAdd.setText("Place " + locationList.size() + ":  "
                + locationToAdd.latitude() + ", " + locationToAdd.longitude()
                + " at: " + locationToAdd.hour() + ":" + locationToAdd.minutes() + " " + day);
        placeBox.getChildren().add(placeToAdd);
    }

    @FXML
    public void handleAddToFavorite() {
        double latitude = Double.parseDouble(userLatitude.getText());
        double longitude = Double.parseDouble(userLongitude.getText());
        String placeName = favoritePlaceName.getText();
        int hour = hourSpinner.getValue();
        int minutes = minuteSpinner.getValue();

        // Check if location with given name exists

        Optional<FavouriteLocation> existingLocation = FavouriteLocation.getExistingLocation(placeName,favoriteLocations);

        if (existingLocation.isPresent()){
            //update location
            FavouriteLocation oldLocation = existingLocation.get();
            favoriteLocations.remove(oldLocation);
        } else {favoritesBox.getItems().add(placeName);}

        FavouriteLocation newLocation = new FavouriteLocation(latitude,longitude,hour,minutes,placeName);
        favoriteLocations.add(newLocation);
        jsonFileHandler.writeLocationsToFile(favoriteLocations);
    }

    @FXML
    public void handleDeleteFavorite() {
        String selectedOption = favoritesBox.getValue();
        if (selectedOption != null) {
            favoritesBox.getItems().remove(selectedOption);
            FavouriteLocation selectedLocation = favoriteLocations
                    .stream()
                    .filter(location -> location.name().equals(selectedOption))
                    .findFirst()
                    .orElse(null);
            favoriteLocations.remove(selectedLocation);
            favoritesBox.setValue(null);
            favoritesBox.getSelectionModel().clearSelection();
            JsonFileHandler.writeLocationsToFile(favoriteLocations);
        }
    }

    @FXML
    public void handleSelectedFav() {
        String selectedOption = favoritesBox.getValue();

        FavouriteLocation selectedLocation = favoriteLocations
                .stream()
                .filter(location -> location.name().equals(selectedOption))
                .findFirst()
                .orElse(null);
        if (selectedLocation != null){
            userLatitude.setText(String.valueOf(selectedLocation.latitude()));
            userLongitude.setText(String.valueOf(selectedLocation.longitude()));
            hourSpinner.getValueFactory().setValue(selectedLocation.hour());
            minuteSpinner.getValueFactory().setValue(selectedLocation.minutes());
        }

    }

    @FXML
    public void handleClearWeather() {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        hourSpinner.getValueFactory().setValue(hour);
        minuteSpinner.getValueFactory().setValue(minute);
        locationList.clear();
        placeBox.getChildren().clear();
        resetTiles();
    }

    @FXML
    public void handleLocateUser() {
        LocalizationJson localization = LocalizationDownloader.downloadCurrentLocalization();
        userLatitude.setText(String.valueOf(localization.getLatitude()));
        userLongitude.setText(String.valueOf(localization.getLongitude()));
    }


    public void setTemperatureTile(Image icon, String feature) {
        tempIcon.setImage(icon);
        tempFeature.setText(feature);
    }

    public void setCloudTile(Image icon, String feature) {
        cloudIcon.setImage(icon);
        cloudFeature.setText(feature);
    }


    public void setUmbrellaTile(Image icon, String feature) {
        umbrellaIcon.setImage(icon);
        umbrellaFeature.setText(feature);
    }

    public void setMaskTile(Image icon, String feature) {
        maskIcon.setImage(icon);
        maskFeature.setText(feature);
    }

    public void resetTiles() {
        Image placeholder = IconManager.getIconImage("Placeholder");
        setTemperatureTile(placeholder, "");
        setCloudTile(placeholder, "");
        setUmbrellaTile(placeholder, "");
        setMaskTile(placeholder, "");
    }


    @FXML private TextField userLatitude;
    @FXML private TextField userLongitude;
    @FXML private ImageView tempIcon;
    @FXML private Text tempFeature;
    @FXML private ImageView cloudIcon;
    @FXML private Text cloudFeature;
    @FXML private ImageView umbrellaIcon;
    @FXML private Text umbrellaFeature;
    @FXML private ImageView maskIcon;
    @FXML private Text maskFeature;
    @FXML private VBox placeBox;
    @FXML private Spinner<Integer> minuteSpinner;
    @FXML private Spinner<Integer> hourSpinner;
    @FXML private TextField favoritePlaceName;
    @FXML private Button deleteFavoriteButton;
    @FXML private Button addToFavorite;
    @FXML private ChoiceBox<String> favoritesBox;
    @FXML private CheckBox tomorrowBox;
    @FXML private Button locationButton;
    @FXML private Button addWeather;
    @FXML private Button showWeatherButtonCoords;
    @FXML private Button clearWeather;
    @FXML private Button showWeatherButtonUserLocation;

    public AppController getAppController() {
        return appController;
    }

    public Button getShowWeatherButtonUserLocation() {
        return showWeatherButtonUserLocation;
    }
}
