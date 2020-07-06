package com.app.model;



public class IndexValue<T> {
    public final int index;
    public final T value;

    public IndexValue(int index, T value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }
}
