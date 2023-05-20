package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SmallCircle extends Circle {
    private double currentAngle;

    public SmallCircle() {
        super(250,550,10);
//        this.setFill(new ImagePattern(new Image(MainCircle.class.getResource("/images/defaultAvatar.jpg").toExternalForm())));
        this.setFill(Color.BLACK);
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }

    public double getX() {
        return 250 + 150 * Math.cos(currentAngle);
    }

    public double getY() {
        return 200 + 150 * Math.sin(currentAngle);
    }

    public double getDistanceFromCenter() {
        return Math.sqrt(Math.pow(250 - this.getCenterX(),2) + Math.pow(200 - this.getCenterY(),2));
    }

    public void placeOnCircle() {
        setCenterY(Math.sqrt(22500 - Math.pow(this.getCenterX(),2)));
    }
}
