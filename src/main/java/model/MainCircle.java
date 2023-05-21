package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MainCircle extends Circle {
    public MainCircle() {
        super(300, 250, 100);

//        this.setFill(Color.BLACK);
        this.setFill(new ImagePattern(
                new Image("file:/Users/kasrahmi/Downloads/IMG_6538.png")));
    }
}
