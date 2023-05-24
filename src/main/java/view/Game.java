package view;

import controller.GameController;
import controller.UserController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.MainCircle;
import model.SmallCircle;
import model.User;
import utils.DefaultMaps;
import utils.Utils;

import java.net.URL;
import java.util.*;

public class Game extends Application {
    private User player;
    private final int countOfBalls;
    private  DifficultyLevel difficultyLevel;
    private boolean isMute;
    private final int numberOfMap;
    private int score;
    private int totalTime;
    private boolean isMultiPlayer;
    private double windDegree = 0;

    public Game() {
        this.player = UserController.getCurrentUser();
        this.countOfBalls = player.getPreferredCountOfBalls();
        this.difficultyLevel = player.getDifficultyLevel();
        this.isMute = player.isMutePreferred();
        this.numberOfMap = player.getNumberOfMapPreferred();
        GameController.setGame(this);
        this.isMultiPlayer = GameController.isIsGameMultiPlayer();
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isMultiPlayer() {
        return isMultiPlayer;
    }

    public void setMultiPlayer(boolean multiPlayer) {
        isMultiPlayer = multiPlayer;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
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
        score.setFont(new Font("Segoe Print",27));
        score.setTextFill(Color.GREEN);
        score.setId("score");

        Label time = new Label();
        time.setText("00:00");
        time.setId("time");
        time.setTextFill(Color.PURPLE);
        time.setFont(new Font("Segoe Print",30));

        Button pauseButton = new Button("Pause");

        Label windLabel = new Label("degree of wind: " + windDegree);
        windLabel.setTranslateY(50);
        windLabel.setTranslateX(10);
        windLabel.setFont(new Font("Segoe Print",20));
        windLabel.setId("windLabel");

        hbox.getChildren().addAll(List.of(pauseButton,numberOfBallsLeft,ballsForFreeze,score,time));

        gamePane.getChildren().addAll(hbox,windLabel);
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
        handleTime(gamePane);
        SmallCircle smallCircle = makeSmallCircle(gamePane,false);

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
        score = 0;
        totalTime = 0;
        rotateAnimation.setAmount(amount);
        rotateAnimation.play();
    }


    public SmallCircle makeSmallCircle(Pane gamePane, boolean isFromSecondPlayer) {
        SmallCircle smallCircle = new SmallCircle(isFromSecondPlayer);
        gamePane.getChildren().add(smallCircle);
        smallCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                if (keyName.equals(player.getPreferredShootingButton())) {
                    GameController.shoot(gamePane,smallCircle);
                } else if (isMultiPlayer && keyName.equals(player.getPreferredSecondPlayerShootingButton())) {
                    smallCircle.setFromSecondPlayer(true);
                    GameController.shoot(gamePane,smallCircle);
                    System.out.println("from enter a work is done");
                }
                else if (keyName.equals(player.getPreferredFreezeButton())) {
                    GameController.freeze(gamePane);
                } else if (keyName.equals("Backspace")) {
                    GameController.pause(gamePane);
                } else if (keyName.equals("Right") && GameController.getCurrentPhase() == 4 && smallCircle.getCenterX() < 480) {
                    smallCircle.setCenterX(smallCircle.getCenterX() + 5);
                } else if (keyName.equals("Left") && GameController.getCurrentPhase() == 4 && smallCircle.getCenterX() > 20) {
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
        else {
            System.out.println("win from here (Game)");
            GameController.GameOverWin(pane);
        }
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
//                System.out.println("still running reverse");
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
//                System.out.println("still running size animation");
                for (SmallCircle circle : GameController.getBallsOnCircle()) {
                    if (circle.getRadius() == 10)
                        circle.setRadius(12);
                    else if (circle.getRadius() == 12)
                        circle.setRadius(10);

                    /* TODO this item is in the document and i coded it but dont want to be in my game
//                    if (GameController.isCrashInCurrentBalls(GameController.getBallsOnCircle())) {
//                        try {
//                            GameController.GameOverLost(pane);
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                       */
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
        Label windLabel = GameController.findWindLabelInPane(pane);
        Random random = new Random();
        int[] oneAndMinus = {-1,1};
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double change;
                if (GameController.getCurrentPhase() <= 3) {
                    timer.cancel();
                    return;
                }
                change = oneAndMinus[random.nextInt(0,2)] * difficultyLevel.getSpeedOfWind();
                windDegree += change;
                Platform.runLater(() -> {
                    windLabel.setText("degree of wind: " + windDegree);
                });
            }
        }, 0, 3000);

    }

    public void handleTime(Pane pane) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (GameController.getCurrentPhase() == 0) {
                    timer.cancel();
                    return;
                }
                Label timeLabel = GameController.findLabelInPane(pane,"time");
                if (Utils.getTimeFromLabel(Objects.requireNonNull(timeLabel)) == 120) {
                    try {
                        Platform.runLater(() -> {
                            try {
                                GameController.GameOverLost(pane);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                int seconds, minutes;
                minutes = Integer.parseInt(timeLabel.getText().substring(0, 2));
                seconds = Integer.parseInt(timeLabel.getText().substring(3, 5));
                seconds = (seconds + 1) % 60;
                if (seconds == 0) minutes++;
                String formattedSecond = String.format("%02d",seconds);
                String formattedMinute = String.format("%02d",minutes);
                System.out.println(formattedMinute + ":" + formattedSecond);
                Platform.runLater(() -> {
                    timeLabel.setText(formattedMinute + ":" + formattedSecond);
                });
            }
        }, 0, 1000);
    }
}

