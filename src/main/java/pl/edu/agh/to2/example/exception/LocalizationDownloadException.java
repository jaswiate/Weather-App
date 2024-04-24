package pl.edu.agh.to2.example.exception;

public class LocalizationDownloadException extends RuntimeException {

    public LocalizationDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
