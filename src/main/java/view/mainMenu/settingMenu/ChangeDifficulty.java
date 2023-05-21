package view.mainMenu.settingMenu;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.loginMenu.LoginMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChangeDifficulty extends Application {

    public static Stage stage;
    public CheckBox easy = new CheckBox("easy");
    public CheckBox medium = new CheckBox("medium");
    public CheckBox hard = new CheckBox("hard");
    public ArrayList<CheckBox> checkBoxes = new ArrayList<>(List.of(easy, medium, hard));
    public controller.SettingMenuController controller = new controller.SettingMenuController();
    @FXML
    private ListView listView;
    private Set<Integer> intSet;
    ObservableList observableList = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws Exception {
        SettingMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/settingMenu/settingMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        url = LoginMenu.class.getResource("/view/settingMenu/difficulty.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        easy.setId("3");
        medium.setId("2");
        hard.setId("1");

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
        hBox.getChildren().add(new Text("Hardness level :"));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        for (CheckBox checkBox : checkBoxes) {
            HBox box = new HBox();
            box.setSpacing(10); box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(checkBox);
            hBox.getChildren().add(box);
        }
        for (CheckBox checkBox : checkBoxes) {
            makeOnActionCheckBoxes(checkBox);
        }
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.getText().equals(String.valueOf(controller.getLevel()))) {
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
                        if (!checkBox.getText().equals(box.getText())) box.setSelected(false);
                    }
                controller.setDifficulty(checkBox.getText());
                }
            }
        });
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(SettingMenu.stage);
    }

}

