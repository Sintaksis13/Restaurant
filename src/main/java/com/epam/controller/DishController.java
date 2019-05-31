package com.epam.controller;

import com.epam.entity.Dish;
import com.epam.service.HiberDishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DishController {
    private final HiberDishService dishService;

    public DishController(HiberDishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Rinat");

        return "welcome";
    }

    @RequestMapping("/addDish")
    public String addDish(
            Model model,
            @RequestParam(name = "name") String dishName,
            @RequestParam(name = "description") String dishDescription,
            @RequestParam(name = "price") Double dishPrice
    ) {
        Dish dish = new Dish(dishName, dishDescription, dishPrice);
        dishService.saveDish(dish);
        model.addAttribute("dishes", dishService.findAllDishes());
        return "welcome";
    }
}
