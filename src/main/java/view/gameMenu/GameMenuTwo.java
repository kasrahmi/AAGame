package view.gameMenu;

import controller.GameMenuController;
import controller.GameMenuTwoController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;

public class GameMenuTwo extends GameMenu{
    public static Stage stage;
    public static GameMenuTwoController controller = new GameMenuTwoController();
    public static Ball topBall = new Ball(Color.WHITE);
    public Text numberOfTopBall = new Text();
    public boolean firstPlayer = true;
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        twoPlayer = true;
        GameMenuTwo.stage = stage;

        numberOfTopBall.setX(310);
        numberOfTopBall.setY(20);
        numberOfTopBall.setText(String.valueOf(GameMenuController.numberOfBalls));
        numberOfTopBall.setFill(Color.WHITE);
        numberOfTopBall.setFocusTraversable(false);
        borderPane.getChildren().add(numberOfTopBall);
    }

    @Override
    public Ball createBallHandler() {
        Ball ball = new Ball(Color.BLACK);
        GameMenu.ball = ball;
        ball.requestFocus();
        Ball topBall = new Ball(Color.WHITE);
        topBall.setCenterX(300);
        topBall.setCenterY(20);
//        setTopBallWork();
        GameMenuTwo.topBall = topBall;
        borderPane.getChildren().add(topBall);
        topBall.setVisible(true);
        ball.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode keyName = keyEvent.getCode();

                if (firstPlayer) {
                    if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                        if (controller.getNumberOfBalls() >= 0) {
                            chargeFreeze();
                            if (controller.getNumberOfBalls() != 0) {
                                controller.shotBall();
                                numberOfTopBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                                numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                                score.setText(String.valueOf(scoreCalculator()));
                                firstPlayer = false;
                            }
                        }
                    } else if (keyName.equals(KeyCode.LEFT) && CurrentGame.getPhase() == 4) controller.moveBallLeft(ball);
                    else if (keyName.equals(KeyCode.RIGHT) && CurrentGame.getPhase() == 4) controller.moveBallRight(ball);
                } else {
                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                        if (controller.getNumberOfBalls() >= 0) {
                            chargeFreeze();
                            if (controller.getNumberOfBalls() != 0) {
                                controller.shotBallFromTop();
                                numberOfTopBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                                numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                                score.setText(String.valueOf(scoreCalculator()));
                                firstPlayer = true;
                            }
                        }
                    } else if (keyName.equals(KeyCode.LEFT) && CurrentGame.getPhase() == 4) controller.moveBallLeft(topBall);
                    else if (keyName.equals(KeyCode.RIGHT) && CurrentGame.getPhase() == 4) controller.moveBallRight(topBall);
                }
                if (keyName.equals(CurrentGame.getFreeze())) {
                    if (progressBar.getProgress() == 1.0) {
                        borderPane.setStyle("-fx-background-color: #0000CD;");
                        controller.freeze();
                        progressBar.setProgress(0.0);
                    }
                    ball.requestFocus();
                } else if (keyName.equals(CurrentGame.getPause())) pause();
            }
        });
        return ball;
    }

    private void setTopBallWork() {
        topBall.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("ghable inja");
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    System.out.println("inja");
                    if (controller.getNumberOfBalls() >= 0) {
                        chargeFreeze();
                        if (controller.getNumberOfBalls() != 0) {
                            controller.shotBallFromTop();
                            numberOfTopBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                            numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                            score.setText(String.valueOf(scoreCalculator()));
                            numberOfBall.setVisible(true); ball.setVisible(true);
                            topBall.setVisible(false); numberOfTopBall.setVisible(false);
                            ball.requestFocus();
                        }
                    }
                }
            }
        });
    }

    public static void moveToNextPhase() {
        pauseTimer();
        invisbleTimeLine.stop();
        reverseTimeLine.stop();
        changeRadiusTimeline.stop();
        windTimeline.stop();
        GameMenuController.rotateAnimation.stop();
        borderPane.setStyle("-fx-background-color: #32cd32;");
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GameMenuController.rotateAnimation.setAngeleRotate(CurrentGame.getDifficulty().getRotateAngle());
                    CurrentGame.setPhase(CurrentGame.getPhase() + 1);
                    if (CurrentGame.getPhase() == 5) {
                        winOrLoosGame(true);
                    }
                    else {
                        try {
                            new GameMenuTwo().start(GameMenuTwo.stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }));
        timeline.setCycleCount(0);
        timeline.play();
    }
}
