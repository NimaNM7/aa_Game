package model;

public enum DifficultyLevel {
    EASY(3000,1,7),
    MEDIUM(2000,2,5),
    HARD(1000,3,3),
    ;
    //TODO add details here if its better
    private final int timeOfCycle;
    private final int speedOfWind;
    private final int fresseTime;

    DifficultyLevel(int timeOfCycle, int speedOfWind, int fresseTime) {
        this.timeOfCycle = timeOfCycle;
        this.speedOfWind = speedOfWind;
        this.fresseTime = fresseTime;
    }

    public int getTimeOfCycle() {
        return timeOfCycle;
    }

    public int getSpeedOfWind() {
        return speedOfWind;
    }

    public int getFresseTime() {
        return fresseTime;
    }
}
