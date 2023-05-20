package view.animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;
import model.Database;
import view.gameMenu.GameMenu;

public class ShootingBall extends Transition {
    Pane pane;
    Ball ball;
    public ShootingBall(Ball ball) {
        pane = GameMenu.borderPane;
        this.ball = ball;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        double y = ball.getCenterY() - 10;
        double x = ball.getCenterX() - 10;

        if (y <= 10) pane.getChildren().remove(ball);
        if (x <= 10 || x >= 590) pane.getChildren().remove(ball);
        if (getDistanceFromCenterOfTheCircle(x, y) <= 200) {
            rotationBalls();
            this.stop();
        }

        ball.setCenterY(y);
    }

    private double getDistanceFromCenterOfTheCircle(double x, double y) {
        return Math.sqrt(Math.pow(x - 300, 2) + Math.pow(y - 250, 2));
    }

    public void rotationBalls() {
        Rotate rotate = new Rotate(0, 300, 250);
        ball.getTransforms().add(rotate);
        addLine(rotate);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1500 * CurrentGame.getLevel()), new KeyValue(rotate.angleProperty(), 360)));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void addLine(Rotate rotate) {
        Line line = new Line(ball.getCenterX(), ball.getCenterY() - 15, 300, 355);
        line.setStrokeWidth(10);
        line.getTransforms().add(rotate);
        pane.getChildren().add(line);
    }
}
