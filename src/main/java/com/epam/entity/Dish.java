package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "dish")
public class Dish extends BaseEntity<Dish> {
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price", nullable = false)
    private Double price;

    public Dish(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish(long id, String name, String description, Double price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.price, price) == 0 &&
                name.equals(dish.name) &&
                Objects.equals(description, dish.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }

    @Override
    public String toString() {
        return "Dish {" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
