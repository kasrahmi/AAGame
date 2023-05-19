package view.MainMenu.SettingMenu;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
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

public class SettingMenu extends Application {

    public static Stage stage;
    public CheckBox easy;
    public CheckBox medium;
    public CheckBox hard;
    public controller.SettingMenuController controller = new controller.SettingMenuController();
    @Override
    public void start(Stage stage) throws Exception {
        SettingMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/view/settingMenu/settingMenu.fxml");
        Pane pane = FXMLLoader.load(url);

        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));

        pane.getChildren().addAll(background, background2);

        url = LoginMenu.class.getResource("/view/settingMenu/settingMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        makeBorderPaneSetting(borderPane);
        pane.getChildren().add(borderPane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void makeBorderPaneSetting(BorderPane borderPane) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        HBox hBox = new HBox();
        makeHardnessLevelHBox(hBox);

        vBox.getChildren().add(hBox);
        borderPane.setCenter(vBox);
    }

    private void makeHardnessLevelHBox(HBox hBox) {
        hBox.getChildren().add(new Text("Hardness level :"));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        HBox hBox1 = new HBox();
        hBox1.setSpacing(10); hBox1.setAlignment(Pos.CENTER);
        this.easy = new CheckBox("1");
        hBox1.getChildren().addAll(new Text("Easy"), easy);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(10); hBox2.setAlignment(Pos.CENTER);
        this.medium = new CheckBox("2");
        hBox2.getChildren().addAll(new Text("Medium"), medium);

        HBox hBox3 = new HBox();
        hBox3.setSpacing(10); hBox3.setAlignment(Pos.CENTER);
        this.hard = new CheckBox("3");
        hBox3.getChildren().addAll(new Text("Hard"), hard);

        makeOnActionCheckBoxes(easy, medium, hard);
        makeOnActionCheckBoxes(medium, hard, easy);
        makeOnActionCheckBoxes(hard, easy, medium);

        hBox.getChildren().addAll(hBox1, hBox2, hBox3);
    }

    private void makeOnActionCheckBoxes(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3) {
        checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)) {
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                }
                controller.setLevel(Integer.parseInt(checkBox1.getText()));
            }
        });
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(SettingMenu.stage);
    }

}
