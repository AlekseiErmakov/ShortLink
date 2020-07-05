package com.app.util;

import com.app.model.IndexValue;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {
    public static <T> Stream<IndexValue<T>> withIndexies(List<T> list) {
        return IntStream.range(0, list.size()).mapToObj(ind -> new IndexValue<>(ind, list.get(ind)));
    }
}
