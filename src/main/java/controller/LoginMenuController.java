package controller;

import controller.utils.checkValidations;
import model.CurrentGame;
import model.Database;
import model.User;
import view.enums.LoginMenuMessages;

public class LoginMenuController {

    public LoginMenuMessages login(String name, String password) {
        Database.loadUsers();

        User user = Database.getUserByUserName(name);

        if (checkValidations.emptyField(name)) return LoginMenuMessages.EMPTY_USERNAME;

        if (checkValidations.emptyField(password)) return LoginMenuMessages.EMPTY_PASSWORD;

        if (user == null) return LoginMenuMessages.USER_DOES_NOT_EXIST;

        if (!user.getPassword().equals(password)) return LoginMenuMessages.WRONG_PASSWORD;

        CurrentGame.setLoggedInUser(user);
        CurrentGame.setGuestMode(false);

        return LoginMenuMessages.SUCCESS;
    }

    public void loginAsGuest() {
        CurrentGame.setLoggedInUser(null);
        CurrentGame.setGuestMode(true);
        Database.loadUsers();
    }
}
