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

    // =========================
    // READ (merge both)
    // =========================
    @Override
    public V get(Object key) {

        V base = firstPart.get(key);
        V update = secondPart.get(key);

        if (base != null && update != null) {
            return merger.apply(base, update);
        }

        return base != null ? base : update;
    }


    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return secondPart.getOrDefault(key, defaultValue);
    }

    // =========================
    // WRITE (ONLY secondPart)
    // =========================
    @Override
    public V put(K key, V value) {
        return secondPart.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        secondPart.putAll(m);
    }


    // =========================
    // DELETE (only secondPart)
    // =========================
    @Override
    public V remove(Object key) {
        return secondPart.remove(key);
    }

    @Override
    public void clear() {
        secondPart.clear();
    }

    // =========================
    // CHECKS
    // =========================
    @Override
    public boolean containsKey(Object key) {
        return secondPart.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    // =========================
    // SIZE (merged keys)
    // =========================
    @Override
    public int size() {
        Set<K> keys = new HashSet<>();
        keys.addAll(firstPart.keySet());
        keys.addAll(secondPart.keySet());
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return firstPart.isEmpty() && secondPart.isEmpty();
    }

    // =========================
    // VIEW (merged read-only)
    // =========================
    @Override
    public Set<Entry<K, V>> entrySet() {

        Map<K, V> merged = new HashMap<>();

        for (K key : firstPart.keySet()) {
            merged.put(key, get(key));
        }

        for (K key : secondPart.keySet()) {
            merged.put(key, get(key));
        }

        return merged.entrySet();
    }
}