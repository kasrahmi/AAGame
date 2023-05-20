package view.gameMenu;

import controller.SettingMenuController;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    @Override
    public void start(Stage stage) throws Exception {
        GameMenu.stage = stage;

        URL url = LoginMenu.class.getResource("/view/gameMenu/gameMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        url = LoginMenu.class.getResource("/view/gameMenu/gameMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        GameMenu.borderPane = borderPane;

        MainCircle mainCircle = new MainCircle();

        RotateTransition transition = new RotateTransition();
        transition.setNode(mainCircle);
        transition.setDuration(Duration.millis(1000));
        transition.setFromAngle(0);
        transition.setToAngle(360);
        transition.setCycleCount(-1);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();

        Ball ball = createBallHandler();

        borderPane.getChildren().addAll(mainCircle, ball);
        pane.getChildren().add(borderPane);
        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        borderPane.getChildren().get(borderPane.getChildren().size() - 1).requestFocus();
//        borderPane.getChildren().get(borderPane.getChildren().size() - 1).requestFocus();

        stage.setScene(scene);
        stage.show();
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
                    if (CurrentGame.getNumberOfBalls() > 0)
                        controller.shotBall(ball);
                }
            }
        });
        return ball;
    }

    public static void sendRequestFocus() {
        borderPane.getChildren().get(borderPane.getChildren().size() - 1).requestFocus();
    }
}
