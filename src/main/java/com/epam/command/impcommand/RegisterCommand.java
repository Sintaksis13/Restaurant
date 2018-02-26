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
import static com.epam.constants.NumericConstants.START_VALUE;
import static com.epam.constants.PageConstants.INDEX_PAGE;
import static com.epam.constants.PageConstants.REGISTER_PAGE;

public class RegisterCommand implements ActionCommand {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
                                                                .getString("register_success_message");
    private static final String FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                             .getString("register_fail_message");

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String phoneNumber = request.getParameter(PHONE_NUMBER);

        UserAction userAction = new UserAction();
        if (userAction.authorizeUser(login, email) && login != null && password != null && email != null) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);

            int result = userAction.registerUser(user);

            if (result != START_VALUE) {
                request.setAttribute(SUCCESS, SUCCESS_MESSAGE);

                request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
            } else {
                request.setAttribute(FAIL, FAIL_MESSAGE);

                request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(FAIL, FAIL_MESSAGE);

            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        }
    }
}
