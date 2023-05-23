package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import view.animations.RotateAnimation;
import view.animations.ShootingBall;
import view.gameMenu.GameMenu;

import java.util.ArrayList;

public class MainCircle extends Circle {
    RotateAnimation rotateAnimation;
    public MainCircle() {
        super(300, 250, 100);

        this.setFill(Color.BLACK);
//        this.setFill(new ImagePattern(
//                new Image("file:/Users/kasrahmi/Downloads/IMG_6538.png")));
        this.rotateAnimation = new RotateAnimation();
    }

    public RotateAnimation getRotateAnimation() {
        return rotateAnimation;
    }
}
