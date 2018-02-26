package com.epam.command.impcommand;

import com.epam.action.DishAction;
import com.epam.command.ActionCommand;
import com.epam.entity.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static com.epam.constants.NameConstants.DISH;
import static com.epam.constants.NameConstants.MESSAGE;
import static com.epam.constants.PageConstants.DISH_EDIT_PAGE;
import static com.epam.constants.PageConstants.DISH_UPDATE_PAGE;

public class UpdateDishCommand implements ActionCommand {
    private static final String FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                            .getString("update_dish_fail_message");
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(DISH);

        Dish dish = new DishAction().findDish(name);

        if (dish != null) {
            request.setAttribute(DISH, dish);
            request.getRequestDispatcher(DISH_UPDATE_PAGE).forward(request, response);
        } else {
            request.setAttribute(MESSAGE, FAIL_MESSAGE);
            request.getRequestDispatcher(DISH_EDIT_PAGE).forward(request, response);
        }
    }
}
