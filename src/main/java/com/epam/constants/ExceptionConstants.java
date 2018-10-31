package com.epam.constants;

public class ExceptionConstants {
    public static final String SQL_EXCEPTION = "SQL exception was thrown with message: {}";
    public static final String SERVLET_EXCEPTION = "Servlet exception was thrown with message: ";
    public static final String IO_EXCEPTION = "In/Out exception was thrown with message: ";
    public static final String REFLECTIVE_OPERATION_EXCEPTION = "Reflective operation exception was thrown with " +
                                                                "message: {}";
    public static final String COOKIE_NOT_FOUND_EXCEPTION = "Cookie not found exception was thrown with message: ";

    private ExceptionConstants() {
        super();
    }
}
