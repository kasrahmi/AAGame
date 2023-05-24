package model;

import javafx.scene.input.KeyCode;

public class CurrentGame {
    private static User loggedInUser;
    private static boolean guestMode;
    private static Difficulty difficulty = Difficulty.MEDIUM;
    private static int numberOfBalls = 20;
    private static boolean darkMode = false;
    private static boolean muteSong = false;
    private static int phase = 1;
    private static int numberOfDefaultBalls = 5;
    private static int songNumber = 1;
    private static KeyCode shoot = KeyCode.SPACE;
    private static KeyCode freeze = KeyCode.TAB;
    private static KeyCode pause = KeyCode.CONTROL;

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

    public static void setNumberOfDefaultBalls(int numberOfDefaultBalls) {
        CurrentGame.numberOfDefaultBalls = numberOfDefaultBalls;
    }

    public static int getNumberOfDefaultBalls() {
        return CurrentGame.numberOfDefaultBalls;
    }

    public static int getSongNumber() {
        return songNumber;
    }

    public static void setSongNumber(int songNumber) {
        CurrentGame.songNumber = songNumber;
    }

    public static KeyCode getShoot() {
        return shoot;
    }

    public static void setShoot(KeyCode shoot) {
        CurrentGame.shoot = shoot;
    }

    public static KeyCode getFreeze() {
        return freeze;
    }

    public static void setFreeze(KeyCode freeze) {
        CurrentGame.freeze = freeze;
    }

    public static KeyCode getPause() {
        return pause;
    }

    public static void setPause(KeyCode pause) {
        CurrentGame.pause = pause;
    }

    public static String getKeyBinds() {
        return "Shooting : " + getShoot().getName() +
                "\nFreeze : " + getFreeze().getName() +
                "\nPause : " + getPause().getName();
    }
}
