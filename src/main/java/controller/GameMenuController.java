package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Ball;
import model.CurrentGame;
import view.animations.ShootingBall;
import view.gameMenu.GameMenu;

public class GameMenuController {
    public void freeze() {

    }

    public void shotBall(Ball ball) {
        Ball newBall = new Ball();
        GameMenu.borderPane.getChildren().add(newBall);
        ShootingBall shootingBall = new ShootingBall(newBall);
        shootingBall.play();
        CurrentGame.setNumberOfBalls(CurrentGame.getNumberOfBalls() - 1);
//        if (CurrentGame.getNumberOfBalls() > 0) {
//            Ball newBall = new Ball();
//            GameMenu.borderPane.getChildren().add(newBall);
//        }
//        if (CurrentGame.getNumberOfBalls() > 0) {
//            Ball newBall = new Ball();
//            newBall.setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent keyEvent) {
//                    String keyName = keyEvent.getCode().getName();
//
//                    if (keyName.equals("Tab"))
//                        freeze();
//                    else if (keyName.equals("Space"))
//                        shotBall(ball);
//                }
//            });
//            GameMenu.borderPane.getChildren().add(newBall);
//        }
    }
}
