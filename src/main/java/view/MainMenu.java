package view;

import controller.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Text text = new Text(20,80,"Welcome to aa Game dear " + UserController.getCurrentUser().getUsername());
        text.setFill(Color.GRAY);
        text.setFont(new Font("Segoe Print",20));
        borderPane.getChildren().add(text);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    public void continueLastGame(MouseEvent mouseEvent) {
        //TODO
    }

    public void startNewGame(MouseEvent mouseEvent) throws Exception {
        User currentUser = UserController.getCurrentUser();
        new Game().start(LoginMenu.stage);
    }

    public void goToProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void goToScoreBoard(MouseEvent mouseEvent) throws Exception {
        new ScoreBoard().start(LoginMenu.stage);
    }

    public void goToSettings(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(LoginMenu.stage);
    }
}
