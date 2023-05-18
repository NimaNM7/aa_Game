package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Avatar extends Rectangle {
    private Image image;
    private String path;

    public Avatar(Image image) {
        this.image = image;
        this.setHeight(80);
        this.setWidth(80);
        this.setFill(new ImagePattern(image));
    }

    public void setXAndY(double x, double y) {
        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
