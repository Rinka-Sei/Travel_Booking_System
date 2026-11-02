package ds;

import model.User;

/**
 * Hand-written separate-chaining hash table for customer records.
 *
 * <p>Average insertion, lookup, and removal are O(1); the worst case is
 * O(n) when all keys share one bucket. Storage is O(n).</p>
 */
public class CustomerHashTable {

    /**
     * Entry stored in a collision chain.
     */
    private static final class HashNode {

        private final String key;
        private User value;
        private HashNode next;

        private HashNode(String key, User value, HashNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final HashNode[] buckets;

    /**
     * Creates a customer hash table.
     *
     * @param capacity number of buckets
     * @throws IllegalArgumentException when {@code capacity <= 0}
     */
    public CustomerHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        buckets = new HashNode[capacity];
    }

    /**
     * Inserts or replaces a customer record.
     *
     * @param key customer lookup key
     * @param value customer record
     */
    public void put(String key, User value) {
        if (key == null) {
            return;
        }
        int index = bucketIndex(key);
        HashNode current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        buckets[index] = new HashNode(key, value, buckets[index]);
    }

    /**
     * Retrieves a customer record.
     *
     * @param key customer lookup key
     * @return matching record, or {@code null} when absent
     */
    public User get(String key) {
        if (key == null) {
            return null;
        }
        HashNode current = buckets[bucketIndex(key)];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes a customer record.
     *
     * @param key customer lookup key
     * @return removed record, or {@code null} when absent
     */
    public User remove(String key) {
        if (key == null) {
            return null;
        }
        int index = bucketIndex(key);
        HashNode current = buckets[index];
        HashNode previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    private int bucketIndex(String key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }
}
