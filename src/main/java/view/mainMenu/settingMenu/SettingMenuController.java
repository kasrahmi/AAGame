package view.mainMenu.settingMenu;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import view.mainMenu.MainMenu;

public class SettingMenuController {

    public static controller.SettingMenuController controller = new controller.SettingMenuController();

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(SettingMenu.stage);
    }

    public void blackAndWhite(MouseEvent mouseEvent) throws Exception {
        controller.setDarkMode(!controller.isDarkMode());
        new SettingMenu().start(SettingMenu.stage);
    }

    public void changeLanguage(MouseEvent mouseEvent) {

    }

    public void muteSong(MouseEvent mouseEvent) throws Exception {
        controller.setMuteAndUnMute(!controller.isSongMute());
        new SettingMenu().start(SettingMenu.stage);
    }

    public void chooseMap(MouseEvent mouseEvent) {

    }

    public void changeNumberOfBalls(MouseEvent mouseEvent) throws Exception {
        new ChangeNumberOfBalls().start(SettingMenu.stage);
    }

    public void changeDifficulty(MouseEvent mouseEvent) throws Exception {
        new ChangeDifficulty().start(SettingMenu.stage);
    }

    public void changePlayingButton(MouseEvent actionEvent) {

    }
}