package view;

import controller.GameController;
import controller.UserController;
import controller.database.Database;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.MainCircle;
import model.SmallCircle;
import model.User;
import utils.DefaultMaps;
import utils.GraphicUtils;

import java.net.URL;
import java.util.*;

public class Game extends Application {
    private User player;
    private final int countOfBalls;
    private  DifficultyLevel difficultyLevel;
    private boolean isMute;
    private final int numberOfMap;

    public Game() {
        this.player = UserController.getCurrentUser();
//        player.setCurrentGame(this);
        this.countOfBalls = player.getPreferredCountOfBalls();
        this.difficultyLevel = player.getDifficultyLevel();
        this.isMute = player.isMutePreferred();
        this.numberOfMap = player.getNumberOfMapPreffered();
        GameController.setGame(this);
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public int getCountOfBalls() {
        return countOfBalls;
    }

    public int getCurrentCountOfBalls() {
        return countOfBalls + 5 - GameController.getBallsOnCircle().size();
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    private void setPane(Pane gamePane) {
        HBox hbox = new HBox();
        hbox.setTranslateX(10);
        hbox.setTranslateY(10);
        hbox.setSpacing(20);

        ProgressBar ballsForFreeze = new ProgressBar();
        ballsForFreeze.setProgress(0.0);
        ballsForFreeze.setId("ballsForFreeze");
        ballsForFreeze.setStyle("-fx-accent: #0000FF; -fx-background-color: #FFFFFF;");

        ProgressBar numberOfBallsLeft = new ProgressBar();
        numberOfBallsLeft.setProgress(1.0);
        numberOfBallsLeft.setId("numberOfBallsLeft");
        numberOfBallsLeft.setStyle("-fx-accent: #FF0000; -fx-background-color: #FFFFFF;");

        Label score = new Label(" " + 0);
        score.setTextFill(Color.GREEN);
        score.setId("score");

        Button pauseButton = new Button("Pause");

        hbox.getChildren().addAll(List.of(pauseButton,numberOfBallsLeft,ballsForFreeze,score));
        gamePane.getChildren().add(hbox);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello please write my code");
            }
        });
        pauseButton.setFocusTraversable(false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Game.class.getResource("/FXML/game.fxml");
        Pane gamePane = FXMLLoader.load(url);

        setPane(gamePane);

        MainCircle mainCircle = new MainCircle();
        gamePane.getChildren().add(mainCircle);

        initializeGame(gamePane,GameController.getBallsOnCircle(),player.getDifficultyLevel().getRotateSpeed(),numberOfMap);

        SmallCircle smallCircle = makeSmallCircle(gamePane);

        primaryStage.setScene(new Scene(gamePane));
        primaryStage.setTitle("Game");
        smallCircle.requestFocus();
        primaryStage.show();
    }

    private void initializeGame(Pane gamePane, ArrayList<SmallCircle> ballsOnCircle, double amount, int numberOfMap) {
        RotateAnimation rotateAnimation = new RotateAnimation(ballsOnCircle);
        GameController.setRotateAnimation(rotateAnimation);
        GameController.setCurrentPhase(1);
        gamePane = DefaultMaps.getDefaultMap(gamePane,numberOfMap);
        rotateAnimation.setAmount(amount);
        rotateAnimation.play();
    }


    public SmallCircle makeSmallCircle(Pane gamePane) {
        SmallCircle smallCircle = new SmallCircle();
        gamePane.getChildren().add(smallCircle);
        smallCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                if (keyName.equals("Space")) {
                    System.out.println("im reporting you some stuff. first, the current phase is " + GameController.getCurrentPhase()
                    + " and the size of array list of ball circles minus 5 is " + (GameController.getBallsOnCircle().size() - 5));
                    GameController.shoot(gamePane,smallCircle);
                } else if (keyName.equals("Tab")) {
                    GameController.freeze(gamePane);
                } else if (keyName.equals("Backspace")) {
                    GameController.pause(gamePane);
                } else if (keyName.equals("Right")) {
                    smallCircle.setCenterX(smallCircle.getCenterX() + 5);
                } else if (keyName.equals("Left")) {
                    smallCircle.setCenterX(smallCircle.getCenterX() - 5);
                }
            }
        });
        smallCircle.requestFocus();
        return smallCircle;
    }

    public void goToNextPhase(Pane pane) {
        if (GameController.getCurrentPhase() == 1)
            goToPhase2(pane);
        else if (GameController.getCurrentPhase() == 2)
            goToPhase3(pane);
        else if (GameController.getCurrentPhase() == 3)
            goToPhase4(pane);
        else
            GameController.GameOverWin(pane);
    }

    private void goToPhase2(Pane pane) {
        GameController.setCurrentPhase(2);
        System.out.println("come to phase 2");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (GameController.getCurrentPhase() <= 1) {
                    timer.cancel();
                    return;
                }
                System.out.println("still running reverse");
                GameController.getRotateAnimation().setAmount(-1 * GameController.getRotateAnimation().getAmount());
            }
        }, 0, 4000);
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (GameController.getCurrentPhase() <= 1) {
                    timer.cancel();
                    return;
                }
                System.out.println("still running size animation");
                for (SmallCircle circle : GameController.getBallsOnCircle()) {
                    if (circle.getRadius() == 10)
                        circle.setRadius(12);
                    else if (circle.getRadius() == 12)
                        circle.setRadius(10);
                    //TODO what should i do for isCrashInCurrentBalls
//                    if (GameController.isCrashInCurrentBalls(GameController.getBallsOnCircle())) {
//                        try {
//                            GameController.GameOverLost(pane);
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                }
            }
        }, 0, 1000);
    }

    private void goToPhase3(Pane pane) {
        GameController.setCurrentPhase(3);
        System.out.println("come to phase 3");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (GameController.getCurrentPhase() <= 2) {
                    timer.cancel();
                    return;
                }
                boolean isFirstMemberVisible = GameController.getBallsOnCircle().get(0).isVisible();
                for (SmallCircle circle : GameController.getBallsOnCircle()) {
                    circle.setVisible(!isFirstMemberVisible);
                }
                for (Line line : GameController.getLinesOnCircle()) {
                    line.setVisible(!isFirstMemberVisible);
                }
            }
        }, 0, 1000);
    }

    private void goToPhase4(Pane pane) {
        GameController.setCurrentPhase(4);
        System.out.println("come to phase 4");
    }
}

