package controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.MainCircle;
import model.SmallCircle;
import view.Game;
import view.RotateAnimation;
import view.ShootingAnimation;

import java.util.ArrayList;

public class GameController {
    private static Game game = new Game();
    public static int counter = 0;
    private static ArrayList<SmallCircle> ballsOnCircle = new ArrayList<>();
    private static RotateAnimation rotateAnimation;

    public static RotateAnimation getRotateAnimation() {
        return rotateAnimation;
    }

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
        ShootingAnimation animation = new ShootingAnimation(pane,smallCircle);
        animation.play();
    }

    public static void ballRotation(Pane pane, SmallCircle smallCircle) {
        Rotate rotate = new Rotate((-1) * rotateAnimation.getRotate().getAngle(), 250,350);
        smallCircle.getTransforms().addAll(rotateAnimation.getRotate(),rotate);
        ballsOnCircle.add(smallCircle);
        Line line;
        if (smallCircle.getCenterX() == 250)
            line = new Line(smallCircle.getCenterX(), smallCircle.getCenterY() , 250, 420);
        else
            line = new Line(smallCircle.getCenterX(), smallCircle.getCenterY() , 250, 350);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(5);
        pane.getChildren().add(line);
        line.getTransforms().addAll(rotateAnimation.getRotate(),rotate);
    }


    public static void GameOver() {
        System.out.println("game is over");
    }

    public static void changeRotate(Pane pane) {

    }

    public static ProgressBar findProgressBarInPane(Pane pane) {
        for (Node child : pane.getChildren()) {
            if (child instanceof HBox) {
                for (Node node : ((HBox) child).getChildren()) {
                    if (node instanceof ProgressBar)
                        return (ProgressBar) node;
                }
            }
        }
        return null;
    }

    public static Label findBallsLabelInPane(Pane pane) {
        for (Node child : pane.getChildren()) {
            if (child instanceof HBox) {
                for (Node node : ((HBox) child).getChildren()) {
                    if (node instanceof Label)
                        return (Label) node;
                }
            }
        }
        return null;
    }

    public static void successfulShot(Pane pane, SmallCircle smallCircle) {
        getBallsOnCircle().add(smallCircle);
        ProgressBar progressBar = GameController.findProgressBarInPane(pane);
        if (progressBar != null) {
            progressBar.setProgress(progressBar.getProgress() + 0.1);
        }
        Label label = GameController.findBallsLabelInPane(pane);
        if (label != null) {
            label.setText("number of balls left: " + game.getCurrentCountOfBalls());
        }
    }


    public static void freeze(Pane pane) {
        ProgressBar progressBar = GameController.findProgressBarInPane(pane);
        if (progressBar == null || progressBar.getProgress() <= 0.91) {
            return;
        }
        progressBar.setProgress(0);
        //TODO real freeze
        changeRotate(pane);
    }

    public static void setRotateAnimation(RotateAnimation rotateAnimation) {
        GameController.rotateAnimation = rotateAnimation;
    }
}
