package com.epam.command.impcommand;

import com.epam.action.DishAction;
import com.epam.command.ActionCommand;
import com.epam.entity.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.constants.NameConstants.*;
import static com.epam.constants.PageConstants.DISH_EDIT_PAGE;

public class AddDishCommand implements ActionCommand {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
            .getString("add_dish_success_message");
    private static final String FAIL_MESSAGE = ResourceBundle.getBundle("language")
            .getString("add_dish_fail_message");

    private DishAction dishAction = new DishAction();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(DISH_NAME);
        String description = request.getParameter(DISH_DESC);
        double price = Double.parseDouble(request.getParameter(DISH_PRICE));

        if (dishAction.findDish(name) == null) {
            Dish dish = new Dish();
            dish.setName(name);
            dish.setDescription(description);
            dish.setPrice(price);

            dishAction.addNewDish(dish);

            request.setAttribute(MESSAGE, SUCCESS_MESSAGE);
        } else {
            request.setAttribute(MESSAGE, FAIL_MESSAGE);
        }

        List<Dish> dishList = dishAction.viewMenu();

        request.setAttribute(DISHES, dishList);

        request.getRequestDispatcher(DISH_EDIT_PAGE).forward(request, response);
    }
}
