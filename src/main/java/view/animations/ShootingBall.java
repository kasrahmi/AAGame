package view.animations;

import controller.GameMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
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
import java.util.List;

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
        double x = ball.getCenterX() - CurrentGame.getDifficulty().getWindSpeed() * 4;

        if (y <= 10) pane.getChildren().remove(ball);
        if (x <= 10 || x >= 590) pane.getChildren().remove(ball);
        if (getDistanceFromCenterOfTheCircle(x, y) <= 200 && (!text.getText().equals(""))) {
            GameMenuController.rotationBalls(ball, text);
            GameMenuController.shootingBall = null;
            this.stop();
        } else if (getDistanceFromCenterOfTheCircle(x, y) <= 200 && (text.getText().equals(""))) {
            GameMenuController.rotationBalls(ball, text);
            this.stop();
        }

        if (CurrentGame.getPhase() == 4) {
            ball.setCenterX(x);
            text.setX(x - 2);
        }
        ball.setCenterY(y);
        text.setY(y + 3);
    }

    private double getDistanceFromCenterOfTheCircle(double x, double y) {
        return Math.sqrt(Math.pow(x - 300, 2) + Math.pow(y - 250, 2));
    }

//    private boolean twoBallsCrashed(Ball ball1, Ball ball2) {
//        Point2D point2DFirstBall = ball1.localToScene(ball1.getCenterX(), ball1.getCenterY());
//        Point2D point2DSecondBall = ball2.localToScene(ball2.getCenterX(), ball2.getCenterY());
//        return Math.sqrt(Math.pow(point2DFirstBall.getX() - point2DSecondBall.getX(), 2) +
//                Math.pow(point2DFirstBall.getY() - point2DSecondBall.getY(), 2)) < ball1.getRadius() + ball2.getRadius();
//    }

//    public void rotationBalls() {
//        Rotate rotate = new Rotate(0, 300, 250);
//        ball.getTransforms().add(rotate);
////        System.out.println(text.getBoundsInLocal().getWidth());
////        Bounds textBounds = text.getBoundsInParent();
////        Rotate rotateText = new Rotate(0, GameMenu.mainCircle.getCenterX() - 300, GameMenu.mainCircle.getCenterY() - 250);
//        text.getTransforms().add(rotate);
////        addLine(rotate);
//        Timeline timelineNormal = new Timeline(
//                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), 360)));
//                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), rotate.getAngle() - 360));
//        Timeline textTimeline = new Timeline(
//                new KeyFrame(Duration.millis(CurrentGame.getDifficulty().getRotationTime()), new KeyValue(rotate.angleProperty(), 360)));
//        timelineNormal.setAutoReverse(true);
//        textTimeline.setCycleCount(-1);
//        textTimeline.play();
//        GameMenuController.timelines.add(textTimeline);
//        timelineNormal.setCycleCount(-1);
//        timelineNormal.play();
//        GameMenuController.timelines.add(timelineNormal);
////        GameMenuController.reverseTimelines.add(reverseTimeLine);
//        GameMenuController.balls.add(ball);
//        for (Ball ball1 : GameMenuController.balls) {
//            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent()) && !ball1.equals(ball)) {
//                GameMenu.loseTheGame();
//                return;
//            }
//        }
//    }
//
//    private void addLine(Rotate rotate) {
//        Line line = new Line(ball.getCenterX(), ball.getCenterY() - 15, 300, 250);
//        line.setStrokeWidth(10);
//        line.getTransforms().add(rotate);
//        pane.getChildren().add(0, line);
//    }

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
