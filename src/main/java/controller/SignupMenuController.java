package controller;

import controller.utils.checkValidations;
import model.Database;
import model.User;
import view.enums.SignupMenuMessages;

public class SignupMenuController {

    public String signup(String username, String password, String email) {
        Database.loadUsers();
        String output;

        if (checkValidations.emptyField(username) || checkValidations.emptyField(password)
        || checkValidations.emptyField(email)) return "Empty Field!";

        if (!(output = checkValidations.checkPassword(password)).equals("Successfully set"))
            return output;

        if (!email.matches(".+@.+\\..+")) return "Invalid email type";

        if (Database.getUserByUserName(username) != null) return "Username already exist";

        User user = new User(username, password, email);
        Database.getUsers().add(user);
        Database.saveUsers();

        return "User successfully created";
    }
}
