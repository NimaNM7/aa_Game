package model;

public enum DifficultyLevel {
    EASY(3000,1,7),
    MEDIUM(2300,2,5),
    HARD(1500,3,3),
    ;
    //TODO add details here if its better
    private final int timeOfCycle;
    private final int speedOfWind;
    private final int freezeTime;

    DifficultyLevel(int timeOfCycle, int speedOfWind, int freezeTime) {
        this.timeOfCycle = timeOfCycle;
        this.speedOfWind = speedOfWind;
        this.freezeTime = freezeTime;
    }

    public int getTimeOfCycle() {
        return timeOfCycle;
    }

    public int getSpeedOfWind() {
        return speedOfWind;
    }

    public int getfreezeTime() {
        return freezeTime;
    }
}
