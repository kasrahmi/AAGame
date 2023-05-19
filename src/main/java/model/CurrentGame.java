package model;

public class CurrentGame {
    private static User loggedInUser;
    private static boolean guestMode;
    private static int level = 2;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User currentUser) {
        CurrentGame.loggedInUser = currentUser;
    }

    public static boolean isGuestMode() {
        return guestMode;
    }

    public static void setGuestMode(boolean guestMode) {
        CurrentGame.guestMode = guestMode;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        CurrentGame.level = level;
    }
}
