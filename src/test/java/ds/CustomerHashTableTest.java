package ds;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Item 9 customer hash table CRUD and collision handling.
 */
class CustomerHashTableTest {

    @Test
    void storesRetrievesAndReplacesCustomers() {
        CustomerHashTable table = new CustomerHashTable(4);
        User original = new User("alice", "old", "CUSTOMER");
        User replacement = new User("alice", "new", "CUSTOMER");

        table.put("alice", original);
        assertSame(original, table.get("alice"));

        table.put("alice", replacement);
        assertSame(replacement, table.get("alice"));
    }

    @Test
    void handlesSeparateChainingAndRemoval() {
        CustomerHashTable table = new CustomerHashTable(1);
        User first = new User("first", "p1", "CUSTOMER");
        User second = new User("second", "p2", "CUSTOMER");

        table.put("first", first);
        table.put("second", second);

        assertSame(first, table.get("first"));
        assertSame(second, table.get("second"));
        assertSame(first, table.remove("first"));
        assertNull(table.get("first"));
        assertSame(second, table.get("second"));
    }

    @Test
    void rejectsInvalidCapacityAndHandlesMissingKeys() {
        assertThrows(IllegalArgumentException.class, () -> new CustomerHashTable(0));
        CustomerHashTable table = new CustomerHashTable(2);
        assertNull(table.get("missing"));
        assertNull(table.remove("missing"));
    }
}
