package com.epam.command.impcommand;

import com.epam.action.UserAction;
import com.epam.command.ActionCommand;
import com.epam.entity.type.Role;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static com.epam.constants.NameConstants.*;
import static com.epam.constants.PageConstants.*;

public class AuthenticationCommand implements ActionCommand {
    private static final  String FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                             .getString("authentication_fail_message");

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        UserAction userAction = new UserAction();

        if (userAction.authenticateUser(login, password)) {
            Cookie loginCookie = new Cookie(USER, login);

            loginCookie.setMaxAge(60*60);

            response.addCookie(loginCookie);

            if (userAction.checkRole(loginCookie.getValue()).equalsIgnoreCase(Role.USER.toString())) {
                request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
            } else {
               request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(FAIL, FAIL_MESSAGE);
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }
    }

}
