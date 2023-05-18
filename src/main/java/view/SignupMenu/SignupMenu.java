package view.SignupMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginMenu.LoginMenu;

import java.net.URL;

public class SignupMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        SignupMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/signupMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

}
