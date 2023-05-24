package model;

import javafx.scene.image.Image;
import utils.GraphicUtils;
import view.Game;

public class User {
    private String username;
    private String password;
    private final boolean isGuest;
    private int highscore;
    private String avatarPath;
    private int totalTimeInHighscore;
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;
    private int preferredCountOfBalls = 20;
    private boolean isMutePreferred = false;
    private int numberOfMapPreferred = 1;
    private String preferredShootingButton = "Space";
    private String preferredFreezeButton = "Tab";
    private String preferredSecondPlayerShootingButton = "Enter";

    public User(String username, String password, boolean isGuest) {
        this.username = username;
        this.password = password;
        this.isGuest = isGuest;
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

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Avatar getAvatar() {
        return GraphicUtils.getAvatarWithAddress(avatarPath);
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

    public int getNumberOfMapPreferred() {
        return numberOfMapPreferred;
    }

    public void setNumberOfMapPreferred(int numberOfMapPreferred) {
        this.numberOfMapPreferred = numberOfMapPreferred;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getTotalTimeInHighscore() {
        return totalTimeInHighscore;
    }

    public void setTotalTimeInHighscore(int totalTimeInHighscore) {
        this.totalTimeInHighscore = totalTimeInHighscore;
    }

    public String getPreferredShootingButton() {
        return preferredShootingButton;
    }

    public void setPreferredShootingButton(String preferredShootingButton) {
        this.preferredShootingButton = preferredShootingButton;
    }

    public String getPreferredFreezeButton() {
        return preferredFreezeButton;
    }

    public void setPreferredFreezeButton(String preferredFreezeButton) {
        this.preferredFreezeButton = preferredFreezeButton;
    }

    public String getPreferredSecondPlayerShootingButton() {
        return preferredSecondPlayerShootingButton;
    }

    public void setPreferredSecondPlayerShootingButton(String preferredSecondPlayerShootingButton) {
        this.preferredSecondPlayerShootingButton = preferredSecondPlayerShootingButton;
    }
}
