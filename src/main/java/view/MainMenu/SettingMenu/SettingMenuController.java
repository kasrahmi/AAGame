package view.MainMenu.SettingMenu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import view.MainMenu.MainMenu;

public class SettingMenuController {

    public static controller.SettingMenuController controller = new controller.SettingMenuController();
    public static CheckBox easy;
    public static CheckBox medium;
    public static CheckBox hard;

    public static void checkEasy(ActionEvent actionEvent) {
        if (easy.isSelected()) {
            medium.setSelected(false);
            hard.setSelected(false);
            controller.setLevel(1);
        }
    }
    public static void checkMedium(ActionEvent actionEvent) {
        if (medium.isSelected()) {
            easy.setSelected(false);
            hard.setSelected(false);
            controller.setLevel(2);
        }
    }
    public static void checkHard(ActionEvent actionEvent) {
        if (hard.isSelected()) {
            easy.setSelected(false);
            medium.setSelected(false);
            controller.setLevel(3);
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        if (!easy.isSelected() && !medium.isSelected() && !hard.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Back without choosing level");
            alert.setContentText("You can not go back to main menu without choosing level");
            alert.showAndWait();
        } else {
            new MainMenu().start(SettingMenu.stage);
        }
    }
}
