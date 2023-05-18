package controller;

import controller.utils.checkValidations;
import controller.utils.updateDatabase;
import model.CurrentGame;
import model.Database;
import model.User;
import view.enums.SignupMenuMessages;

public class ProfileMenuController {
    public String changeUsername(String text) {
        Database.loadUsers();
        if (Database.getUserByUserName(text) != null) return "username exist";
        User user = Database.getUserByUserName(CurrentGame.getLoggedInUser().getUsername());
        user.setUsername(text);
        Database.saveUsers();
        return "successfully changed";
    }

    public void removeAccount() {
        User user = Database.getUserByUserName(CurrentGame.getLoggedInUser().getUsername());
        Database.getUsers().remove(user);
        Database.saveUsers();
        updateDatabase.logout();
    }

    public String changePassword(String newPassword) {
        Database.loadUsers();
        String output;
        User currentUser = Database.getUserByUserName(CurrentGame.getLoggedInUser().getUsername());
        if (currentUser.getPassword().equals(newPassword)) return "Same password as before";

        if (!(output = checkValidations.checkPassword(newPassword)).equals("Successfully set"))
            return output;

        currentUser.setPassword(newPassword);
        Database.saveUsers();
        return "Password changed successfully";

    }
}
