package com.app.model;

import lombok.Data;

@Data
public class IndexValue<T> {
    public final int index;
    public final T value;

    public IndexValue(int index, T value) {
        this.index = index;
        this.value = value;
    }
}
