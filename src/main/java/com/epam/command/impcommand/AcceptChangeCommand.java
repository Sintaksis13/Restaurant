package com.epam.command.impcommand;

import com.epam.action.UserAction;
import com.epam.command.ActionCommand;
import com.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

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

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ActionCommand.fetchUser(request.getCookies());

        if (user != null) {
            user = fillUpUser(request, response, user);

            if (new UserAction().changeUserInfo(user) != UNCHANGED_ROWS) {
                request.setAttribute(USER, user);
                request.setAttribute(SUCCESS, SUCCESS_MESSAGE);

                request.getRequestDispatcher(ACCOUNT_PAGE).forward(request, response);
            } else {
                User failedUser = ActionCommand.fetchUser(request.getCookies());

                request.setAttribute(FAIL, EMAIL_FAIL_MESSAGE);
                request.setAttribute(USER, failedUser);

                request.getRequestDispatcher(CHANGE_PAGE).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }
    }

    private User fillUpUser(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        if (request.getParameter(OLD_PASSWORD).isEmpty() && request.getParameter(NEW_PASSWORD).isEmpty()) {
            user.setPassword(user.getPassword());
        } else if (!new UserAction().authenticateUser(user.getLogin(), request.getParameter(OLD_PASSWORD))) {
            request.setAttribute(FAIL, PASSWORD_FAIL_MESSAGE);

            User failedUser = ActionCommand.fetchUser(request.getCookies());

            request.setAttribute(USER, failedUser);

            request.getRequestDispatcher(CHANGE_PAGE).forward(request, response);
        } else {
            user.setPassword(request.getParameter(NEW_PASSWORD));
        }
        user.setEmail(request.getParameter(EMAIL));
        user.setPhoneNumber(request.getParameter(PHONE_NUMBER));

        return user;
    }
}
