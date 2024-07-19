package G26.Project.Model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Generic B-tree class used for storing the search items, support optimized range query.
 * The Key should be comparable (extends Comparable<T>)
 * <p>
 *      This implementation supports insert, modify specified key, and inorder traversal
 *
 * @param <T> The type of data that the B-tree will store. It must be comparable to enable sorting.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *          UID : u7533831
 */
public class BTree<T extends Comparable<T>> {
    /**
     * Degree of the B-tree. It determines the minimum and maximum number of children a node can have.
     */
    private static final int T = 2;
    /**
     * The root node of the B-tree.
     */
    private Node root;

    /**
     * Represents a node in the B-tree.
     */
    private class Node {
        /**
         * The number of keys currently in the node.
         */
        public int count;
        /**
         * An array to store keys.
         */
        Object[] keys;
        /**
         * An array to store child nodes.
         */
        Object[] childrenArray;
        /**
         * Constructs a new node with appropriate arrays for keys and children.
         */
        Node() {
            keys = new Object[2 * T - 1];
            childrenArray = new Object[2 * T];
        }
        /**
         * Checks if the node is a leaf node (has no children).
         *
         * @return true if the node is a leaf, false otherwise.
         */
        boolean isLeaf() {
            return getChild(0) == null;
        }

        @SuppressWarnings("unchecked")
        T getKey(int index) {
            return (T) keys[index];
        }

        void setKey(int index, T value) {
            keys[index] = value;
        }

        @SuppressWarnings("unchecked")
        Node getChild(int index) {
            return (Node) childrenArray[index];
        }

        void setChild(int index, Node child) {
            childrenArray[index] = child;
        }
    }

    /**
     * Constructs a new B-tree with an initial empty root node.
     */
    public BTree() {
        root = new Node();
        root.count = 0;
    }

    /**
     * Searches for keys in the B-tree based on a provided predicate and range check.
     * <p>
     * This method is particularly useful for querying restaurant data based on specified criteria,
     * such as cost, ratings, or other attributes.
     *
     * @param predicate  The predicate to filter keys (e.g., cost less than a certain value).
     * @param rangeCheck The range check function to determine whether to explore child nodes
     *                   (e.g., to find keys within a specific cost range).
     * @return A list of keys that satisfy the given predicate within the specified range.
     */
    public List<T> searchWithPredicate(Predicate<T> predicate, BiFunction<T, T, Boolean> rangeCheck) {
        List<T> results = new ArrayList<>();
        searchWithPredicate(root, predicate, rangeCheck, results);
        return results;
    }

    /**
     * Recursively searches for keys in the B-tree based on a provided predicate and range check.
     *
     * @param node      The current node being processed.
     * @param predicate The predicate to filter keys.
     * @param rangeCheck The range check function to determine whether to explore child nodes.
     * @param results   A list to store keys that satisfy the predicate within the specified range.
     */
    private void searchWithPredicate(Node node, Predicate<T> predicate, BiFunction<T, T, Boolean> rangeCheck, List<T> results) {
        if (node == null) return;

        for (int i = 0; i < node.count; i++) {
            T currentKey = node.getKey(i);
            // If the key satisfies the predicate, add it to results.
            if (predicate.test(currentKey)) {
                results.add(currentKey);
            }
            // For cost-based search, decide whether to go down this child based on the range check.
            // If the current key and its left child might contain valid entries based on the range, dive into the left child.
            if (rangeCheck.apply(currentKey, node.getKey(0))) { // Comparing the currentKey and the leftmost key of the node to decide whether to explore the left child.
                searchWithPredicate(node.getChild(i), predicate, rangeCheck, results);
            }
            // At this point, if the current key is beyond our desired range (e.g., if we're searching for cost < x and currentKey's cost is >= x),
            // then we know that all subsequent keys (and their right children) will also be outside of our range.
            if (!rangeCheck.apply(currentKey, node.getKey(node.count - 1))) { // Compare the currentKey and the rightmost key of the node.
                return;
            }
        }
        // Finally, always check the far right child of the node as it could also contain valid entries.
        searchWithPredicate(node.getChild(node.count), predicate, rangeCheck, results);
    }

    /**
     * Inserts a new key into the B-tree.
     *
     * @param key The key to insert.
     */
    public void insert(T key) {
        Node r = root;
        if (r.count == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.setChild(0, r);
            splitChild(s, 0);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    /**
     * Splits a child node during insertion to maintain the B-tree properties.
     *
     * @param x The parent node.
     * @param i The index of the child node to split.
     */
    private void splitChild(Node x, int i) {
        Node z = new Node();
        Node y = x.getChild(i); // Get the child node being split (left sibling).
        z.count = T - 1; // Set the count of the new node (z) to T - 1.
        for (int j = 0; j < T - 1; j++) { // Copy the keys from the original node (y) to the new node (z).
            z.setKey(j, y.getKey(j + T));
        }
        if (!y.isLeaf()) {  // If the original node (y) is not a leaf, copy its children as well.
            for (int j = 0; j < T; j++) {
                z.setChild(j, y.getChild(j + T));
            }
        }
        y.count = T - 1; // Reduce the count of the original node (y).
        for (int j = x.count; j > i; j--) { // Shift the children of the parent node (x) to make space for the new node (z).
            x.setChild(j + 1, x.getChild(j));
        }
        x.setChild(i + 1, z);
        for (int j = x.count - 1; j >= i; j--) { // Shift the keys of the parent node (x) to make space for the key from the original node (y).
            x.setKey(j + 1, x.getKey(j));
        }
        x.setKey(i, y.getKey(T - 1));
        x.count++;
    }

    /**
     * Inserts a key into a non-full node during insertion.
     *
     * @param x The node where the key will be inserted.
     * @param k The key to insert.
     */
    private void insertNonFull(Node x, T k) {
        int i = x.count - 1;
        if (x.isLeaf()) {
            // If the node is a leaf, shift keys to make space for the new key.
            while (i >= 0 && k.compareTo(x.getKey(i)) < 0) {
                x.setKey(i + 1, x.getKey(i));
                i--;
            }
            // Insert the new key at the appropriate position and increment the count.
            x.setKey(i + 1, k);
            x.count++;
        } else { // If the node is not a leaf, find the child where the key should be inserted.
            while (i >= 0 && k.compareTo(x.getKey(i)) < 0) {
                i--;
            }
            i++;
            if (x.getChild(i).count == 2 * T - 1) { // Check if the selected child is full; if so, split it.
                splitChild(x, i);
                if (k.compareTo(x.getKey(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(x.getChild(i), k);
        }
    }

    /**
     * Updates an existing key in the B-tree with a new value.
     *
     * @param updatedKey The new key to replace the existing key.
     * @return true if the update is successful; otherwise, false.
     */
    public boolean update(T updatedKey) {
        Node node = getNode(root, updatedKey);
        if (node != null) {
            int i;
            for (i = 0; i < node.count; i++) {
                if (updatedKey.equals(node.getKey(i))) {
                    node.setKey(i, updatedKey);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper function of 'update' to recursively retrieves the node containing a specific key.
     *
     * @param node The current node being processed.
     * @param key  The key to search for.
     * @return The node containing the key if found; otherwise, null.
     */
    private Node getNode(Node node, T key) {
        if (node == null) {
            return null;
        }

        int i = 0;
        while (i < node.count && key.compareTo(node.getKey(i)) > 0) {
            i++;
        }
        if (i < node.count && key.equals(node.getKey(i))) {
            return node;
        } else if (node.isLeaf()) {
            return null;
        } else {
            return getNode(node.getChild(i), key);
        }
    }

    /**
     * Performs an in-order traversal of the B-tree and populates a list with the keys in sorted order.
     *
     * @param x    The current node being processed.
     * @param list The list to store the keys in sorted order.
     */
    private void inorderTraversal(Node x, List<T> list) {
        if (x != null) {
            for (int i = 0; i < x.count; i++) {
                inorderTraversal(x.getChild(i), list);
                list.add(x.getKey(i));  // getKey method already returns type T
            }
            inorderTraversal(x.getChild(x.count), list);
        }
    }

    /**
     * Converts the B-tree into a sorted list by performing an in-order traversal.
     *
     * @return A list containing all keys in the B-tree in sorted order.
     */
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }
}
