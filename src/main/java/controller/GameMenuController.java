package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;
import view.animations.RotateAnimation;
import view.animations.ShootingBall;
import view.gameMenu.GameMenu;

import java.awt.*;
import java.util.ArrayList;

public class GameMenuController {
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Timeline> timelines = new ArrayList<>();
    public static ArrayList<Timeline> reverseTimelines = new ArrayList<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static int numberOfBalls;
    public static RotateAnimation rotateAnimation = new RotateAnimation();

    public static void rotationBalls(Ball ball, Text text) {
        Rotate rotate = new Rotate((-1)*rotateAnimation.getRotate().getAngle(), 300, 250);
        ball.getTransforms().addAll(rotateAnimation.getRotate(), rotate);
        text.getTransforms().addAll(rotateAnimation.getRotate(), rotate);

        Line line = new Line(ball.getCenterX(), ball.getCenterY() - 15, 300, 250);
        line.setStrokeWidth(10);
        line.getTransforms().addAll(rotate, rotateAnimation.getRotate());
        GameMenu.borderPane.getChildren().add(0, line);
        lines.add(line);

        rotateAnimation.play();
        for (Ball ball1 : GameMenuController.balls) {
            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent()) && !ball1.equals(ball)) {
                GameMenu.loseTheGame();
                return;
            }
        }
        balls.add(ball);
    }

    public void freeze() {
        rotateAnimation.setAngeleRotate(0.5);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(CurrentGame.getDifficulty().getFreezeTime()),
                actionEvent -> rotateAnimation.setAngeleRotate(CurrentGame.getDifficulty().getRotateAngle())));
        timeline.setCycleCount(0);
        timeline.play();
    }

    public void shotBall() {
        Text text = new Text(String.valueOf(numberOfBalls));
        text.setX(300); text.setY(250);
//        text.setTranslateX(300); text.setTranslateY(250);
        text.setFont(Font.font(10));
        text.setFill(Color.WHITE);
        Ball newBall = new Ball();
//        Line line = new Line(newBall.getCenterX(), newBall.getCenterY() - 15, 300, 250);
//        line.setStrokeWidth(10);
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
