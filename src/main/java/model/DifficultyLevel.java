package model;

public enum DifficultyLevel {
    EASY(1.5,1.2,7),
    MEDIUM(2,1.5,5),
    HARD(3,1.8,3),
    ;
    //TODO add details here if its better
    private final double rotateSpeed;
    private final double speedOfWind;
    private final int freezeTime;

    DifficultyLevel(double rotateSpeed, double speedOfWind, int freezeTime) {
        this.rotateSpeed = rotateSpeed;
        this.speedOfWind = speedOfWind;
        this.freezeTime = freezeTime;
    }

    public double getRotateSpeed() {
        return rotateSpeed;
    }

    public double getSpeedOfWind() {
        return speedOfWind;
    }

    public int getFreezeTime() {
        return freezeTime;
    }
}
