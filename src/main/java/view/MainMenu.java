package view;

import controller.GameController;
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
import model.Avatar;
import model.User;

import java.net.URL;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Avatar currentAvater = UserController.getCurrentUser().getAvatar();
        currentAvater.setXAndY(500,10);
        Text text = new Text(20,80,"Welcome to aa Game dear " + UserController.getCurrentUser().getUsername());
        text.setFill(Color.GRAY);
        text.setFont(new Font("Segoe Print",20));
        borderPane.getChildren().get(0).setTranslateY(60);
        borderPane.getChildren().addAll(text,currentAvater);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    public void startNewGame(MouseEvent mouseEvent) throws Exception {
        GameController.setGameMultiPlayer(false);
        new Game().start(LoginMenu.stage);
    }

    public void startMultiPlayerGame(MouseEvent mouseEvent) throws Exception {
        GameController.setGameMultiPlayer(true);
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
