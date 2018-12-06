package com.epam.entity;

public abstract class BaseEntity {
    private int id;

    @Override
    public String toString() {
        return getClass().getName() + " " + id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
