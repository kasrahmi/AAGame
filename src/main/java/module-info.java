module AAGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.swing;
    requires javafx.media;

    exports model;
    opens model to com.google.gson;
    exports view.mainMenu;
    opens view.mainMenu to javafx.fxml;
    exports view.mainMenu.profileMenu;
    opens view.mainMenu.profileMenu to javafx.fxml;
    exports view.mainMenu.scoreBoardMenu;
    opens view.mainMenu.scoreBoardMenu to javafx.fxml;
    exports view.mainMenu.settingMenu;
    opens view.mainMenu.settingMenu to javafx.fxml;
    exports view.loginMenu;
    opens view.loginMenu to javafx.fxml;
    exports view.signupMenu;
    opens view.signupMenu to javafx.fxml;
    exports view.gameMenu;
    opens view.gameMenu to javafx.fxml;
}