package controller;

import controller.utils.checkBallsCrashed;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
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

import java.util.ArrayList;

public class GameMenuController {
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Timeline> timelines = new ArrayList<>();
    public static ArrayList<Timeline> reverseTimelines = new ArrayList<>();
    public static ArrayList<Group> groups = new ArrayList<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static int numberOfBalls;
    public static RotateAnimation rotateAnimation;
    public static ShootingBall shootingBall;

    public static void rotationBalls(Ball ball, Text text) {
        Rotate rotate = new Rotate((-1)*rotateAnimation.getRotate().getAngle(), 300, 250);
        ball.getTransforms().addAll(rotateAnimation.getRotate(), rotate);
        text.getTransforms().addAll(rotateAnimation.getRotate(), rotate);

        Line line = new Line(ball.getCenterX(), ball.getCenterY() - 15, 300, 250);
        line.setStrokeWidth(2);
        line.getTransforms().addAll(rotate, rotateAnimation.getRotate());
        GameMenu.borderPane.getChildren().add(0, line);
        lines.add(line);

        makeGroups(ball, line, text);

        for (Ball ball1 : GameMenuController.balls) {
            if (checkBallsCrashed.twoBallsCrashed(ball, ball1)) {
                GameMenu.winOrLoosGame(false);
                return;
            }
        }
        if (GameMenuController.numberOfBalls == 0) {
            GameMenu.borderPane.getChildren().remove(GameMenu.ball);
            GameMenu.borderPane.getChildren().remove(GameMenu.numberOfBall);
            try {
                GameMenu.moveToNextPhase();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        balls.add(ball);
    }

    private static void makeGroups(Ball ball, Line line, Text text) {
        Group group = new Group(ball, line, text);
        groups.add(group);
        GameMenu.borderPane.getChildren().add(0, group);
    }

    public static void rotationBalls(Ball ball, Rotate rotation) {
        Rotate rotate = new Rotate((-1)*rotateAnimation.getRotate().getAngle(), 300, 250);
        ball.getTransforms().addAll(rotateAnimation.getRotate(), rotate);

        Line line = new Line(ball.getCenterX(), ball.getCenterY(), 300, 250);
        line.setStrokeWidth(2);
        line.getTransforms().addAll(rotate, rotateAnimation.getRotate(), rotation);
        GameMenu.borderPane.getChildren().add(0, line);
        lines.add(line);

        makeGroups(ball, line, new Text(""));
        balls.add(ball);
    }

    public void freeze() {
        rotateAnimation.setAngeleRotate(CurrentGame.getDifficulty().getRotateAngle() * 0.4);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(CurrentGame.getDifficulty().getFreezeTime()),
                actionEvent -> {
            rotateAnimation.setAngeleRotate(CurrentGame.getDifficulty().getRotateAngle());
            GameMenu.borderPane.setStyle("-fx-background-color: #6495ED;");
                }));
        timeline.setCycleCount(0);
        timeline.play();
    }

    public void shotBall() {
        Text text = new Text(String.valueOf(numberOfBalls));
        text.setX(300); text.setY(250);
//        text.setTranslateX(300); text.setTranslateY(250);
        text.setFont(Font.font(10));
        text.setFill(Color.WHITE);
        Ball newBall = new Ball(GameMenu.ball.getCenterX(), GameMenu.ball.getCenterY());
//        Line line = new Line(newBall.getCenterX(), newBall.getCenterY() - 15, 300, 250);
//        line.setStrokeWidth(10);
        GameMenu.borderPane.getChildren().addAll(newBall, text);
        numberOfBalls--;
        ShootingBall shootingBall = new ShootingBall(newBall, text);
        GameMenuController.shootingBall = shootingBall;
        shootingBall.play();
    }

    public void getNumberOfBallsEachPhase() {
        numberOfBalls = CurrentGame.getNumberOfBalls();
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public void moveBallLeft() {
        double x = GameMenu.ball.getCenterX() - 10;
        if (x > 10)
            GameMenu.ball.setCenterX(x);
    }

    public void moveBallRight() {
        double x = GameMenu.ball.getCenterX() + 10;
        if (x < 590)
            GameMenu.ball.setCenterX(x);
    }
}
