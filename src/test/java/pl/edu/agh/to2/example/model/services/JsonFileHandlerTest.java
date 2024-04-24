package pl.edu.agh.to2.example.model.services;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.FavouriteLocation;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class JsonFileHandlerTest {


    @Test
    public void testReadLocationsFromFile() {
        List<FavouriteLocation> expectedLocations = Arrays.asList(
                new FavouriteLocation(1.0, 2.0, 10, 30, "Location1"),
                new FavouriteLocation(3.0, 4.0, 15, 45, "Location2")
        );

        JsonFileHandler jsonFileHandlerMock = mock(JsonFileHandler.class);
        jsonFileHandlerMock.writeLocationsToFile(expectedLocations);
        List<FavouriteLocation> actualLocations = jsonFileHandlerMock.readLocationsFromFile();

        assertEquals(expectedLocations, actualLocations);
    }



    @Test
    public void testWriteAndReadEmptyLocations() {

        List<FavouriteLocation> emptyLocations = Arrays.asList();
        JsonFileHandler jsonFileHandlerMock = mock(JsonFileHandler.class);
        jsonFileHandlerMock.writeLocationsToFile(emptyLocations);
        List<FavouriteLocation> readLocations = jsonFileHandlerMock.readLocationsFromFile();


        assertEquals(emptyLocations, readLocations);
    }

}