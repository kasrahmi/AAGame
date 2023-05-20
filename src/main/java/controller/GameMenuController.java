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
        ShootingBall shootingBall = new ShootingBall(ball);
        shootingBall.play();
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
