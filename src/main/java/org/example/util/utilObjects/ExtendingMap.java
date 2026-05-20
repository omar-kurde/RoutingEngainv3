package org.example.util.utilObjects;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;


import java.util.*;
import java.util.function.Function;

public class ExtendingMap<K, V> extends AbstractMap<K, V> {

    private final Map<K, V> firstPart;
    private final Map<K, V> secondPart;
    private final BiFunction<V, V, V> merger;

    public ExtendingMap(
            Map<K, V> firstPart,
            BiFunction<V, V, V> merger
    ) {
        this.firstPart = firstPart;
        this.secondPart = new HashMap<>();
        this.merger = merger;
    }

    @Override
    public V get(Object key) {

        boolean inFirst = firstPart.containsKey(key);
        boolean inSecond = secondPart.containsKey(key);

        if (inFirst && inSecond) {
            return merger.apply(
                    firstPart.get(key),
                    secondPart.get(key)
            );
        }

        if (inFirst) {
            return firstPart.get(key);
        }

        return secondPart.get(key);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {

        V value = get(key);

        return value != null
                ? value
                : defaultValue;
    }

    @Override
    public V put(K key, V value) {

        if (firstPart.containsKey(key)) {

            V merged = merger.apply(
                    firstPart.get(key),
                    value
            );

            secondPart.put(key, merged);

            return merged;
        }

        return secondPart.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        for (Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public V computeIfAbsent(
            K key,
            Function<? super K, ? extends V> mappingFunction
    ) {

        if (containsKey(key)) {
            return get(key);
        }

        V value = mappingFunction.apply(key);

        secondPart.put(key, value);

        return value;
    }

    @Override
    public V remove(Object key) {

        return secondPart.remove(key);
    }

    @Override
    public void clear() {

        secondPart.clear();
    }

    @Override
    public boolean containsKey(Object key) {

        return firstPart.containsKey(key)
                || secondPart.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {

        return values().contains(value);
    }

    @Override
    public int size() {

        Set<K> keys = new HashSet<>();

        keys.addAll(firstPart.keySet());
        keys.addAll(secondPart.keySet());

        return keys.size();
    }

    @Override
    public boolean isEmpty() {

        return firstPart.isEmpty()
                && secondPart.isEmpty();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {

        Map<K, V> merged = new HashMap<>();

        Set<K> keys = new HashSet<>();

        keys.addAll(firstPart.keySet());
        keys.addAll(secondPart.keySet());

        for (K key : keys) {
            merged.put(key, get(key));
        }

        return merged.entrySet();
    }
}