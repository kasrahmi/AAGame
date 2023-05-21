package view.animations;

import controller.GameMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;
import model.Database;
import view.gameMenu.GameMenu;

import java.util.ArrayList;

public class ShootingBall extends Transition {
    Pane pane;
    Ball ball;
    Text text;
    public ShootingBall(Ball ball, Text text) {
        pane = GameMenu.borderPane;
        this.ball = ball;
        this.text = text;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        double y = ball.getCenterY() - 10;
        double x = ball.getCenterX() - CurrentGame.getDifficulty().getWindSpeed();

        if (y <= 10) pane.getChildren().remove(ball);
        if (x <= 10 || x >= 590) pane.getChildren().remove(ball);
        if (getDistanceFromCenterOfTheCircle(x, y) <= 200) {
            rotationBalls();
            this.stop();
        }

        ball.setCenterX(x);
        ball.setCenterY(y);
        text.setX(x - 2);
        text.setY(y + 3);
    }

    private double getDistanceFromCenterOfTheCircle(double x, double y) {
        return Math.sqrt(Math.pow(x - 300, 2) + Math.pow(y - 250, 2));
    }

    private boolean twoBallsCrashed(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < 20;
    }

    public void rotationBalls() {
        Rotate rotate = new Rotate(0, 300, 250);
        ball.getTransforms().add(rotate);
//        System.out.println(text.getBoundsInLocal().getWidth());
//        Bounds textBounds = text.getBoundsInParent();
//        Rotate rotateText = new Rotate(0, GameMenu.mainCircle.getCenterX() - 300, GameMenu.mainCircle.getCenterY() - 250);
        text.getTransforms().add(rotate);
        addLine(rotate);
        Timeline timelineNormal = new Timeline(
                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), 360)));
//        Timeline reverseTimeLine = new Timeline(
//                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), rotate.getAngle() - 360)));
        Timeline textTimeline = new Timeline(
                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), 360)));
        textTimeline.setCycleCount(-1);
        textTimeline.play();
        GameMenuController.timelines.add(textTimeline);
        timelineNormal.setCycleCount(-1);
        timelineNormal.play();
        GameMenuController.timelines.add(timelineNormal);
//        GameMenuController.reverseTimelines.add(reverseTimeLine);
        GameMenuController.balls.add(ball);
        for (Ball ball1 : GameMenuController.balls) {
            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent()) && !ball1.equals(ball)) {
                GameMenu.loseTheGame();
                return;
            }
        }
    }

    private void addLine(Rotate rotate) {
        Line line = new Line(ball.getCenterX(), ball.getCenterY() - 15, 300, 250);
        line.setStrokeWidth(10);
        line.getTransforms().add(rotate);
        pane.getChildren().add(0, line);
    }

//    public void rotationBalls() {
//        Rotate rotate = new Rotate(0, 300, 250);
//        ball.getTransforms().add(rotate);
//        text.getTransforms().add(new Rotate(0, 300, 250)); // Create a new Rotate object for the text
//        Timeline timelineNormal = new Timeline(
//                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), 360)));
//        timelineNormal.setCycleCount(-1);
//        timelineNormal.play();
//        GameMenuController.timelines.add(timelineNormal);
//        GameMenuController.balls.add(ball);
//        for (Ball ball1 : GameMenuController.balls) {
//            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent()) && !ball1(ball)) {
//                GameMenu.loseTheGame();
//                return;
//            }
//        }
//    }
}
