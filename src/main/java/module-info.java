module AAGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.swing;

    exports model;
    opens model to com.google.gson;
    exports view.MainMenu;
    exports view.MainMenu.ProfileMenu;
    opens view.MainMenu.ProfileMenu to javafx.fxml;
    exports view.MainMenu.ScoreBoardMenu;
    opens view.MainMenu.ScoreBoardMenu to javafx.fxml;
    exports view.MainMenu.SettingMenu;
    opens view.MainMenu.SettingMenu to javafx.fxml;
    exports view.LoginMenu;
    opens view.LoginMenu to javafx.fxml;
    exports view.SignupMenu;
    opens view.SignupMenu to javafx.fxml;
}