package controller;

import controller.utils.checkBallsCrashed;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import model.Ball;
import view.animations.ShootingBall;
import view.animations.ShootingBallFromTop;
import view.gameMenu.GameMenu;
import view.gameMenu.GameMenuTwo;

public class GameMenuTwoController extends GameMenuController{
    public static ShootingBallFromTop shootingBallFromTop;
    public void shotBallFromTop() {
        Text text = new Text(String.valueOf(numberOfBalls));
        text.setX(300); text.setY(20);
        text.setFont(Font.font(10));
        text.setFill(Color.BLACK);

        Ball newBall = new Ball(GameMenuTwo.topBall.getCenterX(),GameMenuTwo.topBall.getCenterY(), Color.WHITE);
        GameMenu.borderPane.getChildren().addAll(newBall, text);
        numberOfBalls--;
        ShootingBallFromTop shootingBallFromTop = new ShootingBallFromTop(newBall, text);
        GameMenuTwoController.shootingBallFromTop = shootingBallFromTop;
        shootingBallFromTop.play();
    }

    public static void rotationBalls(Ball ball, Text text) {
        Rotate rotate = new Rotate((-1)*rotateAnimation.getRotate().getAngle(), 300, 250);
        ball.getTransforms().addAll(rotateAnimation.getRotate(), rotate);
        text.getTransforms().addAll(rotateAnimation.getRotate(), rotate);

        Line line = new Line(ball.getCenterX(), ball.getCenterY() + 15, 300, 250);
        line.setStrokeWidth(2);
        line.getTransforms().addAll(rotate, rotateAnimation.getRotate());
        GameMenu.borderPane.getChildren().add(0, line);
        lines.add(line);

        GameMenuController.makeGroups(ball, line, text);

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
}
