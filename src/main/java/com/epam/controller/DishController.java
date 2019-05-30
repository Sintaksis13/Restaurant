package com.epam.controller;

import com.epam.action.DishAction;
import com.epam.entity.Dish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ResourceBundle;

import static com.epam.constants.NameConstants.DISHES;
import static com.epam.constants.NameConstants.MESSAGE;
import static com.epam.constants.NumericConstants.UNCHANGED_ROWS;

@Controller
public class DishController {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
            .getString("add_dish_success_message");
    private static final String FAIL_MESSAGE = ResourceBundle.getBundle("language")
            .getString("add_dish_fail_message");

    private final DishAction dishAction = new DishAction();

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
            @RequestParam(name = "price") double dishPrice
    ) {
        if (dishAction.findDish(dishName) == null &&
                dishAction.addNewDish(new Dish(dishName, dishDescription, dishPrice)) != UNCHANGED_ROWS) {
            model.addAttribute(MESSAGE, SUCCESS_MESSAGE);
        } else {
            model.addAttribute(MESSAGE, FAIL_MESSAGE);
        }

        model.addAttribute(DISHES, dishAction.viewMenu());
        return "dishPage";
    }
}
