package com.epam.command.impcommand;

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
import static com.epam.constants.PageConstants.INDEX_PAGE;
import static com.epam.constants.PageConstants.RESERVE_PAGE;

public class ReserveCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AcceptChangeCommand.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);

        if (user != null) {
            request.getRequestDispatcher(RESERVE_PAGE).forward(request, response);
        } else {
            response.sendRedirect(INDEX_PAGE);
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
}
