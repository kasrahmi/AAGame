package model;

public class CurrentGame {
    private static User loggedInUser;
    private static boolean guestMode;
    private static Difficulty difficulty = Difficulty.MEDIUM;
    private static int numberOfBalls = 20;
    private static boolean darkMode = false;
    private static boolean muteSong = false;
    private static int phase = 1;

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

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty difficulty) {
        CurrentGame.difficulty = difficulty;
    }

    public static int getNumberOfBalls() {
        return numberOfBalls;
    }

    public static void setNumberOfBalls(int numberOfBalls) {
        CurrentGame.numberOfBalls = numberOfBalls;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setDarkMode(boolean darkMode) {
        CurrentGame.darkMode = darkMode;
    }

    public static boolean isMuteSong() {
        return muteSong;
    }

    public static void setMuteSong(boolean muteSong) {
        CurrentGame.muteSong = muteSong;
    }

    public static int getPhase() {
        return phase;
    }

    public static void setPhase(int phase) {
        CurrentGame.phase = phase;
    }
}
