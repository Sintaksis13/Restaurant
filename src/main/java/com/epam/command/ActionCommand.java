package com.epam.command;

import com.epam.entity.User;
import com.epam.exception.CookieNotFoundException;
import com.epam.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.ExceptionConstants.COOKIE_NOT_FOUND_EXCEPTION;

public interface ActionCommand {
    Logger LOGGER = Logger.getLogger(ActionCommand.class);

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    static User fetchUser(Cookie[] cookies) {
        User user = new User();

        try {
            user = new Validator().checkCookie(cookies);
        } catch (CookieNotFoundException ex) {
            LOGGER.warn(COOKIE_NOT_FOUND_EXCEPTION + ex.getMessage());
        }

        return user;
    }
}
