package model;

import controller.GameMenuController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    public Ball(Color color) {
        super(300, 600, 10);
        this.setFill(color);
    }
    public Ball(double xCenter, double yCenter, Color color) {
        super(xCenter, yCenter, 10);
        this.setFill(color);
    }
}
