package view.MainMenu.ScoreBoardMenu;

import view.MainMenu.MainMenu;

public class ScoreBoardMenuController {
    public static controller.ScoreBoardController controller;
    public ScoreBoardMenuController() {
        controller = new controller.ScoreBoardController();
    }
    public static String getScoreBoard(int i) {
        return controller.getScoreBoard(i);
    }
    public void back() throws Exception {
        new MainMenu().start(ScoreBoardMenu.stage);
    }
}
