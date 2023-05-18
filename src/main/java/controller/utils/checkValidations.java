package controller.utils;

public class checkValidations {
    public static String checkPassword(String password) {
        if (password.length() < 5) return "Short password in password";
        else if (!password.matches(".*[a-z].*")) return "Don't have lower case in password";
        else if (!password.matches(".*[A-Z].*")) return "Don't have upper case in password";
        else if (!password.matches(".*[0-9].*")) return "Don't have number in password";
        return "Successfully set";
    }

    public static boolean emptyField(String text) {
        return text.equals("");
    }
}
