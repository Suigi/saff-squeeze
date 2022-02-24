package ninja.ranner.saffsqueeze;

public record Runway(Coordinates coordinates) {
    public static Runway at(int longitude, int latitude) {
        return new Runway(Coordinates.of(longitude, latitude));
    }
}
