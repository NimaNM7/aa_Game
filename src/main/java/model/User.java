package model;

import javafx.scene.image.Image;
import utils.GraphicUtils;

public class User {
    private String username;
    private String password;
    private final boolean isGuest;
    private int score;
    private String avatarPath;

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
}
