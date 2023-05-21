package controller;

import model.CurrentGame;
import model.Difficulty;

public class SettingMenuController {
    public String getLevel() {
        return CurrentGame.getDifficulty().getDifficulty();
    }

    public int getNumberOfBalls() {
        return CurrentGame.getNumberOfBalls();
    }
    public void setDifficulty(String difficulty) {
        CurrentGame.setDifficulty(Difficulty.getDifficulty(difficulty));
    }

    public void setNumberOfBalls(int numberOfBalls) {
        CurrentGame.setNumberOfBalls(numberOfBalls);
    }

    public static boolean isDarkMode() {
        return CurrentGame.isDarkMode();
    }

    public void setDarkMode(boolean darkMode) {
        CurrentGame.setDarkMode(darkMode);
    }

    public static boolean isSongMute() {
        return CurrentGame.isMuteSong();
    }

    public void setMuteAndUnMute(boolean muteAndUnMute) {
        CurrentGame.setMuteSong(muteAndUnMute);
    }
}
