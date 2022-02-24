package ninja.ranner.saffsqueeze;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class AutopilotTest {

    @Test
    public void autopilot_flies_to_closest_runway() {
        var aircraftPosition = Coordinates.of(2, 2);
        var closestLondonRunway = Runway.at(5, 5);
        var furtherLondonRunway = Runway.at(10, 10);
        var london = new Airport("London", closestLondonRunway, furtherLondonRunway);

        var targetRunway = Arrays.stream(london.runways())
                .max(Comparator.comparing(runway -> Autopilot.distanceTo(runway, aircraftPosition)))
                .get();

        assertThat(targetRunway.coordinates())
            .isEqualTo(Coordinates.of(5, 5));
    }

}
