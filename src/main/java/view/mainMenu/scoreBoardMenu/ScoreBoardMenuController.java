package view.mainMenu.scoreBoardMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import model.Difficulty;
import view.mainMenu.MainMenu;

public class ScoreBoardMenuController {
    public static controller.ScoreBoardController controller;
    public CheckBox easy;
    public CheckBox medium;
    public CheckBox hard;
    public CheckBox highScore;

    public ScoreBoardMenuController() {
        controller = new controller.ScoreBoardController();
    }
    public static String getScoreBoard(int i) {
        return controller.getScoreBoard(i);
    }
    public void back() throws Exception {
        new MainMenu().start(ScoreBoardMenu.stage);
    }

    @FXML
    private void handleCheckboxAction(ActionEvent event) throws Exception {
        if (event.getSource() == easy) {
            if (easy.isSelected()) {
                medium.setSelected(false);
                hard.setSelected(false);
                highScore.setSelected(false);
            }
            controller.setDifficulty(Difficulty.EASY);
        } else if (event.getSource() == medium) {
            if (medium.isSelected()) {
                easy.setSelected(false);
                hard.setSelected(false);
                highScore.setSelected(false);
            }
            controller.setDifficulty(Difficulty.MEDIUM);
        } else if (event.getSource() == hard) {
            if (hard.isSelected()) {
                easy.setSelected(false);
                medium.setSelected(false);
                highScore.setSelected(false);
            }
            controller.setDifficulty(Difficulty.HARD);
        } else if (event.getSource() == highScore) {
            if (highScore.isSelected()) {
                easy.setSelected(false);
                medium.setSelected(false);
                hard.setSelected(false);
            }
            controller.setDifficulty(null);
        }
        new ScoreBoardMenu().start(ScoreBoardMenu.stage);
    }
}
