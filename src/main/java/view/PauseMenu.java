package view;

import controller.GameController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        if (!GameController.getGame().isMute())
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

    public void changeMusic(MouseEvent mouseEvent) throws IOException {
        URL url = MainMenu.class.getResource("/FXML/PauseMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        borderPane.getChildren().clear();
        Button music1 = new Button("main soundtrack");
        music1.setMinWidth(200);
        Button music2 = new Button("m.a.a.d city - kendrick lamar");
        music2.setMinWidth(200);
        Button music3 = new Button("mosri - safir");
        music3.setMinWidth(200);
        Button back = new Button("back");
        back.setMinWidth(200);
        VBox vBox = new VBox();
        vBox.setTranslateX(200);
        vBox.setTranslateY(100);
        vBox.setSpacing(20);
        initializeButtons(new ArrayList<>(List.of(music1,music2,music3,back)));
        vBox.getChildren().addAll(music1,music2,music3,back);
        borderPane.getChildren().add(vBox);
        LoginMenu.stage.setScene(new Scene(borderPane));
        System.out.println(borderPane.getChildren());
        LoginMenu.stage.show();
    }

    private void initializeButtons(ArrayList<Button> buttons) {
        for (Button button : buttons) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (button.getText().equals("main soundtrack")) {
                        MediaPlayer backUp = new MediaPlayer(new Media(PauseMenu.class.getResource("/SoundTracks/mainSoundtrack.mp3").toString()));
                        GameController.setMediaPlayer(backUp);
                    } else if (button.getText().equals("m.a.a.d city - kendrick lamar")) {
                        MediaPlayer backUp = new MediaPlayer(new Media(PauseMenu.class.getResource("/SoundTracks/soundTrack2.mp3").toString()));
                        GameController.setMediaPlayer(backUp);
                    } else if (button.getText().equals("mosri - safir")) {
                        MediaPlayer backUp = new MediaPlayer(new Media(PauseMenu.class.getResource("/SoundTracks/soundTrack3.mp3").toString()));
                        GameController.setMediaPlayer(backUp);
                    }
                    try {
                        new PauseMenu().start(LoginMenu.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}
