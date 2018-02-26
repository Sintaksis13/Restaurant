package com.epam.command.impcommand;

import com.epam.action.DishAction;
import com.epam.command.ActionCommand;
import com.epam.entity.Dish;
import com.epam.entity.User;
import com.epam.entity.type.Role;
import com.epam.exception.CookieNotFoundException;
import com.epam.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.constants.ExceptionConstants.COOKIE_NOT_FOUND_EXCEPTION;
import static com.epam.constants.NameConstants.DISHES;
import static com.epam.constants.PageConstants.DISH_EDIT_PAGE;
import static com.epam.constants.PageConstants.MENU_PAGE;

public class MenuCommand implements ActionCommand {
    public static final Logger LOGGER = Logger.getLogger(MenuCommand.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dish> dishList = new DishAction().viewMenu();

        request.setAttribute(DISHES, dishList);

        User user = null;

        try {
            user = new Validator().checkCookie(request.getCookies());
        } catch (CookieNotFoundException ex) {
            LOGGER.warn(COOKIE_NOT_FOUND_EXCEPTION + ex.getMessage());
        }

        if (user != null && user.getRole().toString().equalsIgnoreCase(Role.ADMIN.toString())) {
            request.getRequestDispatcher(DISH_EDIT_PAGE).forward(request, response);
        } else {
            request.getRequestDispatcher(MENU_PAGE).forward(request, response);
        }
    }
}
