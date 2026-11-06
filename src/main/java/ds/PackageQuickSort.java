package ds;

import model.PackageDeal;

import java.util.List;

/**
 * Hand-written QuickSort implementation for Item 9 package deals.
 *
 * <p>Average time complexity is O(n log n), worst-case time complexity is
 * O(n^2), and average recursion space is O(log n).</p>
 */
public final class PackageQuickSort {

    private PackageQuickSort() {
    }

    /**
     * Sorts package deals by total price.
     *
     * @param packages package list
     * @param low lower inclusive index
     * @param high upper inclusive index
     * @param ascending whether lower prices come first
     */
    public static void sortByPrice(List<PackageDeal> packages, int low, int high, boolean ascending) {
        if (validRange(packages, low, high)) {
            int pivot = partitionByPrice(packages, low, high, ascending);
            sortByPrice(packages, low, pivot - 1, ascending);
            sortByPrice(packages, pivot + 1, high, ascending);
        }
    }

    /**
     * Sorts package deals by hotel rating.
     *
     * @param packages package list
     * @param low lower inclusive index
     * @param high upper inclusive index
     * @param ascending whether lower ratings come first
     */
    public static void sortByRating(List<PackageDeal> packages, int low, int high, boolean ascending) {
        if (validRange(packages, low, high)) {
            int pivot = partitionByRating(packages, low, high, ascending);
            sortByRating(packages, low, pivot - 1, ascending);
            sortByRating(packages, pivot + 1, high, ascending);
        }
    }

    private static boolean validRange(List<PackageDeal> packages, int low, int high) {
        return packages != null && !packages.isEmpty()
                && low >= 0 && high < packages.size() && low < high;
    }

    private static int partitionByPrice(List<PackageDeal> packages, int low, int high, boolean ascending) {
        double pivotValue = packages.get(high).getTotalPrice();
        int boundary = low - 1;
        for (int index = low; index < high; index++) {
            if (comesBefore(packages.get(index).getTotalPrice(), pivotValue, ascending)) {
                swap(packages, ++boundary, index);
            }
        }
        swap(packages, boundary + 1, high);
        return boundary + 1;
    }

    private static int partitionByRating(List<PackageDeal> packages, int low, int high, boolean ascending) {
        double pivotValue = packages.get(high).getHotelRating();
        int boundary = low - 1;
        for (int index = low; index < high; index++) {
            if (comesBefore(packages.get(index).getHotelRating(), pivotValue, ascending)) {
                swap(packages, ++boundary, index);
            }
        }
        swap(packages, boundary + 1, high);
        return boundary + 1;
    }

    private static boolean comesBefore(double value, double pivot, boolean ascending) {
        return ascending ? value <= pivot : value >= pivot;
    }

    private static void swap(List<PackageDeal> packages, int first, int second) {
        PackageDeal temporary = packages.get(first);
        packages.set(first, packages.get(second));
        packages.set(second, temporary);
    }
}
