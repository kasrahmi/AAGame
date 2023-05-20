package view.LoginMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.MainMenu.ProfileMenu.ProfileMenu;

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
//        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//
//        pane.getChildren().addAll(background, background2);

        url = LoginMenu.class.getResource("/view/loginMenu/loginMenuButtons.fxml");
        BorderPane borderPane2 = FXMLLoader.load(url);
        pane.getChildren().add(borderPane2);


        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }
}