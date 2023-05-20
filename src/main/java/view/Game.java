package view;

import controller.GameController;
import controller.UserController;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.MainCircle;
import model.SmallCircle;
import model.User;

import java.net.URL;
import java.util.Objects;

public class Game extends Application {
    private Pane gamePane;
    private User player;
    private int countOfBalls;
    private  DifficultyLevel difficultyLevel;
    private boolean isMute;
    static int counter;

    public Game() {
        this.player = UserController.getCurrentUser();
        player.setCurrentGame(this);
        this.countOfBalls = player.getPreferredCountOfBalls();
        this.difficultyLevel = player.getDifficultyLevel();
        this.isMute = player.isMutePreferred();
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

    public void setCountOfBalls(int countOfBalls) {
        this.countOfBalls = countOfBalls;
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
        this.gamePane = gamePane;
        Button pauseButton = new Button("Pause");
        gamePane.getChildren().add(pauseButton);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello please write my code");
            }
        });

        MainCircle mainCircle = new MainCircle();
        gamePane.getChildren().add(mainCircle);
        counter = 0;

        SmallCircle smallCircle = makeSmallCircle(gamePane,mainCircle);

        primaryStage.setScene(new Scene(gamePane));
        primaryStage.setTitle("Game");
        smallCircle.requestFocus();
        primaryStage.show();
    }


    private SmallCircle makeSmallCircle(Pane gamePane, MainCircle mainCircle) {
        SmallCircle smallCircle = new SmallCircle();
        smallCircle.requestFocus();
        gamePane.getChildren().add(smallCircle);
        smallCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                if (keyName.equals("Space")) {
                    GameController.shoot(gamePane,mainCircle);
                    if (GameController.getBallsOnCircle().size() == countOfBalls) {
                        GameController.GameOver();
                    }
                } else if (keyName.equals("Tab")) {
                    //TODO freeze
                }
            }
        });
        return smallCircle;
    }
}

