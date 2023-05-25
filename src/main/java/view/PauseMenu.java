package view;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class PauseMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/PauseMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);

        String controlButtons = "shooting : " + GameController.getGame().getPlayer().getPreferredShootingButton() + "\n" +
                "freeze : " + GameController.getGame().getPlayer().getPreferredFreezeButton() + "\n" +
                "second player shooting : " + GameController.getGame().getPlayer().getPreferredSecondPlayerShootingButton();

        Text controlButtonsText = new Text(controlButtons);
        controlButtonsText.setX(50);
        controlButtonsText.setY(320);
        controlButtonsText.setFont(new Font("Segoe Print",20));
        controlButtonsText.setFill(Color.GRAY);
        borderPane.getChildren().add(controlButtonsText);

        primaryStage.setTitle("Pause");
        Scene pauseScene = new Scene(borderPane);
        primaryStage.setScene(pauseScene);
        primaryStage.show();
    }

    public void resume(MouseEvent mouseEvent) throws Exception {
        GameController.getRotateAnimation().play();
        GameController.getMediaPlayer().play();
        LoginMenu.stage.setScene(GameController.getGame().scene);
        LoginMenu.stage.show();
    }

    public void restart(MouseEvent mouseEvent) throws Exception {
        System.out.println("restart button");
        GameController.reset();
        new Game().start(LoginMenu.stage);
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        GameController.reset();
        new MainMenu().start(LoginMenu.stage);
    }

    public void changeMusic(MouseEvent mouseEvent) {

    }
}
