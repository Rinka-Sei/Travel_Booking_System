package ds;

import model.Flight;
import model.Hotel;
import model.PackageDeal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests Item 9 package QuickSort ordering.
 */
class PackageQuickSortTest {

    private final Flight flight = new Flight("F1", "A", "08:00", "B", "10:00", 100.0);

    @Test
    void sortsPackagesByPriceInBothDirections() {
        List<PackageDeal> packages = new ArrayList<>(List.of(
                new PackageDeal(flight, new Hotel("H1", "One", "B", 4.0, 300.0)),
                new PackageDeal(flight, new Hotel("H2", "Two", "B", 5.0, 100.0)),
                new PackageDeal(flight, new Hotel("H3", "Three", "B", 3.0, 200.0))
        ));

        PackageQuickSort.sortByPrice(packages, 0, packages.size() - 1, true);
        assertEquals(200.0, packages.get(0).getTotalPrice());
        assertEquals(400.0, packages.get(2).getTotalPrice());

        PackageQuickSort.sortByPrice(packages, 0, packages.size() - 1, false);
        assertEquals(400.0, packages.get(0).getTotalPrice());
        assertEquals(200.0, packages.get(2).getTotalPrice());
    }

    @Test
    void sortsPackagesByRating() {
        List<PackageDeal> packages = new ArrayList<>(List.of(
                new PackageDeal(flight, new Hotel("H1", "One", "B", 4.0, 300.0)),
                new PackageDeal(flight, new Hotel("H2", "Two", "B", 5.0, 100.0)),
                new PackageDeal(flight, new Hotel("H3", "Three", "B", 3.0, 200.0))
        ));

        PackageQuickSort.sortByRating(packages, 0, packages.size() - 1, false);
        assertEquals(5.0, packages.get(0).getHotelRating());
        assertEquals(3.0, packages.get(2).getHotelRating());
    }
}
