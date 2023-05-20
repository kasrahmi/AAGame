package view.gameMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ball;
import model.InvisibleCircle;
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

        InvisibleCircle invisibleCircle = new InvisibleCircle();
        MainCircle mainCircle = new MainCircle();
        Ball ball = new Ball();

        createBallHandler(ball);

        borderPane.getChildren().addAll(invisibleCircle, mainCircle, ball);
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

    public void createBallHandler(Ball ball) {
        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Tab"))
                    controller.freeze();
                else if (keyName.equals("Space"))
                    controller.shotBall(ball);
            }
        });
    }
}
