package view.MainMenu.ProfileMenu;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.LoginMenu.LoginMenu;
import view.MainMenu.MainMenu;
import view.SignupMenu.SignupMenu;

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

    public ProfileMenu() {
        this.controller = new ProfileMenuController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenu.stage = stage;
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/profileMenu.fxml"));

        ImageView background = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
        ImageView background2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/background.png").toString(), 800 ,600, false, false));

        pane.getChildren().addAll(background, background2);

        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/view/profileMenu/profileMenuButtons.fxml"));
        pane.getChildren().add(borderPane);

        ProfileMenu.pane = pane;
        Scene scene = new Scene(pane);

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

    public void selectAvatar(MouseEvent mouseEvent) {

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
}
