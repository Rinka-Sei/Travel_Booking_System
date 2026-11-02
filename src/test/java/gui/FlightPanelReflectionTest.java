package gui;

import model.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying reflection integration in {@link FlightPanel} for {@code ds.FlightBST}.
 */
public class FlightPanelReflectionTest {

    @Test
    @DisplayName("sortFlightsWithReflection orders flights chronologically via reflected FlightBST")
    public void testSortFlightsWithReflection() {
        Flight f1 = new Flight("FL1", "NYC", "18:30", "LAX", "22:00", 300.0);
        Flight f2 = new Flight("FL2", "NYC", "06:15", "LAX", "09:45", 250.0);
        Flight f3 = new Flight("FL3", "NYC", "12:00", "LAX", "15:30", 280.0);

        List<Flight> list = new ArrayList<>(List.of(f1, f2, f3));
        List<Flight> sorted = FlightPanel.sortFlightsWithReflection(list);

        assertNotNull(sorted);
        assertEquals(3, sorted.size());
        assertEquals("06:15", sorted.get(0).getDepartureTime());
        assertEquals("12:00", sorted.get(1).getDepartureTime());
        assertEquals("18:30", sorted.get(2).getDepartureTime());
    }

    @Test
    @DisplayName("sortFlightsWithReflection gracefully handles null and empty lists")
    public void testSortFlightsNullAndEmpty() {
        assertNull(FlightPanel.sortFlightsWithReflection(null));
        List<Flight> empty = new ArrayList<>();
        List<Flight> result = FlightPanel.sortFlightsWithReflection(empty);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
