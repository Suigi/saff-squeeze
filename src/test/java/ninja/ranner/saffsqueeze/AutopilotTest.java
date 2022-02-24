package ninja.ranner.saffsqueeze;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AutopilotTest {

    @Test
    public void autopilot_flies_to_closest_runway() {
        var aircraft = new FakeAircraft(Coordinates.of(2, 2));
        var closestLondonRunway = Runway.at(5, 5);
        var furtherLondonRunway = Runway.at(10, 10);
        var london = new Airport("London", closestLondonRunway, furtherLondonRunway);
        var autopilot = new Autopilot(aircraft, london);

        var targetAirport = Arrays.stream(autopilot.airports)
                .filter(airport -> airport.city().equals("London"))
                .findFirst()
                .get();

        var targetRunway = autopilot.findClosest(targetAirport.runways());
        assertThat(targetRunway.coordinates())
            .isEqualTo(Coordinates.of(5, 5));
    }

    public static class FakeAircraft implements Autopilot.Aircraft {

        private final Coordinates currentPosition;
        private Coordinates target;

        public FakeAircraft(Coordinates currentPosition) {
            this.currentPosition = currentPosition;
        }

        @Override
        public void setWayPoint(Coordinates plan) {
            this.target = plan;
        }

        @Override
        public Coordinates currentPosition() {
            return currentPosition;
        }

        public void assertTargetCoordinatesAre(Coordinates coordinates) {
            assertThat(this.target)
                    .isEqualTo(coordinates);
        }
    }
}
