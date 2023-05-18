package model;

public class User {
    private String username;
    private String password;
    private String email;
    private int score;
    private int level;
    private int time;
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.score = 0;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    @Override
    public String toString() {
        return getUsername() + " score : " + getScore() + " time : " + getTime();
    }
}
