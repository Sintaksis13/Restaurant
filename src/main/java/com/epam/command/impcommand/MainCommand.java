package com.epam.command.impcommand;

import com.epam.action.UserAction;
import com.epam.command.ActionCommand;
import com.epam.entity.User;
import com.epam.exception.CookieNotFoundException;
import com.epam.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.ExceptionConstants.COOKIE_NOT_FOUND_EXCEPTION;
import static com.epam.constants.NameConstants.*;
import static com.epam.constants.PageConstants.*;

public class MainCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AcceptChangeCommand.class);


    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;

        try {
            user = new Validator().checkCookie(request.getCookies());
        } catch (CookieNotFoundException ex) {
            LOGGER.warn(COOKIE_NOT_FOUND_EXCEPTION + ex.getMessage());
        }

        if (user != null) {
            request.setAttribute(USER, user);

            if (new UserAction().checkRole(user.getLogin()).equalsIgnoreCase(USER)) {
                request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
            } else if (new UserAction().checkRole(user.getLogin()).equalsIgnoreCase(ADMIN)) {
                request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }
    }
}
