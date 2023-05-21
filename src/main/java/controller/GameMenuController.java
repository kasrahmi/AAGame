package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Ball;
import model.CurrentGame;
import view.animations.ShootingBall;
import view.gameMenu.GameMenu;

import java.util.ArrayList;

public class GameMenuController {
    public static ArrayList<Ball> balls = new ArrayList<>();
    public int numberOfBalls;
    public void freeze() {

    }

    public void shotBall() {
        Ball newBall = new Ball();
        GameMenu.borderPane.getChildren().add(newBall);
        ShootingBall shootingBall = new ShootingBall(newBall);
        shootingBall.play();
        numberOfBalls--;
    }

    public void getNumberOfBallsEachPhase() {
        this.numberOfBalls = CurrentGame.getNumberOfBalls();
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }
}
