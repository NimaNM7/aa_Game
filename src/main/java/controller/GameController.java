package controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.MainCircle;
import model.SmallCircle;
import view.ShootingAnimation;

public class GameController {
    public static void shoot(Pane pane, MainCircle mainCircle) {
        SmallCircle smallCircle = new SmallCircle();
        pane.getChildren().add(smallCircle);
        ShootingAnimation animation = new ShootingAnimation(pane,smallCircle,mainCircle);
        animation.play();
    }

    public static void rotateBall(Pane gamePane, SmallCircle smallCircle) {
        Rotate rotate = new Rotate(0,250,200);
        smallCircle.getTransforms().add(rotate);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(4), new KeyValue(rotate.angleProperty(), 360)));
        timeline.cycleCountProperty().set(-1);
        timeline.play();
    }
}
