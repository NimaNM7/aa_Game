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

    public ShootingAnimation(SmallCircle smallCircle) {
        this.pane = GameController.getGame().gamePane;
        System.out.println("ALL4 " + smallCircle.getCenterY());
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
                (!smallCircle.isFromSecondPlayer() && y < 0) || (smallCircle.isFromSecondPlayer() && y > 750)) {
            System.out.println(smallCircle.isFromSecondPlayer());

            try {
                GameController.GameOverLost();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.stop();
            frac = 1;
        } else if (smallCircle.getDistanceFromCenter() < 180) {
            smallCircle.placeOnCircle();
            GameController.ballRotation(smallCircle);
            if (GameController.isThereCrash(smallCircle)) {
                try {
                    GameController.GameOverLost();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                GameController.successfulShot(smallCircle);
            }
            this.stop();
            frac = 1;
        } else {
            smallCircle.setCenterY(y);
            smallCircle.setCenterX(x);
        }
    }
}
