package model;

public enum Difficulty {
    EASY(1.2, 4500, 7, "easy", 1, 1.2),
    MEDIUM(1.5, 3000, 5, "medium", 2, 1.6),
    HARD(1.8, 1500, 3, "hard", 3, 2.0);
    final double windSpeed;
    final double rotationTime;
    final double freezeTime;
    final String difficulty;
    final int levelDifficulty;
    final double rotateAngle;
    Difficulty(double windSpeed, double rotationTime, double freezeTime, String difficulty,
               int levelDifficulty, double rotateAngle) {
        this.windSpeed = windSpeed;
        this.rotationTime = rotationTime;
        this.freezeTime = freezeTime;
        this.difficulty = difficulty;
        this.levelDifficulty = levelDifficulty;
        this.rotateAngle = rotateAngle;
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

    public int getLevelDifficulty() {
        return levelDifficulty;
    }

    public double getRotateAngle() {
        return rotateAngle;
    }

    public static Difficulty getDifficulty(String difficulty) {
            for (Difficulty difficulty1 : Difficulty.values()) {
                if(difficulty1.difficulty.equals(difficulty)) return difficulty1;
            }
            return null;
        }
}
