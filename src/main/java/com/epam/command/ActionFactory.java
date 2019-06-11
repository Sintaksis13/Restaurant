package com.epam.command;

import com.epam.command.impcommand.*;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {

    private static final Map<String, ActionCommand> actionMap = new HashMap<>();

    private ActionFactory() {
        super();
    }

    static {
        actionMap.put("registerPage", new RegisterCommand());
        actionMap.put("mainPage", new MainCommand());
        actionMap.put("authorizationPage", new AuthenticationCommand());
        actionMap.put("roomPage", new RoomCommand());
        actionMap.put("logoutPage", new LogOutCommand());
        actionMap.put("changeAccount", new ChangeCommand());
        actionMap.put("acceptChanges", new AcceptChangeCommand());
        actionMap.put("viewUsers", new ViewUsersCommand());
    }

    public static ActionCommand getCommand(String name) {
        return actionMap.get(name);
    }

}
