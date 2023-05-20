package view.signupMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.loginMenu.LoginMenu;

import java.net.URL;

public class SignupMenu extends Application {
    public static Stage stage;
    public static Pane borderPane;

    @Override
    public void start(Stage stage) throws Exception {
        SignupMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/signupMenu/signupMenu.fxml");
        Pane pane = FXMLLoader.load(url);

//        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//
//        pane.getChildren().addAll(background, background2);

        url = LoginMenu.class.getResource("/view/signupMenu/signupMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        pane.getChildren().add(borderPane);
        SignupMenu.borderPane = pane;
        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }

}
