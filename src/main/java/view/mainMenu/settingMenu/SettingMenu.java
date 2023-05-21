package view.mainMenu.settingMenu;

import controller.SettingMenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.loginMenu.LoginMenu;

import java.net.URL;
import java.util.Set;

public class SettingMenu extends Application {

    public static Stage stage;
    public CheckBox easy;
    public CheckBox medium;
    public CheckBox hard;
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

//        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//
//        pane.getChildren().addAll(background, background2);

        url = LoginMenu.class.getResource("/view/settingMenu/settingMenuButtons.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

//        makeBorderPaneSetting(borderPane);
        pane.getChildren().add(borderPane);

        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }

}

