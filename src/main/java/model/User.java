package model;

import javafx.scene.image.Image;
import utils.GraphicUtils;
import view.Game;

public class User {
    private String username;
    private String password;
    private final boolean isGuest;
    private int score;
    private String avatarPath;
    private Game currentGame;
    private int totalTime;
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;
    private int preferredCountOfBalls = 20;
    private boolean isMutePreferred = false;
    private int numberOfMapPreffered = 1;

    public User(String username, String password, boolean isGuest) {
        this.username = username;
        this.password = password;
        this.isGuest = isGuest;
        this.score = 0;
        this.avatarPath = "/images/defaultAvatar.jpg";
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

    public boolean isGuest() {
        return isGuest;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Avatar getAvatar() {
        return GraphicUtils.getAvatarWithAddress(avatarPath);
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getPreferredCountOfBalls() {
        return preferredCountOfBalls;
    }

    public void setPreferredCountOfBalls(int preferedCountOfBalls) {
        this.preferredCountOfBalls = preferedCountOfBalls;
    }

    public boolean isMutePreferred() {
        return isMutePreferred;
    }

    public void setMutePreferred(boolean mutePreferred) {
        isMutePreferred = mutePreferred;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getNumberOfMapPreffered() {
        return numberOfMapPreffered;
    }

    public void setNumberOfMapPreffered(int numberOfMapPreffered) {
        this.numberOfMapPreffered = numberOfMapPreffered;
    }
}
