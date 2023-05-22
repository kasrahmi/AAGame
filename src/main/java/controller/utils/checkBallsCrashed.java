package controller.utils;

import javafx.geometry.Point2D;
import model.Ball;

public class checkBallsCrashed {
    public static boolean twoBallsCrashed(Ball ball1, Ball ball2) {
        Point2D point2DFirstBall = ball1.localToScene(ball1.getCenterX(), ball1.getCenterY());
        Point2D point2DSecondBall = ball2.localToScene(ball2.getCenterX(), ball2.getCenterY());
        return Math.sqrt(Math.pow(point2DFirstBall.getX() - point2DSecondBall.getX(), 2) +
                Math.pow(point2DFirstBall.getY() - point2DSecondBall.getY(), 2)) <= ball1.getRadius() + ball2.getRadius();
    }
}
