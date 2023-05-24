package controller;

import controller.database.Database;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import model.MainCircle;
import model.SmallCircle;
import utils.Utils;
import view.*;

import java.util.*;

public class GameController {
    private static boolean isGameMultiPlayer;
    private static Game game;
    private static ArrayList<SmallCircle> ballsOnCircle = new ArrayList<>();
    private static ArrayList<Line> linesOnCircle = new ArrayList<>();
    private static RotateAnimation rotateAnimation;
    private static int currentPhase = 0;
    private static MediaPlayer mediaPlayer;
    private static MediaPlayer dingPlayer;

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        GameController.mediaPlayer = mediaPlayer;
    }

    public static void setDingPlayer(MediaPlayer dingPlayer) {
        GameController.dingPlayer = dingPlayer;
    }

    public static MediaPlayer getDingPlayer() {
        return dingPlayer;
    }

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

    public static ArrayList<Line> getLinesOnCircle() {
        return linesOnCircle;
    }

    public static int getCurrentPhase() {
        return currentPhase;
    }

    public static void setCurrentPhase(int currentPhase) {
        GameController.currentPhase = currentPhase;
    }

    public static void setGameMultiPlayer(boolean gameMultiPlayer) {
        isGameMultiPlayer = gameMultiPlayer;
    }

    public static boolean isIsGameMultiPlayer() {
        return isGameMultiPlayer;
    }

    public static boolean isThereCrash(SmallCircle smallCircle) {
        for (SmallCircle circle : ballsOnCircle) {
            if (circle.getBoundsInParent().intersects(smallCircle.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public static void shoot(Pane pane, SmallCircle smallCircle) {
        ShootingAnimation animation = new ShootingAnimation(pane,smallCircle);
        animation.play();
        System.out.println("now we have balls: " + game.getCurrentCountOfBalls());
        if (game.getCurrentCountOfBalls() > 0)
            game.makeSmallCircle(pane,smallCircle.isFromSecondPlayer());
    }

    public static void ballRotation(Pane pane, SmallCircle smallCircle) {
        if (rotateAnimation == null) return;
        Rotate rotate = new Rotate((-1) * rotateAnimation.getRotate().getAngle(), 250,350);
        smallCircle.getTransforms().addAll(rotateAnimation.getRotate(),rotate);

        Line line;
        if (smallCircle.getCenterX() == 250)
            line = new Line(smallCircle.getCenterX(), smallCircle.getCenterY() , 250, 350);
        else
            line = new Line(smallCircle.getCenterX(), smallCircle.getCenterY() , 250, 350);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(5);
        pane.getChildren().add(line);
        linesOnCircle.add(line);
        smallCircle.setVisible(ballsOnCircle.get(0).isVisible());
        line.setVisible(ballsOnCircle.get(0).isVisible());
        line.getTransforms().addAll(rotateAnimation.getRotate(),rotate);
    }

    public static ProgressBar findProgressBarInPane(Pane pane, String id) {
        for (Node child : pane.getChildren()) {
            if (child instanceof HBox) {
                for (Node node : ((HBox) child).getChildren()) {
                    if (node instanceof ProgressBar && node.getId().equals(id))
                        return (ProgressBar) node;
                }
            }
        }
        return null;
    }

    public static Label findLabelInPane(Pane pane, String id) {
        for (Node child : pane.getChildren()) {
            if (child instanceof HBox) {
                for (Node node : ((HBox) child).getChildren()) {
                    if (node instanceof Label && node.getId().equals(id))
                        return (Label) node;
                }
            }
        }
        return null;
    }

    public static Label findWindLabelInPane(Pane pane) {
        for (Node child : pane.getChildren()) {
            if (child instanceof Label && child.getId().equals("windLabel"))
                return (Label) child;
        }
        return null;
    }

    public static void successfulShot(Pane pane, SmallCircle smallCircle) {
        if (game == null) return;
        ballsOnCircle.add(smallCircle);
        game.setScore(game.getScore() + (int) game.getDifficultyLevel().getRotateSpeed() * currentPhase);
        if (((game.getCurrentCountOfBalls() % (game.getCountOfBalls()/4) == 0
                || game.getCurrentCountOfBalls() == 0) && GameController.getBallsOnCircle().size() > 6)) {
            game.goToNextPhase(pane);
            if (currentPhase == 0)
                return;
        }
        dingPlayer.stop();
        dingPlayer.play();
        ProgressBar ballsForFreeze = GameController.findProgressBarInPane(pane,"ballsForFreeze");
        if (ballsForFreeze != null) {
            ballsForFreeze.setProgress(ballsForFreeze.getProgress() + 0.1);
        }
        ProgressBar numberOfBallsLeft = GameController.findProgressBarInPane(pane,"numberOfBallsLeft");
        if (numberOfBallsLeft != null) {
            numberOfBallsLeft.setProgress(numberOfBallsLeft.getProgress() - ((float) 1/game.getCountOfBalls()));
            if (numberOfBallsLeft.getProgress() < 0.3) numberOfBallsLeft.setStyle("-fx-accent: #00FF00; -fx-background-color: #FFFFFF;");
            else if (numberOfBallsLeft.getProgress() < 0.7) numberOfBallsLeft.setStyle("-fx-accent: #FFFF00; -fx-background-color: #FFFFFF;");
        }
        Label score = GameController.findLabelInPane(pane,"score");
        if (score != null) {
            score.setText(" " + game.getScore());
        }
    }


    public static void freeze(Pane pane) {
        ProgressBar progressBar = GameController.findProgressBarInPane(pane,"ballsForFreeze");
        if (progressBar == null || progressBar.getProgress() <= 0.91) {
            return;
        }
        progressBar.setProgress(0);
        if (rotateAnimation.getAmount() > 0)
            rotateAnimation.setAmount(0.5);
        else if (rotateAnimation.getAmount() < 0)
            rotateAnimation.setAmount(-0.5);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (rotateAnimation != null)
                    rotateAnimation.setAmount(2);
            }
        }, 1000L * game.getPlayer().getDifficultyLevel().getFreezeTime());
    }

    public static void setRotateAnimation(RotateAnimation rotateAnimation) {
        GameController.rotateAnimation = rotateAnimation;
    }

    public static void reset() {
        rotateAnimation = null;
        ballsOnCircle.clear();
        currentPhase = 0;
        game = null;
        mediaPlayer.stop();
    }

    public static void GameOverWin(Pane pane) {
        pane.setStyle("-fx-background-color: green;");
        Text youWon = new Text("You Won");
        youWon.setFill(Color.BLACK);
        youWon.setTranslateX(130);
        youWon.setTranslateY(100);
        youWon.setFont(new Font("Segoe Print",51));
        pane.getChildren().add(youWon);
        gameOver(pane);
    }

    public static void GameOverLost(Pane pane) throws Exception {
        pane.setStyle("-fx-background-color: red;");
        Text youLost = new Text("You Lost");
        youLost.setFill(Color.BLACK);
        youLost.setTranslateX(130);
        youLost.setTranslateY(100);
        youLost.setFont(new Font("Segoe Print",50));
        pane.getChildren().add(youLost);
        gameOver(pane);
    }

    public static void gameOver(Pane pane) {
        if (game == null) return;
        game.setTotalTime(Utils.getTimeFromLabel(Objects.requireNonNull(findLabelInPane(pane, "time"))));
        if (game.getScore() > game.getPlayer().getHighscore()) {
            game.getPlayer().setHighscore(game.getScore());
            game.getPlayer().setTotalTimeInHighscore(game.getTotalTime());
        } else if (game.getScore() == game.getPlayer().getHighscore() &&
        game.getTotalTime() < game.getPlayer().getTotalTimeInHighscore()) {
            game.getPlayer().setTotalTimeInHighscore(game.getTotalTime());
        }
        game.setScore(0);
        game.setTotalTime(0);
        if (rotateAnimation != null)
            rotateAnimation.stop();
        Database.saveUsers();
        reset();
        pane.requestFocus();
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    new MainMenu().start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void pause(Pane gamePane) {
        if (rotateAnimation.getAmount() == 0)
            rotateAnimation.setAmount(2);
        else
            rotateAnimation.setAmount(0);
    }

    public static boolean isCrashInCurrentBalls(ArrayList<SmallCircle> smallCircles) {
        for (SmallCircle circle1 : GameController.getBallsOnCircle()) {
            for (SmallCircle circle2 : GameController.getBallsOnCircle()) {
                if (!circle1.equals(circle2) && circle1.getBoundsInParent().intersects(circle2.getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }

}
