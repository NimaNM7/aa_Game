package view;

import controller.GameController;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.MainCircle;
import model.SmallCircle;

import java.util.Timer;
import java.util.TimerTask;

public class ShootingAnimation extends Transition {
    private Pane pane;
    private SmallCircle smallCircle;

    public ShootingAnimation(Pane pane, SmallCircle smallCircle) {
        this.pane = pane;
        this.smallCircle = smallCircle;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        double y = smallCircle.getCenterY() - 15;
        if (smallCircle.getDistanceFromCenter() < 180) {
            smallCircle.placeOnCircle();
            GameController.ballRotation(pane,smallCircle);
            if (GameController.isThereCrash(smallCircle)) {
                try {
                    GameController.GameOverLost(pane);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                GameController.successfulShot(pane, smallCircle);
            }
            this.stop();
            frac = 1;
        } else {
            smallCircle.setCenterY(y);
            //TODO changing x for phase 4
        }
    }
}
