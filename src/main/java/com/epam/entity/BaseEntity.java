package com.epam.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
