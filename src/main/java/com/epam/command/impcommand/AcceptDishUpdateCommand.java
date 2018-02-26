package com.epam.command.impcommand;

import com.epam.action.DishAction;
import com.epam.command.ActionCommand;
import com.epam.entity.Dish;
import com.epam.service.impservice.DishService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.constants.NameConstants.*;
import static com.epam.constants.PageConstants.DISH_EDIT_PAGE;

public class AcceptDishUpdateCommand implements ActionCommand {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
                                                                .getString("dish_update_success_message");
    private static final String FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                             .getString("dish_update_fail_message");
    public static final String OLD_NAME = "oldDishName";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(DISH_NAME);
        String description = request.getParameter(DISH_DESC);
        String oldName = request.getParameter(OLD_NAME);
        double price = Double.parseDouble(request.getParameter(DISH_PRICE));

        Dish dish = new Dish();

        dish.setName(name);
        dish.setDescription(description);
        dish.setPrice(price);

        DishAction dishAction = new DishAction();

        if (dishAction.updateDishInfo(dish, oldName) != 0) {
            request.setAttribute(MESSAGE, SUCCESS_MESSAGE);
        } else {
            request.setAttribute(MESSAGE, FAIL_MESSAGE);
        }

        List<Dish> dishes = new DishAction().viewMenu();

        request.setAttribute(DISHES, dishes);

        request.getRequestDispatcher(DISH_EDIT_PAGE).forward(request, response);
    }
}
