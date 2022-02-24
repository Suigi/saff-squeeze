package ninja.ranner.saffsqueeze;

public record Coordinates(int longitude, int latitude) {
    public static Coordinates of(int longitude, int latitude) {
        return new Coordinates(longitude, latitude);
    }

    @Override
    public String toString() {
        return "Coordinates(%d, %d)".formatted(longitude, latitude);
    }
}
