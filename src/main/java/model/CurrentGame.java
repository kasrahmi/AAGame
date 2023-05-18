package model;

public class CurrentGame {
    private static User loggedInUser;
    private static boolean guestMode;

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
}
