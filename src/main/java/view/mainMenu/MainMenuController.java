package view.mainMenu;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Database;
import view.gameMenu.GameMenu;
import view.gameMenu.GameMenuTwo;
import view.loginMenu.LoginMenu;
import view.mainMenu.profileMenu.ProfileMenu;
import view.mainMenu.scoreBoardMenu.ScoreBoardMenu;
import view.mainMenu.settingMenu.SettingMenu;

public class MainMenuController {

    public static controller.MainMenuController controller;
    public MainMenuController() {
        controller = new controller.MainMenuController();
    }
    public void start(MouseEvent mouseEvent) throws Exception {
        new GameMenu().start(MainMenu.stage);
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
        Database.saveUsers();
        new LoginMenu().start(MainMenu.stage);
    }

    public void scoreboard(MouseEvent mouseEvent) throws Exception{
        new ScoreBoardMenu().start(MainMenu.stage);
    }

    public void setting(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(MainMenu.stage);
    }

    public static String usernameCurrentUser() {
        return controller.getUsername();
    }

    public void twoPlayerGame(MouseEvent mouseEvent) throws Exception {
        new GameMenuTwo().start(MainMenu.stage);
    }
}
