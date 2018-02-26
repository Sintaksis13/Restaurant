package com.epam.command.impcommand;

import com.epam.command.ActionCommand;
import com.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.NameConstants.USER;
import static com.epam.constants.PageConstants.CHANGE_PAGE;
import static com.epam.constants.PageConstants.INDEX_PAGE;

public class ChangeCommand implements ActionCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ActionCommand.fetchUser(request.getCookies());

        if (user != null) {
            request.setAttribute(USER, user);
            request.getRequestDispatcher(CHANGE_PAGE).forward(request, response);
        } else {
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }
    }
}
