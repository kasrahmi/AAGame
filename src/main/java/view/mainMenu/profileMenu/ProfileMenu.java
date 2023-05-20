package view.mainMenu.profileMenu;

import controller.ProfileMenuController;
import controller.SettingMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.loginMenu.LoginMenu;
import view.mainMenu.MainMenu;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ProfileMenu extends Application {

    public static Stage stage;
    private static Pane pane;
    public TextField newUsername;
    public TextField newPassword;
    public controller.ProfileMenuController controller;
    public Text changeUsername;
    public Text changePassword;
    public CheckBox firstAvatar;
    public File file;
    public ImageView imageView;

    public ProfileMenu() {
        this.controller = new ProfileMenuController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenu.stage = stage;
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/profileMenu.fxml"));

//        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
//
//        pane.getChildren().addAll(background, background2);

        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/profileMenuButtons.fxml"));
        pane.getChildren().add(borderPane);

        ProfileMenu.pane = pane;
        Scene scene = new Scene(pane);

        if (SettingMenuController.isDarkMode())
            scene.getStylesheets().add(LoginMenu.class.getResource("/styles/darkMode.css").toString());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/styles/menusStyle.css").toString());

        stage.setScene(scene);
        stage.show();
    }

    private HBox createHBox(HBox hBox, Button button) {
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(button);
        return hBox;
    }
    private Button createButton(Button button, String text, EventHandler<? super MouseEvent> mouseEvent) {
        button.setText(text);
        button.setOnMouseClicked(mouseEvent);
        return button;
    }
    private EventHandler<? super MouseEvent> show() {

        return null;
    }

    public void changeUsername(MouseEvent mouseEvent) throws IOException {
        pane.getChildren().remove(pane.getChildren().size() - 1);
        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/changeUsername.fxml"));
        pane.getChildren().add(borderPane);
    }

    public void changePassword(MouseEvent mouseEvent) throws IOException {
        pane.getChildren().remove(pane.getChildren().size() - 1);
        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/changePassword.fxml"));
        pane.getChildren().add(borderPane);
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Deleting account");
        alert.setContentText("Are you sure you want to delete your account?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            controller.removeAccount();
            new LoginMenu().start(ProfileMenu.stage);
        }
    }

    public void avatarMenu(MouseEvent mouseEvent) throws IOException {
        pane.getChildren().remove(pane.getChildren().size() - 1);
        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/changeAvatar.fxml"));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        HBox hBox1 = new HBox();
        ImageView imageView1 = controller.getAvatarById("1");
        CheckBox checkBox1 = new CheckBox("1");
        createAvatarHBox(hBox1, checkBox1, imageView1);
        hBox.getChildren().add(hBox1);

        HBox hBox2 = new HBox();
        ImageView imageView2 = controller.getAvatarById("2");
        CheckBox checkBox2 = new CheckBox("2");
        createAvatarHBox(hBox2, checkBox2, imageView2);
        hBox.getChildren().add(hBox2);

        HBox hBox3 = new HBox();
        ImageView imageView3 = controller.getAvatarById("3");
        CheckBox checkBox3 = new CheckBox("3");
        createAvatarHBox(hBox3, checkBox3, imageView3);
        hBox.getChildren().add(hBox3);

        HBox hBox4 = new HBox();
        hBox4.setAlignment(Pos.CENTER);
        hBox4.setSpacing(10);
        FileChooser fileChooser = new FileChooser();
        Button browse = new Button("Browse");
        setActionBrowse(browse, fileChooser, hBox4, checkBox1, checkBox2, checkBox3);

        hBox4.getChildren().add(browse);
        hBox.getChildren().add(hBox4);

        makeListeners(checkBox1, checkBox2, checkBox3);
        makeListeners(checkBox2, checkBox1, checkBox3);
        makeListeners(checkBox3, checkBox1, checkBox2);

//        borderPane.setTop(browse);
        borderPane.setCenter(hBox);
        pane.getChildren().add(borderPane);
    }

    private void setActionBrowse(Button browse, FileChooser fileChooser, HBox hBox, CheckBox checkBox1,
                                 CheckBox checkBox2, CheckBox checkBox3) {
        browse.setOnAction((event) ->
        {
            file = fileChooser.showOpenDialog(stage);
            imageView = new ImageView(file.toURI().toString());
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            if (hBox.getChildren().size() > 1) hBox.getChildren().remove(1);
            hBox.getChildren().add(imageView);
            checkBox1.setSelected(false);
            checkBox2.setSelected(false);
            checkBox3.setSelected(false);
            controller.changeAvatarCustom(file.toURI().toString());
        });
    }

    private void makeListeners(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3) {
        checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)) {
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                }
                controller.changeAvatarById(checkBox1.getText());
            }
        });
    }

    public void changeUsernameSubmit(MouseEvent mouseEvent) {
        changeUsername.setText(controller.changeUsername(newUsername.getText()));
    }

    public void backToProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(ProfileMenu.stage);
    }

    public void changePasswordSubmit(MouseEvent mouseEvent) {
        changePassword.setText(controller.changePassword(newPassword.getText()));
    }

    public void backToMain(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(ProfileMenu.stage);
    }

    public void createAvatarHBox(HBox hBox, CheckBox checkBox, ImageView imageView) {
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().add(checkBox);
        hBox.getChildren().add(imageView);
    }
}
