package model;

public enum DifficultyLevel {
    EASY(1,1,7),
    MEDIUM(2,2,5),
    HARD(3,3,3),
    ;
    //TODO add details here if its better
    private final double rotateSpeed;
    private final int speedOfWind;
    private final int freezeTime;

    DifficultyLevel(double rotateSpeed, int speedOfWind, int freezeTime) {
        this.rotateSpeed = rotateSpeed;
        this.speedOfWind = speedOfWind;
        this.freezeTime = freezeTime;
    }

    public double getRotateSpeed() {
        return rotateSpeed;
    }

    public int getSpeedOfWind() {
        return speedOfWind;
    }

    public int getFreezeTime() {
        return freezeTime;
    }
}
