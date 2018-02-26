package com.epam.validator;

import com.epam.action.UserAction;
import com.epam.entity.User;
import com.epam.exception.CookieNotFoundException;

import javax.servlet.http.Cookie;

public final class Validator {
    public User checkCookie(Cookie[] cookies) throws CookieNotFoundException{
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("user")) {
                return new UserAction().getAccountAccess(cookie.getValue());
            }
        }

        throw new CookieNotFoundException("Cookie was not found!");
    }
}
