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
        actionMap.put("menuPage", new MenuCommand());
        actionMap.put("roomPage", new RoomCommand());
        actionMap.put("logoutPage", new LogOutCommand());
        actionMap.put("changeAccount", new ChangeCommand());
        actionMap.put("acceptChanges", new AcceptChangeCommand());
        actionMap.put("reservePage", new ReserveCommand());
        actionMap.put("acceptReserve", new AcceptReserveCommand());
        actionMap.put("viewUsers", new ViewUsersCommand());
        actionMap.put("addDish", new AddDishCommand());
        actionMap.put("deleteDish", new DeleteDishCommand());
        actionMap.put("updateDish", new UpdateDishCommand());
        actionMap.put("acceptDishUpdate", new AcceptDishUpdateCommand());
    }

    public static ActionCommand getCommand(String name) {
        return actionMap.get(name);
    }
}
