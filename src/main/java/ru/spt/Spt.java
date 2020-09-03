package ru.spt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Spt<K, V> {

    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public Future<V> compute(K k, Function<K, V> f) {
        return executor.submit(() -> cache.computeIfAbsent(k, f));
    }
}
