package view.SignupMenu;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import view.LoginMenu.LoginMenu;

public class SignupMenuController {
    public controller.SignupMenuController controller;
    public TextField username;
    public TextField password;
    public TextField email;
    public SignupMenuController() {
        this.controller = new controller.SignupMenuController();
    }

    public void submit(MouseEvent mouseEvent) {
//        Text text = new Text(360, 50,controller.signup(username.getText(), password.getText(), email.getText()));
        switch (controller.signup(username.getText(), password.getText(), email.getText())) {
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
            case EMPTY_EMAIL : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Bland field");
                alert.setContentText("Please enter your email");
                alert.showAndWait();
                break;
            }
            case SHORT_PASSWORD : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Signup failed");
                alert.setContentText("The password you entered is too short");
                alert.showAndWait();
                break;
            }
            case DOES_NOT_CONTAIN_LOWERCASE_LETTER : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Signup failed");
                alert.setContentText("The password you entered does not have lower case");
                alert.showAndWait();
                break;
            }
            case DOES_NOT_CONTAIN_UPPERCASE_LETTER : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Signup failed");
                alert.setContentText("The password you entered does not have upper case");
                alert.showAndWait();
                break;
            }
            case PASSWORD_DOES_NOT_CONTAIN_NUMBER : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Signup failed");
                alert.setContentText("The password you entered does not have number");
                alert.showAndWait();
                break;
            }
            case USERNAME_EXISTS : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Signup failed");
                alert.setContentText("The user name already exists");
                alert.showAndWait();
                break;
            }
            case INVAILD_EMAIL : {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Signup failed");
                alert.setContentText("The email you entered does not have valid form");
                alert.showAndWait();
                break;
            }
            case SUCCESS : {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Logged in");
                alert.setContentText("Your account created successfully");
                alert.showAndWait();
                break;
            }
        }
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
        email.setText("");
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }
}
