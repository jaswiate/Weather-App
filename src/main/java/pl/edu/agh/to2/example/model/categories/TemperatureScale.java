package pl.edu.agh.to2.example.model.categories;

public record TemperatureScale(int category, String description) {

    @Override
    public String toString() {
        return description;
    }
}
