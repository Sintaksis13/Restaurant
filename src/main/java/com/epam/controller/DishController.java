package com.epam.controller;

import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.service.HiberDishService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DishController {
    private static final Logger LOG = LogManager.getLogger(DishController.class);
    private final HiberDishService dishService;

    public DishController(HiberDishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping("/addDish")
    public String addDish(
            Model model,
            @RequestParam(name = "dishName") String dishName,
            @RequestParam(name = "dishDescription") String dishDescription,
            @RequestParam(name = "dishPrice") Double dishPrice
    ) {
        LOG.debug("Start...");
        Dish dish;
        try {
            dish = new Dish(dishName, dishDescription, dishPrice);
            dishService.saveDish(dish);
        } catch (DaoException e) {
            LOG.debug("Error occurred during saving dish = {}", dishName, e);
            return "errors/500";
        } catch (Exception e) {
            LOG.debug("Unexpected error, dishName = {}, dishDescription={}, dishPrice={}",
                    dishName, dishDescription, dishPrice, e);
            return "errors/500";
        }

        LOG.debug("Finish.");
        model.addAttribute("dish", dish);
        return "welcome";
    }
}
