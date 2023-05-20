package view;

import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.MainCircle;
import model.SmallCircle;

public class ShootingAnimation extends Transition {
    private Pane pane;
    private SmallCircle smallCircle;
    private MainCircle mainCircle;

    public ShootingAnimation(Pane pane, SmallCircle smallCircle,MainCircle mainCircle) {
        this.pane = pane;
        this.smallCircle = smallCircle;
        this.mainCircle = mainCircle;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        double y = smallCircle.getCenterY() - 15;

        if (smallCircle.getDistanceFromCenter() < 150) {
            smallCircle.placeOnCircle();
            stop();
        }

        smallCircle.setCenterY(y);
    }
}
