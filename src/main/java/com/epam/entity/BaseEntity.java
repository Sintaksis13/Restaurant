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

    BaseEntity() {
    }

    BaseEntity(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }

    @SuppressWarnings("unchecked")
    public T setId(long id) {
        this.id = id;
        return (T) this;
    }

    public long getId() {
        return id;
    }
}
