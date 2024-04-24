package pl.edu.agh.to2.example.exception;

public class WeatherDownloadException extends RuntimeException {
    public WeatherDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
