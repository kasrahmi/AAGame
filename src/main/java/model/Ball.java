package model;

import controller.GameMenuController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    public Ball() {
        super(300, 600, 10);
//        this.setFill(Color.BLACK);
//        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                String keyName = keyEvent.getCode().getName();
//
//                if (keyName.equals("Tab"))
//                    GameMenuController.freeze();
//                else if (keyName.equals("Space"))
//                    GameMenuController.shotBall(Ball.this);
//            }
//        });
    }
}
