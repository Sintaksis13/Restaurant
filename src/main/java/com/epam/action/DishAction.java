package com.epam.action;

import com.epam.entity.Dish;
import com.epam.service.impservice.DishService;

import java.util.List;

public class DishAction {
    private DishService dishService = new DishService();

    public List<Dish> viewMenu() {
        return dishService.getAll();
    }

    public int addNewDish(Dish dish) {
        return dishService.create(dish);
    }

    public Dish findDish(String name) {
        return dishService.getDish(name);
    }

    public int deleteDish(int id) {
        return dishService.delete(id);
    }

    public int updateDishInfo(Dish dish, String name) {
        return dishService.update(dish, name);
    }
}
