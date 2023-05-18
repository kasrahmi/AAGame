package controller;

import model.Database;
import model.User;

import java.util.ArrayList;

public class ScoreBoardController {
    public String getScoreBoard() {
        ArrayList<User> users = orderScoreBoard();
        String output = "";
        output += "The scoreBoard :\n\n\n";
        int counter = 0;
        for (User user : users) {

            output += ++counter + ". " + user.toString() + "\n\n\n";

            if (counter == 10) break;
        }
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
