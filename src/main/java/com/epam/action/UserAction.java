package com.epam.action;

import com.epam.entity.User;
import com.epam.service.implementation.UserService;

import java.util.List;

public class UserAction {
    private UserService userService = new UserService();

    public int registerUser(User user) {
        return userService.create(user);
    }

    public boolean authenticateUser(String login, String password) {
        return userService.checkForLogIn(login, password);
    }

    public boolean authorizeUser(String login, String email) {
        return userService.checkForRegistration(login, email);
    }

    public String checkRole(String login) {
        return userService.checkRoleByLogin(login);
    }

    public User getAccountAccess(String login) {
        return userService.getAccount(login);
    }

    public int changeUserInfo(User user) {
        return userService.updateUserInfo(user);
    }

    public void changeUserTable(User user) {
        userService.changeUserTable(user);
    }

    public List<User> getUserList() {
        return userService.getAll();
    }
}
