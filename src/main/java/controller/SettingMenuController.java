package controller;

import model.CurrentGame;

public class SettingMenuController {
    public int getLevel() {
        return CurrentGame.getLevel();
    }

    public int getNumberOfBalls() {
        return CurrentGame.getNumberOfBalls();
    }
    public void setLevel(int i) {
        CurrentGame.setLevel(i);
    }

    public void setNumberOfBalls(int numberOfBalls) {
        CurrentGame.setNumberOfBalls(numberOfBalls);
    }
}
