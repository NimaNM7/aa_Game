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
        double x = 250 ,y;
        if (GameController.getGame() != null)
            x = smallCircle.getCenterX() + GameController.getGame().getWindDegree();

        if (smallCircle.isFromSecondPlayer())
            y = smallCircle.getCenterY() + 15;
        else
            y = smallCircle.getCenterY() - 15;

        if ( x < 20 || x > 480 ||
                (!smallCircle.isFromSecondPlayer() && y > 750) || (smallCircle.isFromSecondPlayer() && y < 0)) {
            System.out.println(x);
            System.out.println(y);
            System.out.println(smallCircle.isFromSecondPlayer());
            System.out.println(smallCircle.getCenterX());
            System.out.println(smallCircle.getCenterY());
            try {
                GameController.GameOverLost(pane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (smallCircle.getDistanceFromCenter() < 180) {
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
            smallCircle.setCenterX(x);
        }
    }
}
