package view.MainMenu.ScoreBoardMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.LoginMenu.LoginMenu;
import view.MainMenu.MainMenu;
import view.MainMenu.ProfileMenu.ProfileMenu;

import java.net.URISyntaxException;
import java.net.URL;

public class ScoreBoardMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/scoreBoardMenu/scoreBoard.fxml");
        Pane pane = FXMLLoader.load(url);

        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));

        pane.getChildren().addAll(background, background2);

        url = LoginMenu.class.getResource("/view/scoreBoardMenu/scoreBoardText.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        VBox vBox = new VBox();
        vBox.getChildren().add(new Text("The scoreBoard :\n\n\n"));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        for (int i = 0; i < 10; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            Text text = new Text(360, 50, ScoreBoardMenuController.getScoreBoard(i));
            if (i <= 2) {
                ImageView imageView = getCupImage(i);
                hBox.getChildren().add(imageView);
            } else hBox.getChildren().add(new Text("\t"));
            hBox.getChildren().add(text);
            vBox.getChildren().add(hBox);
        }

        borderPane.setCenter(vBox);

        pane.getChildren().add(borderPane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private ImageView getCupImage(int i) throws URISyntaxException {
        return new ImageView(
                new Image(LoginMenu.class.getResource("/images/awards/" + (i + 1) + ".png").toURI().toString()
                        , 40 ,40, false, false));
    }
}
