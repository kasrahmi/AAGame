package controller;

import model.Database;
import model.User;

import java.util.ArrayList;

public class ScoreBoardController {
    public String getScoreBoard(int i) {
        ArrayList<User> users = orderScoreBoard();
        String output = "";
//        if (i == 0)
//            output += "The scoreBoard :\n\n\n";
        if (i < users.size())
            output += "\t" + (i + 1) + "." + users.get(i).toString() + "\n\n";
        return output;
    }

    public ArrayList<User> orderScoreBoard() {
        ArrayList<User> users = Database.getUsers();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (users.get(i).getScore() > users.get(j).getScore() ||
                    (users.get(i).getScore() == users.get(j).getScore() &&
                    users.get(i).getTime() < users.get(j).getScore())) {
                    User tmp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, tmp);
                }
            }
        }
        return users;
    }
}
