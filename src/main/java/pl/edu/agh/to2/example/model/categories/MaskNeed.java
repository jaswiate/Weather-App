package pl.edu.agh.to2.example.model.categories;

public record MaskNeed(boolean maskNeeded, String description) {
    @Override
    public String toString() {
        return description;
    }
}
