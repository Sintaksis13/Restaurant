package com.epam.service;

import com.epam.entity.Dish;

import java.util.List;

public interface HiberDishService {
    void saveDish(Dish dish);

    List<Dish> findAllDishes();

    void deleteDishByName(String name);

    Dish findDishByName(String name);

    void updateDish(Dish dish);
}
