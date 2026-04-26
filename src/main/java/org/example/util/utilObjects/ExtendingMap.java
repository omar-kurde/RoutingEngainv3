package org.example.util.utilObjects;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExtendingMap<K, V> extends AbstractMap<K, V> {

    private final Map<K, V> firstPart;
    private final Map<K, V> secondPart;

    public ExtendingMap(Map<K, V> firstPart) {
        this.firstPart = firstPart;
        this.secondPart = new HashMap<>();
    }
    @Override
    public V getOrDefault(Object key, V defaultValue) {

        if (firstPart.containsKey(key)) {
            return firstPart.get(key);
        }

        if (secondPart.containsKey(key)) {
            return secondPart.get(key);
        }

        return defaultValue;
    }
    @Override
    public V computeIfAbsent(K key, java.util.function.Function<? super K, ? extends V> mappingFunction) {

        // إذا موجود في firstPart → رجعه وما نغير شيء
        if (firstPart.containsKey(key)) {
            return firstPart.get(key);
        }

        // إذا موجود في secondPart → رجعه
        if (secondPart.containsKey(key)) {
            return secondPart.get(key);
        }

        // إذا مش موجود → احسب وخزّن في secondPart
        V value = mappingFunction.apply(key);
        secondPart.put(key, value);

        return value;
    }

    @Override
    public V get(Object key) {
        if (firstPart.containsKey(key)) {
            return firstPart.get(key);
        }
        return secondPart.get(key);
    }

    @Override
    public V put(K key, V value) {
        return secondPart.put(key, value);
    }

    @Override
    public boolean containsKey(Object key) {
        return firstPart.containsKey(key) || secondPart.containsKey(key);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Map<K, V> merged = new HashMap<>();
        merged.putAll(firstPart);
        merged.putAll(secondPart);
        return merged.entrySet();
    }
}