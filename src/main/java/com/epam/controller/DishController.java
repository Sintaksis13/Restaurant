package com.epam.controller;

import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.service.HiberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DishController {
    private static final Logger LOG = LogManager.getLogger(DishController.class);
    private final HiberService<Dish> dishService;

    public DishController(HiberService<Dish> dishService) {
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
        Dish dish = null;
        try {
            dish = new Dish(dishName, dishDescription, dishPrice);
            dishService.save(dish);
        } catch (DaoException e) {
            LOG.debug("Error occurred during saving dish = {}", dish, e);
            return "errors/500";
        } catch (Exception e) {
            LOG.debug("Unexpected error, dish = {}", dish, e);
            return "errors/500";
        }

        model.addAttribute("dishes", dishService.findAll());
        return "index_main";
    }

    @RequestMapping("/showAllDishes")
    public String getAllDishes(Model model) {
        model.addAttribute("dishes", dishService.findAll());
        return "index_main";
    }
}
