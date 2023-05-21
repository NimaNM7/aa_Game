package view;

import controller.GameController;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.SmallCircle;

import java.util.ArrayList;

public class RotateAnimation extends Transition {
    private ArrayList<SmallCircle> rotatingBalls;
    private Rotate rotate;
    private double amount;

    public RotateAnimation(ArrayList<SmallCircle> rotatingBalls) {
        rotate = new Rotate(0,250,350);
        this.rotatingBalls = rotatingBalls;
        for (SmallCircle ball : rotatingBalls) {
            ball.getTransforms().add(rotate);
        }
        this.setCycleDuration(Duration.millis(20000));
        this.setCycleCount(-1);
        this.interpolatorProperty().set(Interpolator.LINEAR);
    }

    public void addBallToCircle(SmallCircle smallCircle) {
        smallCircle.getTransforms().add(rotate);
        rotatingBalls.add(smallCircle);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public  Rotate getRotate() {
        return rotate;
    }

    @Override
    protected void interpolate(double frac) {
        rotate.setAngle(rotate.getAngle() + amount);
    }


}
