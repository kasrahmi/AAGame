package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.CurrentGame;
import view.gameMenu.GameMenu;

public class RotateAnimation extends Transition {
    double angeleRotate;
    Rotate rotate;
    Pane pane;
    public RotateAnimation() {
        pane = GameMenu.borderPane;
        rotate = new Rotate(0, 300, 250);
        angeleRotate = CurrentGame.getDifficulty().getRotateAngle();
        this.setCycleDuration(Duration.millis(CurrentGame.getDifficulty().getRotationTime()));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        rotate.setAngle(rotate.getAngle() + angeleRotate);
    }

    public double getAngeleRotate() {
        return angeleRotate;
    }

    public Rotate getRotate() {
        return rotate;
    }

    public void setAngeleRotate(double angeleRotate) {
        this.angeleRotate = angeleRotate;
    }
}
