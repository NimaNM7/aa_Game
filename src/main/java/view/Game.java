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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.MainCircle;
import model.SmallCircle;
import model.User;
import utils.DefaultMaps;
import utils.GraphicUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game extends Application {
    private User player;
    private final int countOfBalls;
    private  DifficultyLevel difficultyLevel;
    private boolean isMute;
    private final int numberOfMap;

    public Game() {
        this.player = UserController.getCurrentUser();
        player.setCurrentGame(this);
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Game.class.getResource("/FXML/game.fxml");
        Pane gamePane = FXMLLoader.load(url);

        HBox hbox = new HBox();
        hbox.setTranslateX(10);
        hbox.setTranslateY(10);
        hbox.setSpacing(20);

        Label numberOfBallsLeft = new Label("number of balls left : " + countOfBalls);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.0);
        progressBar.setStyle("-fx-accent: #0000FF; -fx-background-color: #FFFFFF;");


        Button pauseButton = new Button("Pause");

        hbox.getChildren().addAll(List.of(pauseButton,numberOfBallsLeft,progressBar));
        gamePane.getChildren().add(hbox);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello please write my code");
            }
        });

        MainCircle mainCircle = new MainCircle();
        gamePane.getChildren().add(mainCircle);



        initializeGame(gamePane,GameController.getBallsOnCircle(),2,numberOfMap);

        SmallCircle smallCircle = makeSmallCircle(gamePane,mainCircle);

        primaryStage.setScene(new Scene(gamePane));
        primaryStage.setTitle("Game");
        smallCircle.requestFocus();
        pauseButton.setFocusTraversable(false);
        primaryStage.show();
    }

    private void initializeGame(Pane gamePane, ArrayList<SmallCircle> ballsOnCircle, int amount, int numberOfMap) {
        RotateAnimation rotateAnimation = new RotateAnimation(ballsOnCircle);
        GameController.setRotateAnimation(rotateAnimation);
        gamePane = DefaultMaps.getDefaultMap(gamePane,numberOfMap);
        rotateAnimation.setAmount(amount);
        rotateAnimation.play();
    }


    private SmallCircle makeSmallCircle(Pane gamePane, MainCircle mainCircle) {
        SmallCircle smallCircle = new SmallCircle();
        gamePane.getChildren().add(smallCircle);
        smallCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                if (keyName.equals("Space")) {
                    GameController.shoot(gamePane,mainCircle);
                    if (GameController.getBallsOnCircle().size() == countOfBalls + 5) {
                        GameController.GameOver();
                    }
                } else if (keyName.equals("Tab")) {
                    GameController.freeze(gamePane);
                }
            }
        });
        return smallCircle;
    }
}

