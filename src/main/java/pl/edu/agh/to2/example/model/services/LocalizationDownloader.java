package pl.edu.agh.to2.example.model.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONTokener;
import pl.edu.agh.to2.example.exception.LocalizationDownloadException;
import pl.edu.agh.to2.example.model.LocalizationJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class LocalizationDownloader {

    private static final String API_URL = "https://ipinfo.io";
    public static LocalizationJson downloadCurrentLocalization(){
        LocalizationJson jsonResponse;

        try{
            URL url = new URL(API_URL);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

            ObjectMapper objectMapper = new ObjectMapper();
            jsonResponse = objectMapper.readValue(jsonObject.toString(),LocalizationJson.class);
        }
        catch(IOException e){
            throw new LocalizationDownloadException("Error downloading localization data",e);
        }
        return jsonResponse;
    }
}
