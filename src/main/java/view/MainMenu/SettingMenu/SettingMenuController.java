package view.MainMenu.SettingMenu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import view.MainMenu.MainMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void blackAndWhite(MouseEvent mouseEvent) {
        controller.setDarkMode(!controller.isDarkMode());
    }

    public void changeLanguage(MouseEvent mouseEvent) {

    }

    public void muteSong(MouseEvent mouseEvent) {

    }

    public void chooseMap(MouseEvent mouseEvent) {

    }

    public void changeNumberOfBalls(MouseEvent mouseEvent) throws Exception {
        new ChangeNumberOfBalls().start(SettingMenu.stage);
    }

    public void changeDifficulty(MouseEvent mouseEvent) throws Exception {
        new ChangeDifficulty().start(SettingMenu.stage);
    }
}