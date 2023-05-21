package view.gameMenu;

import controller.GameMenuController;
import controller.SettingMenuController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;
import model.MainCircle;
import view.animations.ShootingBall;
import view.loginMenu.LoginMenu;
import view.mainMenu.MainMenu;

import java.net.URL;

public class GameMenu extends Application {
    private static Stage stage;
    public static BorderPane borderPane;
    private static controller.GameMenuController controller = new controller.GameMenuController();
    private static Scene scene;
    public static MainCircle mainCircle;
    @Override
    public void start(Stage stage) throws Exception {
        GameMenu.stage = stage;

        GameMenuController.balls.clear();
        GameMenuController.timelines .clear();
        URL url = LoginMenu.class.getResource("/view/gameMenu/gameMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        url = LoginMenu.class.getResource("/view/gameMenu/gameMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        GameMenu.borderPane = borderPane;

        MainCircle mainCircle = new MainCircle();
        Text text = new Text(String.valueOf(CurrentGame.getPhase()));
        text.setTranslateX(292);
        text.setTranslateY(260);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(40));
        GameMenu.mainCircle = mainCircle;

//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(4.5), e -> reverse()),
//                new KeyFrame(Duration.seconds(9), e -> autoReverseOff())
//                );
//        timeline.setCycleCount(-1);
//        timeline.setAutoReverse(true);
//        timeline.play();
        controller.getNumberOfBallsEachPhase();
        Ball ball = createBallHandler();

        borderPane.getChildren().addAll(mainCircle, text, ball);
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

    private void reverse() {
        for (Timeline timeline : GameMenuController.timelines) {
            timeline.stop();
        }
        for (Timeline reverseTimeline : GameMenuController.reverseTimelines) {
            reverseTimeline.play();
        }
    }

    private void autoReverseOff() {
        for (Timeline reverseTimeline : GameMenuController.reverseTimelines) {
            reverseTimeline.stop();
        }
        for (Timeline timeline : GameMenuController.timelines) {
            timeline.play();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(GameMenu.stage);
    }

    public Ball createBallHandler() {
        Ball ball = new Ball();
        ball.requestFocus();
        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Tab"))
                    controller.freeze();
                else if (keyName.equals("Space")) {
                    if (controller.getNumberOfBalls() > 0) {
                        controller.shotBall();
                    } else {
                        try {
                            moveToNextPhase();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        return ball;
    }

    private void moveToNextPhase() throws Exception {
        CurrentGame.setPhase(CurrentGame.getPhase() + 1);
        new GameMenu().start(GameMenu.stage);
    }

    public static void loseTheGame() {
        Text text = new Text("Game over!\n" +
                "Enter any key to back to main menu\n" +
                "Score : " + scoreCalculator());
        text.setTranslateX(270);
        text.setTranslateY(500);
        text.setFont(Font.font(20));
        for (Timeline timeline : GameMenuController.timelines) {
            timeline.stop();
        }
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
                score += (i) * (CurrentGame.getNumberOfBalls() - controller.numberOfBalls);
            } else score += i * CurrentGame.getNumberOfBalls();
        }
        return score;
    }
}
