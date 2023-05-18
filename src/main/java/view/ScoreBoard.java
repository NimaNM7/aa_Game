package view;

import controller.UserController;
import controller.database.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ScoreBoard extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(new URL(ScoreBoard.class.getResource("/FXML/ScoreBoard.fxml").toExternalForm()));
        VBox vBox = new VBox();
        Label label = null;
        User user;
        for (int i = 0; i < UserController.getAllUsersSorted().size() && i < 10; i++) {
            user = UserController.getAllUsersSorted().get(i);
            label = new Label((i+1) + "- username: " + user.getUsername() + " - score: " + user.getScore() + " - total time: " + user.getTotalTime());
            if (i == 0)
                label.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (i == 1)
                label.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (i == 2)
                label.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
            label.setTextFill(Color.BLACK);
            label.setTranslateX(50);
            label.setTranslateY(20 + 10 * i);
            vBox.getChildren().add(label);
        }
        Button back = new Button("back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new MainMenu().start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        back.setTranslateY(240);
        back.setTranslateX(100);
        vBox.getChildren().add(back);
        borderPane.setCenter(vBox);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("ScoreBoard");
        primaryStage.show();
    }
}
