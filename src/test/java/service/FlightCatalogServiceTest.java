package service;

import model.Flight;
import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying reflection-driven DSA operations and fallbacks in {@link FlightCatalogService}.
 */
public class FlightCatalogServiceTest {

    @Test
    @DisplayName("sortFlightsWithBST sorts flights chronologically via reflected FlightBST")
    public void testSortFlightsWithBST() {
        FlightCatalogService service = new FlightCatalogService();

        Flight f1 = new Flight("FL101", "NYC", "14:45", "LON", "22:15", 500.0);
        Flight f2 = new Flight("FL102", "NYC", "08:30", "PAR", "16:00", 450.0);
        Flight f3 = new Flight("FL103", "NYC", "22:15", "TOK", "06:30", 800.0);
        Flight f4 = new Flight("FL104", "NYC", "12:00", "ROM", "20:00", 650.0);

        List<Flight> input = new ArrayList<>(List.of(f1, f2, f3, f4));
        List<Flight> sorted = service.sortFlightsWithBST(input);

        assertNotNull(sorted);
        assertEquals(4, sorted.size());
        assertEquals("08:30", sorted.get(0).getDepartureTime());
        assertEquals("12:00", sorted.get(1).getDepartureTime());
        assertEquals("14:45", sorted.get(2).getDepartureTime());
        assertEquals("22:15", sorted.get(3).getDepartureTime());
    }

    @Test
    @DisplayName("sortFlightsWithBST handles null and empty flight lists gracefully")
    public void testSortFlightsWithBSTEmptyAndNull() {
        FlightCatalogService service = new FlightCatalogService();

        List<Flight> emptyResult = service.sortFlightsWithBST(new ArrayList<>());
        assertNotNull(emptyResult);
        assertTrue(emptyResult.isEmpty());

        List<Flight> nullResult = service.sortFlightsWithBST(null);
        assertNotNull(nullResult);
        assertTrue(nullResult.isEmpty());
    }
}
