package controller;

import model.Database;
import model.Difficulty;
import model.User;

import java.util.ArrayList;

public class ScoreBoardController {
    public static Difficulty difficulty = Difficulty.MEDIUM;
    public String getScoreBoard(int i) {

        String output = "";

        ArrayList<User> users = Database.getUsers();


        if (difficulty == null && i < users.size()) {
            users = orderScoreBoardHighScore();
            output += "\t" + (i + 1) + ".\t" + users.get(i).toString();
        }
        else if (i < users.size() && difficulty.equals(Difficulty.EASY)) {
            System.out.println(difficulty);
            users = orderScoreBoardEasy();
            output += "\t" + (i + 1) + ".\t" + users.get(i).getUsername() + "\tscore : "
                    + users.get(i).getScoreEasy() + "\ttime : " + users.get(i).getTime();
        }
        else if (i < users.size() && difficulty.equals(Difficulty.MEDIUM)) {
            System.out.println(difficulty);
            users = orderScoreBoardMedium();
            output += "\t" + (i + 1) + ".\t" + users.get(i).getUsername() + "\tscore : "
                    + users.get(i).getScoreMedium() + "\ttime : " + users.get(i).getTime();
        }
        else if (i < users.size() && difficulty.equals(Difficulty.HARD)) {
            System.out.println(difficulty);
            users = orderScoreBoardHard();
            output += "\t" + (i + 1) + ".\t" + users.get(i).getUsername() + "\tscore : "
                    + users.get(i).getScoreHard() + "\ttime : " + users.get(i).getTime();
        }


//        if (i == 0)
//            output += "The scoreBoard :\n\n\n";
//        if (i < users.size())
//            output += "\t" + (i + 1) + "." + users.get(i).toString() + "\n\n";
        return output;
    }

    private ArrayList<User> orderScoreBoardHard() {
        ArrayList<User> users = Database.getUsers();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (users.get(i).getScoreHard() > users.get(j).getScoreHard() ||
                        (users.get(i).getScoreHard() == users.get(j).getScoreHard() &&
                                users.get(i).getTime() < users.get(j).getTime())) {
                    User tmp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, tmp);
                }
            }
        }
        return users;
    }

    private ArrayList<User> orderScoreBoardMedium() {
        ArrayList<User> users = Database.getUsers();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (users.get(i).getScoreMedium() > users.get(j).getScoreMedium() ||
                        (users.get(i).getScoreMedium() == users.get(j).getScoreMedium() &&
                                users.get(i).getTime() < users.get(j).getTime())) {
                    User tmp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, tmp);
                }
            }
        }
        return users;
    }

    private ArrayList<User> orderScoreBoardEasy() {
        ArrayList<User> users = Database.getUsers();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (users.get(i).getScoreEasy() > users.get(j).getScoreEasy() ||
                        (users.get(i).getScoreEasy() == users.get(j).getScoreEasy() &&
                                users.get(i).getTime() < users.get(j).getTime())) {
                    User tmp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, tmp);
                }
            }
        }
        return users;
    }

    public ArrayList<User> orderScoreBoardHighScore() {
        ArrayList<User> users = Database.getUsers();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (users.get(i).getHighScore() > users.get(j).getHighScore() ||
                    (users.get(i).getHighScore() == users.get(j).getHighScore() &&
                    users.get(i).getTime() < users.get(j).getTime())) {
                    User tmp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, tmp);
                }
            }
        }
        return users;
    }

    public void setDifficulty(Difficulty difficulty) {
        ScoreBoardController.difficulty = difficulty;
    }
}
