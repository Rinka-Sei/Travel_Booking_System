package service;

import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying reflection-driven hash table caching and fallbacks in {@link CustomerRegistryService}.
 */
public class CustomerRegistryServiceTest {

    @Test
    @DisplayName("CustomerRegistryService instantiates reflected CustomerHashTable cache")
    public void testCustomerHashTableReflectionInstantiation() {
        CustomerRegistryService registryService = new CustomerRegistryService();
        Object tableInstance = registryService.getCustomerHashTable();

        assertNotNull(tableInstance, "CustomerHashTable should be instantiated via reflection");
        assertEquals("ds.CustomerHashTable", tableInstance.getClass().getName());
    }

    @Test
    @DisplayName("cacheUser and getCachedUser operate correctly using reflected hash table")
    public void testCacheAndGetCachedUser() {
        CustomerRegistryService registryService = new CustomerRegistryService();
        User user1 = new User("alice", "pass123", "CUSTOMER");
        User user2 = new User("bob", "pass456", "CUSTOMER");

        registryService.cacheUser(user1);
        registryService.cacheUser(user2);

        User cachedAlice = registryService.getCachedUser("alice");
        assertNotNull(cachedAlice);
        assertEquals("alice", cachedAlice.getUsername());
        assertEquals("pass123", cachedAlice.getPassword());
        assertEquals("CUSTOMER", cachedAlice.getRole());

        User cachedBob = registryService.getCachedUser("bob");
        assertNotNull(cachedBob);
        assertEquals("bob", cachedBob.getUsername());

        assertNull(registryService.getCachedUser("non_existent"));
        assertNull(registryService.getCachedUser(null));
    }

    @Test
    @DisplayName("cacheUser gracefully ignores null input")
    public void testCacheNullUser() {
        CustomerRegistryService registryService = new CustomerRegistryService();
        assertDoesNotThrow(() -> registryService.cacheUser(null));
    }
}
