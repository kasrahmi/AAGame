package model;

public enum Difficulty {
    EASY(1.2, 4500, 7, "easy"),
    MEDIUM(1.5, 3000, 5, "medium"),
    HARD(1.8, 1500, 3, "hard");
    final double windSpeed;
    final double rotationTime;
    final double freezeTime;
    final String difficulty;
    Difficulty(double windSpeed, double rotationTime, double freezeTime, String difficulty) {
        this.windSpeed = windSpeed;
        this.rotationTime = rotationTime;
        this.freezeTime = freezeTime;
        this.difficulty = difficulty;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getRotationTime() {
        return rotationTime;
    }

    public double getFreezeTime() {
        return freezeTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

public static Difficulty getDifficulty(String difficulty) {
        for (Difficulty difficulty1 : Difficulty.values()) {
            if(difficulty1.difficulty.equals(difficulty)) return difficulty1;
        }
        return null;
    }
}
