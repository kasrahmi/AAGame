package controller.utils;

import model.CurrentGame;

public class updateDatabase {
    public static void logout() {
        CurrentGame.setGuestMode(false);
        CurrentGame.setLoggedInUser(null);
    }

}
