package com.epam.entity;

public class Dish extends BaseEntity {

    private String name;
    private String description;
    private double price;

    @Override
    public String toString() {
        return super.toString() + " " + name + " " + description + " " + price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
