package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MainCircle extends Circle {
    public MainCircle() {
        super(300, 250, 100);

        this.setFill(new ImagePattern(
                new Image(MainCircle.class.getResource("/images/circle.png").toExternalForm())));
    }
}
