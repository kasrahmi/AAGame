package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
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

        if (y <= 10) pane.getChildren().remove(ball);

        ball.setCenterY(y);
    }
}
