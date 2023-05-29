package view.animations;

import controller.GameMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        soundEffect();
    }

    private void soundEffect() {
        if (!CurrentGame.isMuteSong()) {
            Media media = new Media(getClass().getResource("/sounds/shooting.wav").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }

    @Override
    protected void interpolate(double v) {
        double y = ball.getCenterY() - 10;
        double x = ball.getCenterX() + GameMenu.wind;

        if (y <= 10) {
            pane.getChildren().remove(ball);
            GameMenu.winOrLoosGame(false);
        }
        if (x <= 10 || x >= 590) {
            pane.getChildren().remove(ball);
            GameMenu.winOrLoosGame(false);
        }
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

}
