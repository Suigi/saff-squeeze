package ninja.ranner.saffsqueeze;

import java.util.Arrays;
import java.util.Comparator;

public class Autopilot {

    public final Aircraft aircraft;
    public final Airport[] airports;

    public Autopilot(Aircraft aircraft, Airport... airPorts) {
        this.aircraft = aircraft;
        this.airports = airPorts;
    }

    public void flyTo(String targetCity) {
        var targetAirport = Arrays.stream(airports)
                .filter(airport -> airport.city().equals(targetCity))
                .findFirst()
                .get();

        var targetRunway = findClosest(targetAirport.runways());
        aircraft.setWayPoint(targetRunway.coordinates());
    }

    public Runway findClosest(Runway[] runways) {
        return Arrays.stream(runways)
                .max(Comparator.comparing(runway -> distanceTo(runway, aircraft.currentPosition())))
                .get();
    }

    public static int distanceTo(Runway runway, Coordinates currentPosition) {
        return distanceSquared(runway.coordinates(), currentPosition);
    }

    private static int distanceSquared(Coordinates a, Coordinates b) {
        var longitudeDifference = Math.abs(a.longitude() - b.longitude());
        var latitudeDifference = Math.abs(a.latitude() - b.latitude());
        return longitudeDifference * longitudeDifference +
                latitudeDifference * latitudeDifference;
    }

    public interface Aircraft {
        void setWayPoint(Coordinates plan);

        Coordinates currentPosition();
    }

}
