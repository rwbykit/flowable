package rwbykit.flowable.engine.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {

    public static <K, V> LambdaMap<K, V> lambdaMap() {
        return new LambdaMap<K, V>();
    }

    public static class LambdaMap<K, V> {
        private Map<K, V> valueMap;

        protected LambdaMap() {
            this.valueMap = new HashMap<>();
        }

        public LambdaMap<K, V> add(K key, V value) {
            this.valueMap.put(key, value);
            return this;
        }

        public LambdaMap<K, V> hashMap() {
            this.valueMap = new HashMap<>(this.valueMap);
            return this;
        }

        public LambdaMap<K, V> concurrentHashMap() {
            this.valueMap = new ConcurrentHashMap<>(this.valueMap);
            return this;
        }

        public LambdaMap<K, V> immutable() {
            this.valueMap = Collections.unmodifiableMap(this.valueMap);
            return this;
        }

        @SuppressWarnings("unchecked")
        public <Key, Value> Map<Key, Value> get() {
            return (Map<Key, Value>) this.valueMap;
        }

    }

    public static <K, V> Map<K, V> cloneMap(Map<K, V> sourceMap) {
        Map<K, V> targetMap = new ConcurrentHashMap<>(8);
        if (Objects.nonNull(sourceMap)) {
            targetMap.putAll(sourceMap);
        }
        return targetMap;
    }

    public static <K, V> Map<K, V> newConcurrentHashMap(int size) {
        return new ConcurrentHashMap<K, V>(size);
    }

    public static <K, V> Map<K, V> emptyMap() {
        return java.util.Collections.emptyMap();
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m) {
        return java.util.Collections.unmodifiableMap(m);
    }

}
