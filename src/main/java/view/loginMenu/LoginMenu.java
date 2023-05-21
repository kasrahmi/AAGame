package view.loginMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenu extends Application {
    public static Stage stage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;

        URL url = LoginMenu.class.getResource("/view/loginMenu/loginMenu.fxml");
        Pane pane = FXMLLoader.load(url);


        url = LoginMenu.class.getResource("/view/loginMenu/loginMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        pane.getChildren().add(borderPane);


        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }
}