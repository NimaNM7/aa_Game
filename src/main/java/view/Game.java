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
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Game.class.getResource("/FXML/game.fxml");
        Pane gamePane = FXMLLoader.load(url);
        Button pauseButton = new Button("Pause");
        gamePane.getChildren().add(pauseButton);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello please write my code");
            }
        });

        MainCircle mainCircle = makeMainCircle(gamePane);
        counter = 0;

        SmallCircle smallCircle = makeSmallCircle(gamePane,mainCircle);

        primaryStage.setScene(new Scene(gamePane));
        primaryStage.setTitle("Game");
        smallCircle.requestFocus();
        primaryStage.show();
    }

    private MainCircle makeMainCircle(Pane pane) {
        MainCircle mainCircle = new MainCircle();
        pane.getChildren().add(mainCircle);
//        mainCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                String keyName = event.getCode().getName();
//                System.out.println(keyName);
//                if (keyName.equals("Space")) {
//                    GameController.shoot(pane,mainCircle);
//                }
//            }
//        });

        RotateTransition transition = new RotateTransition(Duration.millis(700));
        transition.setNode(mainCircle);
        transition.setFromAngle(0);
        transition.setToAngle(360);
        transition.setCycleCount(-1);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        return mainCircle;
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
                } if (keyName.equals("Right")) {
                    smallCircle.setCenterX(smallCircle.getCenterX() + 5);
                }
            }
        });
        return smallCircle;
    }
}

