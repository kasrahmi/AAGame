package controller;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Ball;
import model.CurrentGame;
import view.animations.ShootingBall;
import view.gameMenu.GameMenu;

import java.awt.*;
import java.util.ArrayList;

public class GameMenuController {
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Timeline> timelines = new ArrayList<>();
    public static ArrayList<Timeline> reverseTimelines = new ArrayList<>();
    public int numberOfBalls;
    public void freeze() {

    }

    public void shotBall() {
        Text text = new Text(String.valueOf(numberOfBalls));
        text.setX(300); text.setY(250);
//        text.setTranslateX(300); text.setTranslateY(250);
        text.setFont(Font.font(10));
        text.setFill(Color.WHITE);
        Ball newBall = new Ball();
        GameMenu.borderPane.getChildren().addAll(newBall, text);
        ShootingBall shootingBall = new ShootingBall(newBall, text);
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
