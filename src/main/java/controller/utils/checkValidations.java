package controller.utils;

public class checkValidations {
    public static String checkPassword(String password) {
        if (password.length() < 5) return "Short password";
        else if (!password.matches(".*[a-z].*")) return "Don't have lower case";
        else if (!password.matches(".*[A-Z].*")) return "Don't have upper case";
        else if (!password.matches(".*[0-9].*")) return "Don't have number";
        return "Successfully set";
    }
}
