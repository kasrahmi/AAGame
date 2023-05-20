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

//    private void makeBorderPaneSetting(BorderPane borderPane) {
//        VBox vBox = new VBox();
//        vBox.setAlignment(Pos.CENTER);
//        vBox.setSpacing(10);
//
//        HBox hBox = new HBox();
//        makeHardnessLevelHBox(hBox);
//
//        HBox hBox1 = new HBox();
//        makeListViewHBox(hBox1);
//
//        vBox.getChildren().add(hBox);
//        borderPane.setCenter(vBox);
//    }

//    private void makeListViewHBox(HBox hBox) {
//        for (int i = 4; i < 7; i++) {
//            intSet.add(i);
//        }
//        observableList.setAll(intSet);
//        listView.setItems(observableList);
//        listView.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>()
//        {
//            @Override
//            public ListCell<Integer> call(ListView<Integer> listView)
//            {
//                return new ListViewCell();
//            }
//        });
//    }

//    private void makeHardnessLevelHBox(HBox hBox) {
//        hBox.getChildren().add(new Text("Hardness level :"));
//        hBox.setAlignment(Pos.CENTER);
//        hBox.setSpacing(10);
//
//        HBox hBox1 = new HBox();
//        hBox1.setSpacing(10); hBox1.setAlignment(Pos.CENTER);
//        this.easy = new CheckBox("1");
//        hBox1.getChildren().addAll(new Text("Easy"), easy);
//
//        HBox hBox2 = new HBox();
//        hBox2.setSpacing(10); hBox2.setAlignment(Pos.CENTER);
//        this.medium = new CheckBox("2");
//        hBox2.getChildren().addAll(new Text("Medium"), medium);
//
//        HBox hBox3 = new HBox();
//        hBox3.setSpacing(10); hBox3.setAlignment(Pos.CENTER);
//        this.hard = new CheckBox("3");
//        hBox3.getChildren().addAll(new Text("Hard"), hard);
//
//        makeOnActionCheckBoxes(easy, medium, hard);
//        makeOnActionCheckBoxes(medium, hard, easy);
//        makeOnActionCheckBoxes(hard, easy, medium);
//
//        hBox.getChildren().addAll(hBox1, hBox2, hBox3);
//    }

//    private void makeOnActionCheckBoxes(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3) {
//        checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if (newValue.equals(true)) {
//                    checkBox2.setSelected(false);
//                    checkBox3.setSelected(false);
//                }
//                controller.setLevel(Integer.parseInt(checkBox1.getText()));
//            }
//        });
//    }

//    public void back(MouseEvent mouseEvent) throws Exception {
//        new MainMenu().start(SettingMenu.stage);
//    }

}

