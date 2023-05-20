package controller;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import model.MainCircle;
import model.SmallCircle;
import view.ShootingAnimation;

public class GameController {
    public static void shoot(Pane pane, MainCircle mainCircle) {
        SmallCircle smallCircle = new SmallCircle();
        pane.getChildren().add(smallCircle);
        ShootingAnimation animation = new ShootingAnimation(pane,smallCircle,mainCircle);
        animation.play();
        System.out.println("animation play is the previous line");
    }
}
