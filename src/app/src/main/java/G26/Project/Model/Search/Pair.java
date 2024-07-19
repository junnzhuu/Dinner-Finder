package G26.Project.Model.Search;

import androidx.annotation.NonNull;

/**
 * Represents a generic key-value pair.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 * <p>
 * {@code @Author: Jiawei Liu}
 *           UID : u7603069
 * {@code @Contributor: Jing Li (Contribute to line 38)}
 */
public class Pair<K, V> {
    private final K key;
    private final V value;
    /**
     * Constructs a new Pair with the specified key and value.
     *
     * @param key   The key of the pair.
     * @param value The value of the pair.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return "<" + key + ", " + value + ">";
    }

}
