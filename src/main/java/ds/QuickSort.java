package ds;

import model.Hotel;
import model.PackageDeal;

import java.util.List;

/**
 * Backward-compatible alias for the Item 9 {@link PackageQuickSort}.
 */
public final class QuickSort {

    private QuickSort() {
    }

    /**
     * Sorts package deals by price ascending.
     *
     * @param packages package list
     * @param low lower index
     * @param high upper index
     */
    public static void sortByPrice(List<PackageDeal> packages, int low, int high) {
        sortByPrice(packages, low, high, true);
    }

    /**
     * Sorts package deals by price.
     *
     * @param packages package list
     * @param low lower index
     * @param high upper index
     * @param ascending sort direction
     */
    public static void sortByPrice(List<PackageDeal> packages, int low, int high, boolean ascending) {
        PackageQuickSort.sortByPrice(packages, low, high, ascending);
    }

    /**
     * Sorts package deals by rating descending.
     *
     * @param packages package list
     * @param low lower index
     * @param high upper index
     */
    public static void sortByRating(List<PackageDeal> packages, int low, int high) {
        sortByRating(packages, low, high, false);
    }

    /**
     * Sorts package deals by rating.
     *
     * @param packages package list
     * @param low lower index
     * @param high upper index
     * @param ascending sort direction
     */
    public static void sortByRating(List<PackageDeal> packages, int low, int high, boolean ascending) {
        PackageQuickSort.sortByRating(packages, low, high, ascending);
    }

    /**
     * Sorts hotels by nightly price in ascending order.
     *
     * @param hotels hotel list
     * @param low lower index
     * @param high upper index
     */
    static void sortHotelsByPrice(List<Hotel> hotels, int low, int high) {
        if (hotels == null || hotels.isEmpty() || low < 0 || high >= hotels.size() || low >= high) {
            return;
        }
        int boundary = low - 1;
        double pivot = hotels.get(high).getPricePerNight();
        for (int index = low; index < high; index++) {
            if (hotels.get(index).getPricePerNight() <= pivot) {
                Hotel temporary = hotels.get(++boundary);
                hotels.set(boundary, hotels.get(index));
                hotels.set(index, temporary);
            }
        }
        Hotel temporary = hotels.get(boundary + 1);
        hotels.set(boundary + 1, hotels.get(high));
        hotels.set(high, temporary);
        sortHotelsByPrice(hotels, low, boundary);
        sortHotelsByPrice(hotels, boundary + 2, high);
    }
}
