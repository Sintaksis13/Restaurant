package com.epam.command.impcommand;

import com.epam.action.TableAction;
import com.epam.action.UserAction;
import com.epam.command.ActionCommand;
import com.epam.entity.Table;
import com.epam.entity.User;
import com.epam.entity.type.TableStatus;
import com.epam.entity.type.TableType;
import com.epam.exception.CookieNotFoundException;
import com.epam.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static com.epam.constants.ExceptionConstants.COOKIE_NOT_FOUND_EXCEPTION;
import static com.epam.constants.NameConstants.FAIL;
import static com.epam.constants.NameConstants.SUCCESS;
import static com.epam.constants.PageConstants.INDEX_PAGE;
import static com.epam.constants.PageConstants.MAIN_PAGE;

public class AcceptReserveCommand implements ActionCommand {
    private static final String SUCCESS_MESSAGE = ResourceBundle.getBundle("language")
                                                                .getString("reserve_success_message");
    private static final String FAIL_MESSAGE = ResourceBundle.getBundle("language")
                                                             .getString("reserve_fail_message");
    private static final String SEATS_NUMBER = "seatsNumber";
    private static final String RESERVE_TIME = "time";
    private static final String TABLE_VALUE = "tableValue";

    private static final Logger LOGGER = Logger.getLogger(AcceptChangeCommand.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;

        try {
            user = new Validator().checkCookie(request.getCookies());
        } catch (CookieNotFoundException ex) {
            LOGGER.warn(COOKIE_NOT_FOUND_EXCEPTION + ex.getMessage());
        }

        if (user != null) {
            Table wantedTable = getTable(request);

            int reserveResult = new TableAction().reserveTable(wantedTable);

            if (reserveResult != 0) {
                wantedTable.setId(reserveResult);
                user.setTable(wantedTable);
                new UserAction().changeUserTable(user);
                request.setAttribute(SUCCESS, SUCCESS_MESSAGE);
            } else {
                request.setAttribute(FAIL, FAIL_MESSAGE);
            }

            request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
        } else {
            response.sendRedirect(INDEX_PAGE);
        }
    }

    private Table getTable(HttpServletRequest request) {
        Table wantedTable = new Table();

        wantedTable.setSeatsNumber(Integer.parseInt(request.getParameter(SEATS_NUMBER)));
        wantedTable.setReservationTime(Integer.parseInt(request.getParameter(RESERVE_TIME)));
        if (request.getParameter(TABLE_VALUE).equalsIgnoreCase(TableType.COMMON.toString())) {
            wantedTable.setValue(TableType.COMMON);
        } else {
            wantedTable.setValue(TableType.VIP);
        }
        wantedTable.setStatus(TableStatus.BOOKED);
        return wantedTable;
    }
}
