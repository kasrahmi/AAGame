package view.loginMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;

public class LoginMenu extends Application {
    public static Stage stage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
//        stage.getIcons().add(new Image(LoginMenu.class.getResource("icon.png").toURI().toString()));

        URL url = LoginMenu.class.getResource("/view/loginMenu/loginMenu.fxml");
        Pane pane = FXMLLoader.load(url);


        url = LoginMenu.class.getResource("/view/loginMenu/loginMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        pane.getChildren().add(borderPane);
        var icon = new Image("icon.png");
        stage.getIcons().add(icon);

//        if (Taskbar.isTaskbarSupported()) {
//            var taskbar = Taskbar.getTaskbar();
//            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
//                final Toolkit toolkit = Toolkit.getDefaultToolkit();
//                var dockIcon = toolkit.getImage(String.valueOf(getClass().getClassLoader().getResource("icon.png")));
//                System.out.println(dockIcon);
//                System.out.println(toolkit);
//                System.out.println(taskbar);
//                System.out.println(taskbar.getIconImage());
//                taskbar.setIconImage(dockIcon);
//            }
//        }

        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }
}