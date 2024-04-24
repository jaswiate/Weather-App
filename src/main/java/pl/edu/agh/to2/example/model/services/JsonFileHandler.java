package pl.edu.agh.to2.example.model.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.agh.to2.example.model.FavouriteLocation;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JsonFileHandler {
    private static final String FILE_PATH = "src/main/resources/db/favourites.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<FavouriteLocation> readLocationsFromFile() {
        try {
            return objectMapper.readValue(new File(FILE_PATH), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public static void writeLocationsToFile(List<FavouriteLocation> locations) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
