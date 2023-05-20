package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.awt.*;

public class InvisibleCircle extends Circle {
    public InvisibleCircle() {
        super(300, 250, 130);

        this.setFill(new ImagePattern(
                new Image(MainCircle.class.getResource("/images/circle.png").toExternalForm())));
    }
}
