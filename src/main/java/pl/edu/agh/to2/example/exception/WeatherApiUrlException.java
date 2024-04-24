package pl.edu.agh.to2.example.exception;

public class WeatherApiUrlException extends RuntimeException{
    public WeatherApiUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}
