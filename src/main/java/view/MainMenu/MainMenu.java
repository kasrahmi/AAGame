package view.MainMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.LoginMenu.LoginMenu;
import view.MainMenu.ProfileMenu.ProfileMenu;

import java.net.URL;

public class MainMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/mainMenu/mainMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));

        pane.getChildren().addAll(background, background2);

        HBox hBox = new HBox();
        Text username = new Text(360, 50, MainMenuController.usernameCurrentUser());
        username.setFill(Paint.valueOf("black"));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(username);
        pane.getChildren().add(hBox);

        url = LoginMenu.class.getResource("/view/mainMenu/mainButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        pane.getChildren().add(borderPane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
