package view.gameMenu;

import controller.GameMenuController;
import controller.SettingMenuController;
import controller.utils.checkBallsCrashed;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;
import model.Database;
import model.MainCircle;
import view.loginMenu.LoginMenu;
import view.mainMenu.MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameMenu extends Application {
    private static Stage stage;
    public static BorderPane borderPane;
    private static controller.GameMenuController controller = new controller.GameMenuController();
    private static Scene scene;
    public static MainCircle mainCircle;
    public static Text numberOfBall = new Text();
    static ArrayList<Ball> ballsGotBigger = new ArrayList<>();
    static Timeline changeRadiusTimeline = new Timeline();
    static Timeline reverseTimeLine = new Timeline();
    static Timeline invisbleTimeLine = new Timeline();
    static BorderPane pausePane = new BorderPane();
    public static Ball ball = new Ball();
    public Button resumeButton = new Button();
    public Text score;
    public ProgressBar progressBar;
    public ArrayList<Ball> defaultBalls = new ArrayList<>();
    public static MediaPlayer mediaPlayer;
    public static double time = 0.0;
    public static Timer timer;
    public static TimerTask task;
    public static Label label;

    @Override
    public void start(Stage stage) throws Exception {
        GameMenu.stage = stage;


        invisbleTimeLine = new Timeline();
        reverseTimeLine = new Timeline();
        changeRadiusTimeline = new Timeline();
//        borderPane.getChildren().removeAll(GameMenuController.timelines);
//        GameMenuController.balls.clear();
//        GameMenuController.timelines.clear();
//        GameMenuController.lines.clear();
        URL url = LoginMenu.class.getResource("/view/gameMenu/gameMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        url = LoginMenu.class.getResource("/view/gameMenu/gameMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        if (!CurrentGame.isMuteSong()) {
            Media media = new Media(getClass().getResource("/sounds/1.mp3").toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }

        progressBar = new ProgressBar(0);
        progressBar.setFocusTraversable(false);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setFocusTraversable(false);
        vBox.getChildren().add(progressBar);

        Label label = new Label("seconds : " + time);
        this.label = label;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                time += 0.5;
                Platform.runLater(() -> label.setText("seconds : " + time));
            }
        };
        GameMenu.timer = timer;
        GameMenu.task = task;
        timer.scheduleAtFixedRate(task, 0, 500);

        GameMenu.borderPane = borderPane;
        for (Node child : borderPane.getChildren()) {
            if (child instanceof Line) {
                borderPane.getChildren().remove(child);
                break;
            }
        }

        URL urlPause = LoginMenu.class.getResource("/view/gameMenu/pauseMenu.fxml");
        GameMenu.pausePane = FXMLLoader.load(urlPause);

        borderPane.getChildren().removeAll(GameMenuController.balls);
        borderPane.getChildren().removeAll(GameMenuController.lines);

        GameMenuController.balls.clear();
        GameMenuController.timelines.clear();
        GameMenuController.lines.clear();
        ballsGotBigger.clear();

        MainCircle mainCircle = new MainCircle();
        Text text = new Text(String.valueOf(CurrentGame.getPhase()));
        text.setTranslateX(292);
        text.setTranslateY(260);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(40));
        GameMenu.mainCircle = mainCircle;
        GameMenuController.rotateAnimation = mainCircle.getRotateAnimation();
        GameMenuController.rotateAnimation.play();
        makeDefaultBalls();

        if (CurrentGame.getPhase() > 1) {
            triggerReverse();
            changeBallSize();
            if (CurrentGame.getPhase() >= 3) invisibleTimeLineMaker();
            if (CurrentGame.getPhase() == 4) {
                Label windLabel = new Label("wind speed is : - " + (CurrentGame.getDifficulty().getWindSpeed() * 4));
                borderPane.setRight(windLabel);
            }
        }
        controller.getNumberOfBallsEachPhase();

        Ball ball = createBallHandler();

        score = new Text();
        borderPane.getChildren().add(score);
        score.setY(15);
        score.setX(20);
        score.setStrokeWidth(20);
        score.setText("0");


        numberOfBall.setX(310);
        numberOfBall.setY(600);
        numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
        numberOfBall.setFill(Color.WHITE);

        borderPane.setTop(vBox);
        borderPane.setLeft(label);
        borderPane.getChildren().addAll(mainCircle, text, numberOfBall);
        for (Ball defaultBall : defaultBalls) {
            borderPane.getChildren().add(defaultBall);
        }
        borderPane.getChildren().add(ball);
        pane.getChildren().add(borderPane);
        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        borderPane.getChildren().get(borderPane.getChildren().size() - 1).requestFocus();
//        borderPane.getChildren().get(borderPane.getChildren().size() - 1).requestFocus();

        GameMenu.scene = scene;
        stage.setScene(scene);
        stage.show();
    }

    private void makeDefaultBalls() {
        int rotation = 360 / CurrentGame.getNumberOfDefaultBalls();
        GameMenuController.rotateAnimation.play();
        controller.getNumberOfBallsEachPhase();
        for (int i = 0; i < CurrentGame.getNumberOfDefaultBalls(); i++) {
            Ball ball = new Ball();
            ball.setCenterX(300);
            ball.setCenterY(440);
            Rotate rotate = new Rotate(30 + (rotation * i), 300, 250);
            ball.getTransforms().add(rotate);
            GameMenuController.rotationBalls(ball, rotate);
            defaultBalls.add(ball);
        }
        controller.getNumberOfBallsEachPhase();
    }

    private void invisibleTimeLineMaker() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> makeBallsInvisible()));
        invisbleTimeLine = timeline;
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void makeBallsInvisible() {
        for (Group group : GameMenuController.groups) {
            group.setVisible(false);
        }
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> makeBallsVisible()));
        timeline.setCycleCount(0);
        timeline.play();
    }

    private void makeBallsVisible() {
        for (Group group : GameMenuController.groups) {
            group.setVisible(true);
        }
    }

    private void changeBallSize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> makeBallBigger()));
        changeRadiusTimeline = timeline;
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void makeBallBigger() {
        for (Ball ball : GameMenuController.balls) {
            ballsGotBigger.add(ball);
            ball.setRadius(ball.getRadius() * 1.15);
        }
        if (intersectBalls()) {
            ballsGotBigger.clear();
            loseTheGame();
        }
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> makeBallSmaller()));
        timeline.setCycleCount(0);
        timeline.play();
    }

    private void makeBallSmaller() {
        for (int i = 0; i < ballsGotBigger.size(); i++) {
            ballsGotBigger.get(i).setRadius(ballsGotBigger.get(i).getRadius() * 100 / 115);
            ballsGotBigger.remove(ballsGotBigger.get(i));
            i--;
        }
    }

    private boolean intersectBalls() {
        for (Ball ball : GameMenuController.balls) {
            for (Ball ball1 : GameMenuController.balls) {
                if (!ball1.equals(ball) && checkBallsCrashed.twoBallsCrashed(ball, ball1))
                    return true;
            }
        }
        return false;
    }

    private void triggerReverse() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(4), e -> reverseTimeLine())
        );
        reverseTimeLine = timeline;
        timeline.setCycleCount(-1);
        timeline.setAutoReverse(true);
        timeline.play();
    }

    private void reverseTimeLine() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(getRandomTime()), e -> reverse()));
        timeline.setCycleCount(0);
        timeline.play();
    }

    private void reverse() {
        GameMenuController.rotateAnimation.setAngeleRotate((-1) * GameMenuController.rotateAnimation.getAngeleRotate());
    }

    public Ball createBallHandler() {
        Ball ball = new Ball();
        GameMenu.ball = ball;
        ball.requestFocus();
        ball.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Tab")) {
                    if (progressBar.getProgress() == 1.0) {
                        controller.freeze();
                        progressBar.setProgress(0.0);
                    }
                    ball.requestFocus();
                }
                else if (keyName.equals("Space")) {
                    if (controller.getNumberOfBalls() >= 0) {
                        chargeFreeze();
                        if (controller.getNumberOfBalls() != 0) {
                            controller.shotBall();
                            numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                            score.setText(String.valueOf(scoreCalculator()));
                        }
                    }
                } else if (keyName.equals("Ctrl")) pause();
                else if (keyName.equals("Left") && CurrentGame.getPhase() == 4) controller.moveBallLeft();
                else if (keyName.equals("Right") && CurrentGame.getPhase() == 4) controller.moveBallRight();
            }
        });
        return ball;
    }

    private void chargeFreeze() {
        if (progressBar.getProgress() != 1.0) {
            progressBar.setProgress(progressBar.getProgress() + 0.2);
        }
    }

    public static void moveToNextPhase() {
        pauseTimer();
        invisbleTimeLine.stop();
        reverseTimeLine.stop();
        changeRadiusTimeline.stop();
        GameMenuController.rotateAnimation.stop();
        borderPane.setStyle("-fx-background-color: #32cd32;");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        GameMenuController.rotateAnimation.setAngeleRotate(CurrentGame.getDifficulty().getRotateAngle());
                        CurrentGame.setPhase(CurrentGame.getPhase() + 1);
                        try {
                            new GameMenu().start(GameMenu.stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        timeline.setCycleCount(0);
        timeline.play();
    }

    public static void loseTheGame() {
        pauseTimer();
        Text text = new Text("Game over!\n" +
                "Enter any key to back to main menu\n" +
                "Score : " + scoreCalculator());
        text.setTranslateX(270);
        text.setTranslateY(500);
        text.setFont(Font.font(20));
        borderPane.setStyle("-fx-background-color: #8b0000;");
        GameMenuController.rotateAnimation.stop();
        ballsGotBigger.clear();
        GameMenu.invisbleTimeLine.stop();
        GameMenu.reverseTimeLine.stop();
        GameMenu.changeRadiusTimeline.stop();
        GameMenu.invisbleTimeLine = new Timeline();
        GameMenu.reverseTimeLine = new Timeline();
        GameMenu.changeRadiusTimeline = new Timeline();
        if (CurrentGame.getLoggedInUser() != null) CurrentGame.getLoggedInUser().setScore(scoreCalculator(), time);
        GameMenu.time = 0;
        CurrentGame.setPhase(1);
        GameMenu.scene.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                keyEvent.getCode().getName();
                try {
                    GameMenuController.balls.clear();
                    GameMenuController.timelines.clear();
                    new MainMenu().start(GameMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }));
        borderPane.getChildren().add(text);
    }

    private static int scoreCalculator() {
        int score = 0;
        for (int i = 0; i <= CurrentGame.getPhase(); i++) {
            if (i == CurrentGame.getPhase()) {
                score += (i) * (CurrentGame.getNumberOfBalls() - GameMenuController.numberOfBalls);
            } else score += i * CurrentGame.getNumberOfBalls();
        }
        return score * CurrentGame.getDifficulty().getLevelDifficulty();
    }

    private double getRandomTime() {
        return Math.random() * 4;
    }

    public void pause() {
        pauseTimer();
        invisbleTimeLine.stop();
        reverseTimeLine.stop();
        changeRadiusTimeline.stop();
        Button button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resume();
            }
        });
        button.setVisible(true);
        borderPane.getChildren().add(button);
        button.requestFocus();
        GameMenuController.rotateAnimation.pause();
        if (GameMenuController.shootingBall != null) GameMenuController.shootingBall.pause();
        borderPane.setCenter(pausePane);
    }

    public void resume() {
        borderPane.getChildren().remove(pausePane);
        invisbleTimeLine.play();
        reverseTimeLine.play();
        changeRadiusTimeline.play();
        GameMenuController.rotateAnimation.play();
        if (GameMenuController.shootingBall != null) GameMenuController.shootingBall.play();
        resumeTimer();
        ball.requestFocus();
    }

    private static void pauseTimer() {
        timer.cancel();
    }

    private static void resumeTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                time += 0.5;
                Platform.runLater(() -> label.setText("seconds : " + time));
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);
    }

    public void saveGameAndExit() throws Exception {
        //TODO save map
        exit();
    }

    public void exit() throws Exception {
        new MainMenu().start(GameMenu.stage);
    }

    public void changeMusic(MouseEvent mouseEvent) {
        mediaPlayer.stop();
        CurrentGame.setSongNumber(CurrentGame.getSongNumber() % 3 + 1);
        Media media = new Media(getClass().getResource("/sounds/" + CurrentGame.getSongNumber() + ".mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void muteMusic(MouseEvent mouseEvent) {
        SettingMenuController.setMuteAndUnMute(!SettingMenuController.isSongMute());
        if (SettingMenuController.isSongMute()) GameMenu.mediaPlayer.stop();
        else GameMenu.mediaPlayer.play();
    }

    public void buttonGuide(MouseEvent mouseEvent) {

    }

    public void restart(MouseEvent mouseEvent) {

    }
}
