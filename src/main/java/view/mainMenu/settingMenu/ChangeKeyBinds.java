package view.mainMenu.settingMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CurrentGame;
import view.loginMenu.LoginMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChangeKeyBinds extends Application {
    public static Stage stage;
    private static Scene scene;
    public Label shoot = new Label();
    public Button changeShoot;
    public Label freeze = new Label();
    public Button changeFreeze;
    public Label pause = new Label();
    public Button changePause;

    @Override
    public void start(Stage stage) throws Exception {
        ChangeKeyBinds.stage = stage;

        URL url = LoginMenu.class.getResource("/view/settingMenu/settingMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        url = LoginMenu.class.getResource("/view/settingMenu/keyBinds.fxml");
        BorderPane borderPane = FXMLLoader.load(url);


        pane.getChildren().add(borderPane);

        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        ChangeKeyBinds.scene = scene;
        stage.show();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(SettingMenu.stage);
    }

    public void changeShootBind(ActionEvent actionEvent) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                CurrentGame.setShoot(keyEvent.getCode());
            }
        });
    }

    public void changeFreezeBind(ActionEvent actionEvent) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                CurrentGame.setFreeze(keyEvent.getCode());
            }
        });
    }

    public void changePauseBind(ActionEvent actionEvent) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                CurrentGame.setPause(keyEvent.getCode());
            }
        });
    }
}
