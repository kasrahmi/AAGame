package view.SignupMenu;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import view.LoginMenu.LoginMenu;

public class SignupMenuController {
    public controller.SignupMenuController controller;
    public TextField username;
    public TextField password;
    public TextField email;
    public Text message;

    public SignupMenuController() {
        this.controller = new controller.SignupMenuController();
    }

    public void submit(MouseEvent mouseEvent) {
        message.setText(controller.signup(username.getText(), password.getText(), email.getText()));
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
