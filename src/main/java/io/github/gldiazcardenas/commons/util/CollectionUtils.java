package io.github.gldiazcardenas.commons.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility methods related to collections.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class CollectionUtils {

    private CollectionUtils() {
        super();
    }

    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }

    public static <T> Stream<T> parallelStream(Collection<T> collection) {
        return stream(collection).parallel();
    }

    public static <T, R> List<R> map(List<T> collection, Function<T, R> mapper) {
        return stream(collection).map(mapper).collect(Collectors.toList());
    }

    public static <T> List<T> filter(List<T> collection, Predicate<T> predicate) {
        return stream(collection).filter(predicate).collect(Collectors.toList());
    }

    public static <T> T first(Iterable<? extends T> iterable) {
        return iterable == null ? null : Iterables.getFirst(iterable, null);
    }

    public static <T> T first(T[] arr) {
        return get(arr, 0);
    }

    public static <T> T second(Iterable<? extends T> iterable) {
        final int second = 1;
        return iterable == null ? null : Iterables.get(iterable, second, null);
    }

    public static <T> T second(T[] arr) {
        return get(arr, 1);
    }

    public static <T> T last(Iterable<? extends T> iterable) {
        return iterable == null ? null : Iterables.getLast(iterable, null);
    }

    public static <T> T get(T[] arr, int index) {
        return index < size(arr) ? arr[index] : null;
    }

    public static <K, V> V getOrNull(Map<K, V> map, K key) {
        return map == null ? null : map.get(key);
    }

    public static <K> boolean containsKey(Map<K, ?> map, K key) {
        return map != null && map.containsKey(key);
    }

    public static <K, V> boolean contains(Map<K, V> map, K key, V value) {
        if (map == null) {
            return false;
        }
        V mappedValue = map.get(key);
        return Objects.equals(mappedValue, value) && map.containsKey(key);
    }

    public static <T> boolean isEmpty(Iterable<? extends T> iterable) {
        return iterable == null || Iterables.isEmpty(iterable);
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static int size(Collection<?> collection) {
        return collection != null ? collection.size() : 0;
    }

    public static <T> int size(T[] array) {
        return array == null ? 0 : array.length;
    }

    public static <T> Set<T> toSet(T[] array) {
        return isEmpty(array) ? Collections.emptySet() : Sets.newHashSet(array);
    }

    public static <K, U> Map<K, U> toMap(Function<U, K> keyMapper, List<U> items) {
        return stream(items).collect(Collectors.toMap(keyMapper, Function.identity()));
    }

}
