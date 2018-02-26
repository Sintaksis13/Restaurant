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

import static com.epam.constants.NameConstants.DISH;
import static com.epam.constants.NameConstants.DISHES;
import static com.epam.constants.NameConstants.MESSAGE;
import static com.epam.constants.PageConstants.DISH_EDIT_PAGE;

public class DeleteDishCommand implements ActionCommand {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
                                                                .getString("delete_dish_success_message");

    private DishAction dishAction = new DishAction();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(DISH));

        if (dishAction.deleteDish(id) != 0) {
            request.setAttribute(MESSAGE, SUCCESS_MESSAGE);
        }

        List<Dish> dishList = new DishAction().viewMenu();

        request.setAttribute(DISHES, dishList);

        request.getRequestDispatcher(DISH_EDIT_PAGE).forward(request, response);
    }
}
