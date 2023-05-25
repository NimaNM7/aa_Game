package view;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class PauseMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/PauseMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        primaryStage.setTitle("Pause");
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void resume(MouseEvent mouseEvent) throws Exception {
        System.out.println(GameController.getGame().gamePane == null);
        Scene scene = new Scene(GameController.getGame().gamePane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
//        System.out.println(GameController.getBallsOnCircle());
//        GameController.getGame().start(LoginMenu.stage);
    }

    public void restart(MouseEvent mouseEvent) throws Exception {
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
