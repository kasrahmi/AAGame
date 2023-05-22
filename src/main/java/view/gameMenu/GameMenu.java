package view.gameMenu;

import controller.GameMenuController;
import controller.SettingMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.CurrentGame;
import model.MainCircle;
import view.loginMenu.LoginMenu;
import view.mainMenu.MainMenu;

import java.net.URL;

public class GameMenu extends Application {
    private static Stage stage;
    public static BorderPane borderPane;
    private static controller.GameMenuController controller = new controller.GameMenuController();
    private static Scene scene;
    public static MainCircle mainCircle;
    public Text numberOfBall = new Text();
    @Override
    public void start(Stage stage) throws Exception {
        GameMenu.stage = stage;


//        borderPane.getChildren().removeAll(GameMenuController.timelines);
//        GameMenuController.balls.clear();
//        GameMenuController.timelines.clear();
//        GameMenuController.lines.clear();
        URL url = LoginMenu.class.getResource("/view/gameMenu/gameMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        url = LoginMenu.class.getResource("/view/gameMenu/gameMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        GameMenu.borderPane = borderPane;
        for (Node child : borderPane.getChildren()) {
            if (child instanceof Line) {
                borderPane.getChildren().remove(child);
                break;
            }
        }
        borderPane.getChildren().removeAll(GameMenuController.balls);
        borderPane.getChildren().removeAll(GameMenuController.lines);

        GameMenuController.balls.clear();
        GameMenuController.timelines.clear();
        GameMenuController.lines.clear();

        MainCircle mainCircle = new MainCircle();
        Text text = new Text(String.valueOf(CurrentGame.getPhase()));
        text.setTranslateX(292);
        text.setTranslateY(260);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(40));
        GameMenu.mainCircle = mainCircle;

        if (CurrentGame.getPhase() > 1) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(4), e -> reverseTimeLine())
            );
            timeline.setCycleCount(-1);
            timeline.setAutoReverse(true);
            timeline.play();
        }

        controller.getNumberOfBallsEachPhase();
        Ball ball = createBallHandler();

        numberOfBall.setX(310);
        numberOfBall.setY(600);
        numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
        numberOfBall.setFill(Color.WHITE);

        borderPane.getChildren().addAll(mainCircle, text, numberOfBall, ball);
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

    private void reverseTimeLine() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(getRandomTime()), e -> reverse()));
        timeline.setCycleCount(0);
        timeline.play();
    }

    private void reverse() {
        GameMenuController.rotateAnimation.setAngeleRotate((-1) * GameMenuController.rotateAnimation.getAngeleRotate());
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(GameMenu.stage);
    }

    public Ball createBallHandler() {
        Ball ball = new Ball();
        ball.requestFocus();
        if (GameMenuController.numberOfBalls == CurrentGame.getNumberOfBalls()) {
            for (int i = 0; i < borderPane.getChildren().size(); i++) {
                if (borderPane.getChildren().get(i) instanceof Line) {
                    borderPane.getChildren().remove(borderPane.getChildren().get(i));
                    i--;
                }
            }
        }
        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Tab")) {
                    controller.freeze();
                    ball.requestFocus();
                }
                else if (keyName.equals("Space")) {
                    if (controller.getNumberOfBalls() > -1) {
                        if (GameMenuController.numberOfBalls == 0) {
                            borderPane.getChildren().remove(ball);
                            borderPane.getChildren().remove(numberOfBall);
                            try {
                                moveToNextPhase();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        controller.shotBall();
                        numberOfBall.setText(String.valueOf(GameMenuController.numberOfBalls));
                    }
                }
            }
        });
        return ball;
    }

    private void moveToNextPhase() throws Exception {
        CurrentGame.setPhase(CurrentGame.getPhase() + 1);
        start(GameMenu.stage);
    }

    public static void loseTheGame() {
        Text text = new Text("Game over!\n" +
                "Enter any key to back to main menu\n" +
                "Score : " + scoreCalculator());
        text.setTranslateX(270);
        text.setTranslateY(500);
        text.setFont(Font.font(20));
        GameMenuController.rotateAnimation.stop();
        if (CurrentGame.getLoggedInUser() != null) CurrentGame.getLoggedInUser().setHighScore(scoreCalculator());
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
}
