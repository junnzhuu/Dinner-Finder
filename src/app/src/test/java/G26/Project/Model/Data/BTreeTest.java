package G26.Project.Model.Data;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BTreeTest {

    private BTree<Integer> bTree;

    @Before
    public void setUp() {
        bTree = new BTree<>();
    }

    @Test
    public void testInsertAndSearch() {
        // Insert keys into the B-tree.
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(5);
        bTree.insert(15);
        bTree.insert(25);
        bTree.insert(35);

        // Search for keys in the B-tree.
        List<Integer> searchResults = bTree.searchWithPredicate(k -> k >= 20, (k1, k2) -> k1 >= k2);
        assertEquals(2, searchResults.size());
        assertTrue(searchResults.contains(20));
        assertTrue(searchResults.contains(25));
        assertFalse(searchResults.contains(10));
        assertFalse(searchResults.contains(5));
        assertFalse(searchResults.contains(15));

        // Search for non-existent keys.
        List<Integer> nonExistentResults = bTree.searchWithPredicate(k -> k > 40, (k1, k2) -> k1 >= k2);
        assertTrue(nonExistentResults.isEmpty());
    }

    @Test
    public void testInsertAndUpdate() {
        // Insert keys into the B-tree.
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);

        // Update a key.
        boolean updateResult = bTree.update(20);
        assertTrue(updateResult);

        // Verify that the key has been updated.
        List<Integer> list = bTree.toList();
        assertEquals(3, list.size());
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertTrue(list.contains(30));
    }

    @Test
    public void testInsertAndUpdateNonExistent() {
        // Insert keys into the B-tree.
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);

        // Update a non-existent key.
        boolean updateResult = bTree.update(40);
        assertFalse(updateResult);

        // Verify that the B-tree remains unchanged.
        List<Integer> list = bTree.toList();
        assertEquals(3, list.size());
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertTrue(list.contains(30));
    }

    @Test
    public void testToList() {
        // Insert keys into the B-tree.
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);

        // Convert the B-tree to a sorted list.
        List<Integer> list = bTree.toList();
        assertEquals(3, list.size());
        assertEquals(10, (int) list.get(0));
        assertEquals(20, (int) list.get(1));
        assertEquals(30, (int) list.get(2));
    }
}
