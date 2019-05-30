package com.epam.entity;

public abstract class BaseEntity<T> {
    private int id;

    @Override
    public String toString() {
        return getClass().getName() + " " + id;
    }

    public T setId(int id) {
        this.id = id;
        return (T) this;
    }

    public int getId() {
        return id;
    }
}
