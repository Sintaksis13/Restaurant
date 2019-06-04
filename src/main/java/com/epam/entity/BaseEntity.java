package com.epam.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public BaseEntity() {
    }

    public BaseEntity(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + id;
    }

    public T setId(long id) {
        this.id = id;
        return (T) this;
    }

    public long getId() {
        return id;
    }
}
