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
import java.util.ResourceBundle;

import static com.epam.constants.ExceptionConstants.COOKIE_NOT_FOUND_EXCEPTION;
import static com.epam.constants.NameConstants.*;
import static com.epam.constants.NumericConstants.UNCHANGED_ROWS;
import static com.epam.constants.PageConstants.*;

public class AcceptChangeCommand implements ActionCommand {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
                                                                .getString("change_success_message");
    private static final String PASSWORD_FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                                      .getString("change_password_fail_message");
    private static final String EMAIL_FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                                   .getString("change_email_fail_message");
    private static final String OLD_PASSWORD = "oldpassword";
    private static final String NEW_PASSWORD = "newpassword";

    private static final Logger LOGGER = Logger.getLogger(AcceptChangeCommand.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);

        if (user != null) {
            user = setUser(request, response, user, EMAIL, PHONE_NUMBER);

            if (new UserAction().changeUserInfo(user) != UNCHANGED_ROWS) {
                request.setAttribute(USER, user);
                request.setAttribute(SUCCESS, SUCCESS_MESSAGE);

                request.getRequestDispatcher(ACCOUNT_PAGE).forward(request, response);
            } else {
                User failedUser = getUser(request);

                request.setAttribute(FAIL, EMAIL_FAIL_MESSAGE);
                request.setAttribute(USER, failedUser);

                request.getRequestDispatcher(CHANGE_PAGE).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }
    }

    private User getUser(HttpServletRequest request) {
        User user = null;

        try {
            user = new Validator().checkCookie(request.getCookies());
        } catch (CookieNotFoundException ex) {
            LOGGER.warn(COOKIE_NOT_FOUND_EXCEPTION + ex.getMessage());
        }

        return user;
    }

    private User setUser(HttpServletRequest request, HttpServletResponse response,
                         User user, String email, String phoneNumber) throws ServletException, IOException {
        if (request.getParameter(OLD_PASSWORD).isEmpty() && request.getParameter(NEW_PASSWORD).isEmpty()) {
            user.setPassword(user.getPassword());
        } else if (!new UserAction().authenticateUser(user.getLogin(), request.getParameter(OLD_PASSWORD))) {
            request.setAttribute(FAIL, PASSWORD_FAIL_MESSAGE);

            User failedUser = getUser(request);

            request.setAttribute(USER, failedUser);

            request.getRequestDispatcher(CHANGE_PAGE).forward(request, response);
        } else {
            user.setPassword(request.getParameter(NEW_PASSWORD));
        }
        user.setEmail(request.getParameter(email));
        user.setPhoneNumber(request.getParameter(phoneNumber));

        return user;
    }
}
