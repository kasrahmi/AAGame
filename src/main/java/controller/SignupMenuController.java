package controller;

import model.Database;
import model.User;
import view.enums.SignupMenuMessages;

public class SignupMenuController {

    public SignupMenuMessages signup(String username, String password, String email) {
        Database.loadUsers();

        if (username.equals("")) return SignupMenuMessages.EMPTY_USERNAME;
        else if (password.equals("")) return SignupMenuMessages.EMPTY_PASSWORD;
        else if (email.equals("")) return SignupMenuMessages.EMPTY_EMAIL;

        if (password.length() < 5) return SignupMenuMessages.SHORT_PASSWORD;
        else if (!password.matches(".*[a-z].*")) return SignupMenuMessages.DOES_NOT_CONTAIN_LOWERCASE_LETTER;
        else if (!password.matches(".*[A-Z].*")) return SignupMenuMessages.DOES_NOT_CONTAIN_UPPERCASE_LETTER;
        else if (!password.matches(".*[0-9].*")) return SignupMenuMessages.PASSWORD_DOES_NOT_CONTAIN_NUMBER;

        if (!email.matches(".+@.+\\..+")) return SignupMenuMessages.INVAILD_EMAIL;

        if (Database.getUserByUserName(username) != null) return SignupMenuMessages.USERNAME_EXISTS;

        User user = new User(username, password, email);
        Database.getUsers().add(user);
        Database.saveUsers();

        return SignupMenuMessages.SUCCESS;
    }
}
