package model;

public class User {
    private String username;
    private String password;
    private String email;
    private int highScore;
    private int scoreEasy;
    private int scoreMedium;
    private int scoreHard;
    private int level;
    private int time;
    private String pathAvatar;
    public User(String username, String password, String email, String pathAvatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.pathAvatar = pathAvatar;
        this.highScore = 0;
        this.scoreEasy = 0;
        this.scoreMedium = 0;
        this.scoreHard = 0;
        this.level = 1;
        this.time = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        this.level++;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPathAvatar() {
        return pathAvatar;
    }

    public void setPathAvatar(String pathAvatar) {
        this.pathAvatar = pathAvatar;
    }

    public int getScoreEasy() {
        return scoreEasy;
    }

    public void setScoreEasy(int scoreEasy) {
        if (this.scoreEasy < scoreEasy)
            this.scoreEasy = scoreEasy;
    }

    public int getScoreMedium() {
        return scoreMedium;
    }

    public void setScoreMedium(int scoreMedium) {
        if (this.scoreMedium < scoreMedium)
            this.scoreMedium = scoreMedium;
    }

    public int getScoreHard() {
        return scoreHard;
    }

    public void setScoreHard(int scoreHard) {
        if (this.scoreHard < scoreHard)
            this.scoreHard = scoreHard;
    }

    public void setScore(int score) {
        if (score > this.highScore) setHighScore(score);
        if (CurrentGame.getDifficulty().difficulty.equals("easy")) setScoreEasy(score);
        else if (CurrentGame.getDifficulty().difficulty.equals("medium")) setScoreMedium(score);
        else setScoreHard(score);
    }

    @Override
    public String toString() {
        return getUsername() + "\tscore : " + getHighScore() + "\ttime : " + getTime();
    }
}
