package com.epam.controller;

import com.epam.command.ActionCommand;
import com.epam.command.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constants.ExceptionConstants.IO_EXCEPTION;
import static com.epam.constants.ExceptionConstants.SERVLET_EXCEPTION;

@WebServlet("/MainServlet")
public final class MainServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(MainServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand actionCommand = ActionFactory.getCommand(request.getParameter("actionCommand"));
        LOG.debug("Processing command: {}", actionCommand);
        try {
            actionCommand.execute(request, response);
        } catch (ServletException | IOException ex) {
            if (ex.getClass().equals(ServletException.class)) {
                LOG.warn(SERVLET_EXCEPTION + ex.getMessage());
            } else {
                LOG.warn(IO_EXCEPTION + ex.getMessage());
            }
        }
    }

}
