package ds;

/**
 * Backward-compatible alias for the Item 9 {@link CustomerHashTable}.
 *
 * <p>Existing authentication callers can continue using the historical name
 * while the canonical DSA contract is exposed as {@code CustomerHashTable}.</p>
 */
public class UserHashTable extends CustomerHashTable {

    /**
     * Creates a user hash table with the requested bucket capacity.
     *
     * @param capacity number of buckets
     */
    public UserHashTable(int capacity) {
        super(capacity);
    }
}
