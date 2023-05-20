package view.mainMenu.settingMenu;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import view.mainMenu.MainMenu;

public class SettingMenuController {

    public static controller.SettingMenuController controller = new controller.SettingMenuController();
    public CheckBox easy;
    public CheckBox medium;
    public CheckBox hard;
//    public ArrayList<CheckBox> checkBoxes = new ArrayList<>(Arrays.asList(easy, medium, hard));
//    public SettingMenuController() {
//        checkBoxes = new ArrayList<>();
//        checkBoxes.add(easy);
//        checkBoxes.add(medium);
//        checkBoxes.add(hard);
//    }
//    public void check(ActionEvent actionEvent) {
//        for (CheckBox checkBox : checkBoxes) {
//            if (!checkBox.getId().equals(checkBox.getId())) {
//                System.out.println(checkBox.getId());
//                checkBox.setSelected(false);
//                System.out.println(checkBox.isSelected());
//            } else controller.setLevel(Integer.parseInt(checkBox.getText()));
//        }
//    }

    public void checkEasy(ActionEvent actionEvent) {
        if (easy.isSelected()) {
            medium.setSelected(false);
            hard.setSelected(false);
            controller.setLevel(1);
        }
    }
    public void checkMedium(ActionEvent actionEvent) {
        if (medium.isSelected()) {
            easy.setSelected(false);
            hard.setSelected(false);
            controller.setLevel(2);
        }
    }
    public void checkHard(ActionEvent actionEvent) {
        if (hard.isSelected()) {
            easy.setSelected(false);
            medium.setSelected(false);
            controller.setLevel(3);
        }
    }

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