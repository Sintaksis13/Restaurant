package com.epam.command.impcommand;

import com.epam.command.ActionCommand;
import com.epam.entity.User;
import com.epam.exception.CookieNotFoundException;
import com.epam.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.ExceptionConstants.COOKIE_NOT_FOUND_EXCEPTION;
import static com.epam.constants.NameConstants.USER;
import static com.epam.constants.PageConstants.INDEX_PAGE;

public class LogOutCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AcceptChangeCommand.class);


    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = null;

        try {
            user = new Validator().checkCookie(request.getCookies());
        } catch (CookieNotFoundException ex) {
            LOGGER.warn(COOKIE_NOT_FOUND_EXCEPTION + ex.getMessage());
        }

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
