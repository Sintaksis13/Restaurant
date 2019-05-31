package com.epam.dao.idao;

import com.epam.entity.Dish;

import java.util.List;

public interface DishDao {
    void saveDish(Dish dish);

    List<Dish> findAllDishes();

    void deleteDishByName(String name);

    Dish findDishByName(String name);

    void updateDish(Dish dish);
}
