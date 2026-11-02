package gui;

import model.Flight;
import model.Hotel;
import model.PackageDeal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying reflection integration in {@link PackagePanel} for {@code ds.PackageQuickSort}.
 */
public class PackagePanelReflectionTest {

    @Test
    @DisplayName("sortPackagesWithReflection sorts packages by price ascending and descending")
    public void testSortPackagesByPriceReflection() {
        Flight f = new Flight("F01", "NYC", "08:00", "Paris", "16:00", 200.0);
        Hotel h1 = new Hotel("H01", "Hotel Budget", "Paris", 3.5, 100.0); // total 300.0
        Hotel h2 = new Hotel("H02", "Hotel Luxury", "Paris", 4.8, 500.0); // total 700.0
        Hotel h3 = new Hotel("H03", "Hotel Mid", "Paris", 4.0, 250.0);    // total 450.0

        List<PackageDeal> list = new ArrayList<>();
        list.add(new PackageDeal(f, h2));
        list.add(new PackageDeal(f, h1));
        list.add(new PackageDeal(f, h3));

        // Ascending
        PackagePanel.sortPackagesWithReflection(list, "PRICE", true);
        assertEquals(300.0, list.get(0).getTotalPrice());
        assertEquals(450.0, list.get(1).getTotalPrice());
        assertEquals(700.0, list.get(2).getTotalPrice());

        // Descending
        PackagePanel.sortPackagesWithReflection(list, "PRICE", false);
        assertEquals(700.0, list.get(0).getTotalPrice());
        assertEquals(450.0, list.get(1).getTotalPrice());
        assertEquals(300.0, list.get(2).getTotalPrice());
    }

    @Test
    @DisplayName("sortPackagesWithReflection sorts packages by rating ascending and descending")
    public void testSortPackagesByRatingReflection() {
        Flight f = new Flight("F01", "NYC", "08:00", "Paris", "16:00", 200.0);
        Hotel h1 = new Hotel("H01", "Hotel Budget", "Paris", 3.2, 100.0);
        Hotel h2 = new Hotel("H02", "Hotel Luxury", "Paris", 4.9, 500.0);
        Hotel h3 = new Hotel("H03", "Hotel Mid", "Paris", 4.1, 250.0);

        List<PackageDeal> list = new ArrayList<>();
        list.add(new PackageDeal(f, h1));
        list.add(new PackageDeal(f, h2));
        list.add(new PackageDeal(f, h3));

        // Descending (higher rating first)
        PackagePanel.sortPackagesWithReflection(list, "RATING", false);
        assertEquals(4.9, list.get(0).getHotelRating());
        assertEquals(4.1, list.get(1).getHotelRating());
        assertEquals(3.2, list.get(2).getHotelRating());

        // Ascending (lower rating first)
        PackagePanel.sortPackagesWithReflection(list, "RATING", true);
        assertEquals(3.2, list.get(0).getHotelRating());
        assertEquals(4.1, list.get(1).getHotelRating());
        assertEquals(4.9, list.get(2).getHotelRating());
    }

    @Test
    @DisplayName("sortPackagesWithReflection gracefully handles null and empty lists")
    public void testSortPackagesNullAndEmpty() {
        assertDoesNotThrow(() -> PackagePanel.sortPackagesWithReflection(null, "PRICE", true));
        List<PackageDeal> empty = new ArrayList<>();
        assertDoesNotThrow(() -> PackagePanel.sortPackagesWithReflection(empty, "PRICE", true));
        assertTrue(empty.isEmpty());
    }
}
