package controller;

import controller.utils.updateDatabase;
import model.CurrentGame;

public class MainMenuController {

    public String getUsername() {
        if (CurrentGame.isGuestMode()) return "You have entered as guest";
        return "You have entered as " + CurrentGame.getLoggedInUser().getUsername() +
                "\nLevel : " + CurrentGame.getLoggedInUser().getLevel();
    }
    public void logout() {
        updateDatabase.logout();
    }

    public boolean isGuestMode() {
        return CurrentGame.isGuestMode();
    }
}
