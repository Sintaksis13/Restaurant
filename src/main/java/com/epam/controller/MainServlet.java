package com.epam.controller;

import com.epam.command.ActionCommand;
import com.epam.command.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.ExceptionConstants.IO_EXCEPTION;
import static com.epam.constants.ExceptionConstants.SERVLET_EXCEPTION;

public final class MainServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MainServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand actionCommand = ActionFactory.getCommand(request.getParameter("actionCommand"));
        try {
            actionCommand.execute(request, response);
        } catch (ServletException | IOException ex) {
            if (ex.getClass().equals(ServletException.class)) {
                LOGGER.warn(SERVLET_EXCEPTION + ex.getMessage());
            } else {
                LOGGER.warn(IO_EXCEPTION + ex.getMessage());
            }
        }
    }

}
