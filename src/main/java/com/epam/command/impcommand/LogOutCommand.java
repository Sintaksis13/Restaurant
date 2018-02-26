package com.epam.command.impcommand;

import com.epam.command.ActionCommand;
import com.epam.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.NameConstants.USER;
import static com.epam.constants.PageConstants.INDEX_PAGE;

public class LogOutCommand implements ActionCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = ActionCommand.fetchUser(request.getCookies());

        if (user != null) {
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals(USER)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        response.sendRedirect(INDEX_PAGE);
    }
}
