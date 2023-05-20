package controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.MainCircle;
import model.SmallCircle;
import view.Game;
import view.ShootingAnimation;

import java.util.ArrayList;

public class GameController {
    private static Game game = new Game();
    private static ArrayList<SmallCircle> ballsOnCircle = new ArrayList<>();

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameController.game = game;
    }

    public static ArrayList<SmallCircle> getBallsOnCircle() {
        return ballsOnCircle;
    }

    public static boolean isThereCrash(SmallCircle smallCircle) {
        for (SmallCircle circle : ballsOnCircle) {
            if (circle.getBoundsInParent().intersects(smallCircle.getBoundsInParent()))
                return true;
        }
        return false;
    }

    public static void shoot(Pane pane, MainCircle mainCircle) {
        SmallCircle smallCircle = new SmallCircle();
        pane.getChildren().add(smallCircle);
        ShootingAnimation animation = new ShootingAnimation(pane,smallCircle,mainCircle);
        animation.play();
    }

    public static void rotateBall(Pane pane, SmallCircle smallCircle) {
        Line line;
        if (smallCircle.getCenterX() == 250)
            line = new Line(smallCircle.getCenterX(), smallCircle.getCenterY() - 20 , 250, 420);
        else
            line = new Line(smallCircle.getCenterX(), smallCircle.getCenterY() , 250, 350);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(5);
        pane.getChildren().add(line);
        Rotate rotate = new Rotate(0,250,350);
        smallCircle.getTransforms().add(rotate);
        line.getTransforms().add(rotate);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(GameController.game.getDifficultyLevel().getTimeOfCycle()),
                        new KeyValue(rotate.angleProperty(), 360)));
        timeline.cycleCountProperty().set(-1);
        timeline.play();
    }

    public static void GameOver() {
        System.out.println("game is over");
    }
}
