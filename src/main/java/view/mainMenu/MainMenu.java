package view.mainMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.loginMenu.LoginMenu;

import java.net.URL;

public class MainMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/mainMenu/mainMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        HBox hBox = new HBox();
        Text username = new Text(360, 50, MainMenuController.usernameCurrentUser());
        username.setFont(Font.font(16));
        username.setFill(Paint.valueOf("black"));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);

        ImageView imageView;
        if ((imageView = MainMenuController.controller.getAvatar()) != null)
            hBox.getChildren().add(imageView);

        hBox.getChildren().add(username);

        pane.getChildren().add(hBox);

        url = LoginMenu.class.getResource("/view/mainMenu/mainButtons.fxml");
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
