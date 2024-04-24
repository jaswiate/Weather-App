package pl.edu.agh.to2.example.model.categories;

public record CloudCategory(int category, String description) {

    @Override
    public String toString() {
        return description;
    }
}