package com.epam.controller;

import com.epam.command.ActionCommand;
import com.epam.command.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public final class MainServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(MainServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand actionCommand = ActionFactory.getCommand(request.getParameter("actionCommand"));
        LOG.debug("Processing command: {}", actionCommand);
        try {
            actionCommand.execute(request, response);
        } catch (Exception ex) {
            LOG.error("An error occurred during processing command: {} , error: {}", actionCommand, ex.getMessage());
        }
    }

}
