package view.MainMenu;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import view.LoginMenu.LoginMenu;
import view.MainMenu.ProfileMenu.ProfileMenu;
import view.MainMenu.ScoreBoardMenu.ScoreBoardMenu;

public class MainMenuController {

    public static controller.MainMenuController controller;
    public MainMenuController() {
        controller = new controller.MainMenuController();
    }
    public void start(MouseEvent mouseEvent) {

    }

    public void resume(MouseEvent mouseEvent) {

    }

    public void profile(MouseEvent mouseEvent) throws Exception {
        if (controller.isGuestMode()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entering profile menu failed");
            alert.setContentText("You have to login");
            alert.showAndWait();
        } else new ProfileMenu().start(MainMenu.stage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        controller.logout();
        new LoginMenu().start(MainMenu.stage);
    }

    public void scoreboard(MouseEvent mouseEvent) throws Exception{
        new ScoreBoardMenu().start(MainMenu.stage);
    }

    public void setting(MouseEvent mouseEvent) {

    }

    public static String usernameCurrentUser() {
        return controller.getUsername();
    }
}
