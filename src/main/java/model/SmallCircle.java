package model;

import controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import utils.Utils;

public class SmallCircle extends Circle {
    private boolean isFromSecondPlayer;
//    private static Color[] colors ={Color.BLACK,Color.GRAY,Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW,Color.PINK,Color.PURPLE};

    public SmallCircle() {
        super(250,720,10);
        setFill(Color.BLACK);
        isFromSecondPlayer = false;
    }

    public SmallCircle(boolean isFromSecondPlayer) {
        this();
        if (isFromSecondPlayer) {
            this.setCenterX(250);
            this.setCenterY(30);
            System.out.println("making a small circle which is for second player");
            this.setFill(Color.RED);
        }
    }

    public boolean isFromSecondPlayer() {
        return isFromSecondPlayer;
    }

    public void setFromSecondPlayer(boolean fromSecondPlayer) {
        isFromSecondPlayer = fromSecondPlayer;
        if (isFromSecondPlayer) {
            this.setCenterY(30);
            this.setFill(Color.RED);
        } else {
            this.setCenterY(720);
            this.setFill(Color.BLACK);
        }
    }

    public double getDistanceFromCenter() {
        return Math.sqrt(Math.pow(250 - this.getCenterX(),2) + Math.pow(350 - this.getCenterY(),2));
    }

    public void placeOnCircle() {
        if (isFromSecondPlayer) {
            setCenterY(-1 * Math.sqrt(32400 - Math.pow(this.getCenterX() - 250, 2)) + 350);
        }
        else
            setCenterY(Math.sqrt(32400 - Math.pow(this.getCenterX() - 250,2)) + 350);
    }
}
