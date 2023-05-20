package view.MainMenu.SettingMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.LoginMenu.LoginMenu;
import view.MainMenu.MainMenu;
import view.MainMenu.ProfileMenu.ProfileMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChangeNumberOfBalls extends Application {

    public static Stage stage;
    public ArrayList<Integer> numberOfBalls = new ArrayList<>(List.of(18,19,20,21));
    public ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    public SettingMenuController controller = new SettingMenuController();
    @Override
    public void start(Stage stage) throws Exception {
        SettingMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/settingMenu/settingMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));

        pane.getChildren().addAll(background, background2);

        url = LoginMenu.class.getResource("/view/settingMenu/numberOfBalls.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        makeBorderPaneSetting(borderPane);
        pane.getChildren().add(borderPane);

        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }

    private void makeBorderPaneSetting(BorderPane borderPane) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        HBox hBox = new HBox();
        makeDifficultyHBox(hBox);

        vBox.getChildren().add(hBox);
        borderPane.setCenter(vBox);
    }


    private void makeDifficultyHBox(HBox hBox) {
        hBox.getChildren().add(new Text("Number of balls :"));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        for (Integer numberOfBall : numberOfBalls) {
            HBox box = new HBox();
            box.setSpacing(10); box.setAlignment(Pos.CENTER);
            CheckBox checkBox = new CheckBox(String.valueOf(numberOfBall));
            checkBox.setId(String.valueOf(numberOfBall));
            box.getChildren().add(checkBox);
            hBox.getChildren().add(box);
            checkBoxes.add(checkBox);
        }
        for (CheckBox checkBox : checkBoxes) {
            makeOnActionCheckBoxes(checkBox);
        }
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.getId().equals(String.valueOf(controller.getNumberOfBalls()))) {
                checkBox.setSelected(true);
                break;
            }
        }
    }

    private void makeOnActionCheckBoxes(CheckBox checkBox) {
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)) {
                    for (CheckBox box : checkBoxes) {
                        if (!checkBox.equals(box))
                            box.setSelected(false);
                    }
                    controller.setNumberOfBalls(Integer.parseInt(checkBox.getText()));
                }
            }
        });
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(SettingMenu.stage);
    }

}

