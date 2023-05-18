package view.LoginMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Database;
import view.MainMenu.MainMenu;
import view.SignupMenu.SignupMenu;

public class LoginMenuController {
    public TextField username;
    public TextField password;
    public controller.LoginMenuController controller;
    public LoginMenuController() {
        controller = new controller.LoginMenuController();
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        switch (controller.login(username.getText(), password.getText())) {
            case SUCCESS : {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Logged in");
                alert.setContentText("You successfully logged in");
                alert.showAndWait();
                new MainMenu().start(LoginMenu.stage);
                break;
            }
            case WRONG_PASSWORD : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong password");
                alert.setContentText("The password you entered is wrong");
                alert.showAndWait();
                break;
            }
            case USER_DOES_NOT_EXIST : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("User does not exist");
                alert.setContentText("The username you entered does not exist");
                alert.showAndWait();
                break;
            }
            case EMPTY_PASSWORD : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Bland field");
                alert.setContentText("Please enter your password");
                alert.showAndWait();
                break;
            }
            case EMPTY_USERNAME : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Bland field");
                alert.setContentText("Please enter your username");
                alert.showAndWait();
                break;
            }
        }
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }

    public void enterSignupMenu(MouseEvent mouseEvent) throws Exception {
        new SignupMenu().start(LoginMenu.stage);
    }

    public void enterGuestMode(MouseEvent mouseEvent) throws Exception {
        controller.loginAsGuest();
        new MainMenu().start(LoginMenu.stage);
    }
}