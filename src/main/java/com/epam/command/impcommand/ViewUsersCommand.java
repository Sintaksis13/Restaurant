package com.epam.command.impcommand;

import com.epam.action.UserAction;
import com.epam.command.ActionCommand;
import com.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.constants.PageConstants.VIEW_USERS_PAGE;

public class ViewUsersCommand implements ActionCommand {
    private static final String USERS = "users";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ActionCommand.fetchUser(request.getCookies());

        if (user != null) {
            List<User> userList = new UserAction().getUserList();

            request.setAttribute(USERS, userList);

            request.getRequestDispatcher(VIEW_USERS_PAGE).forward(request, response);
        }
    }
}
