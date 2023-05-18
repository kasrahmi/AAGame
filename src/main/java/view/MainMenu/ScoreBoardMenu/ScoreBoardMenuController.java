package view.MainMenu.ScoreBoardMenu;

import view.MainMenu.MainMenu;

public class ScoreBoardMenuController {
    public static controller.ScoreBoardController controller;
    public ScoreBoardMenuController() {
        controller = new controller.ScoreBoardController();
    }
    public static String getScoreBoard() {
        return controller.getScoreBoard();
    }
    public void back() throws Exception {
        new MainMenu().start(ScoreBoardMenu.stage);
    }
}
