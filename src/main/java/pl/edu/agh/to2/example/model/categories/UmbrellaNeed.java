package pl.edu.agh.to2.example.model.categories;

public record UmbrellaNeed(boolean umbrellaNeeded, String description) {

    @Override
    public String toString() {
        return description;
    }
}
