package controller;

import controller.utils.updateDatabase;
import javafx.scene.image.ImageView;
import model.AvatarOrganizer;
import model.CurrentGame;
import model.Database;

public class MainMenuController {

    public String getUsername() {
        if (CurrentGame.isGuestMode()) return "You have entered as guest";
        return "You have entered as " + CurrentGame.getLoggedInUser().getUsername() +
                "\nLevel : " + Database.getUserByUserName(CurrentGame.getLoggedInUser().getUsername()).getLevel();
    }
    public void logout() {
        updateDatabase.logout();
    }

    public boolean isGuestMode() {
        return CurrentGame.isGuestMode();
    }

    public ImageView getAvatar() {
        if (CurrentGame.isGuestMode()) return null;
        return AvatarOrganizer.getAvatar(CurrentGame.getLoggedInUser());
    }
}
