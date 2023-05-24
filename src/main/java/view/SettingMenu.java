package view;

import controller.UserController;
import controller.database.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.User;
import utils.GraphicUtils;

import java.net.URL;

public class SettingMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(new URL(SettingMenu.class.getResource("/FXML/SettingMenu.fxml").toExternalForm()));
        Label difficultyLabel = new Label("Difficulty Level:");
        ComboBox<String> chooseDifficulty = new ComboBox<>();
        chooseDifficulty.getItems().addAll("Easy","Medium","Hard");

        CheckBox muteCheckbox = new CheckBox("Mute");
        muteCheckbox.setTextFill(Color.GRAY);

        Label numberOfBallsLabel = new Label("number of balls in each phase:");
        ComboBox<Integer> chooseNumberOfBalls = new ComboBox<>();
        chooseNumberOfBalls.getItems().addAll(4,8,12,16,20,24,28,32,36,40);

        Label numberOfMapLabel = new Label("number of map:");
        ComboBox<Integer> chooseNumberOfMap = new ComboBox<>();
        chooseNumberOfMap.getItems().addAll(1,2,3);

        Label shootingButtonLabel = new Label("shooting button:");
        TextField chooseShootingButton = GraphicUtils.makeControlSettings();

        Label freezeButtonLabel = new Label("freeze button:");
        TextField chooseFreezeButton = GraphicUtils.makeControlSettings();

        Label secondPlayerShootingButtonLabel = new Label("second player shooting button (in multiplayer game):");
        TextField chooseSecondPlayerShootingButton = GraphicUtils.makeControlSettings();

        GraphicUtils.makeAllLabelsColored(Color.GRAY,difficultyLabel,numberOfBallsLabel,numberOfMapLabel,
                shootingButtonLabel,freezeButtonLabel,secondPlayerShootingButtonLabel);

        Button submitButton = new Button("Submit");
        Button backButton = new Button("back");
        submitButton.setOnAction(event -> {
            if (muteCheckbox.isSelected()) {
                UserController.getCurrentUser().setMutePreferred(true);
            }
            if (chooseDifficulty.getSelectionModel().getSelectedItem() != null) {
                switch (chooseDifficulty.getSelectionModel().getSelectedItem()) {
                    case "Easy" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.EASY);
                    case "Medium" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.MEDIUM);
                    case "Hard" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.HARD);
                    default -> {
                    }
                }
            }
            if (chooseNumberOfBalls.getSelectionModel().getSelectedItem() != null)
                UserController.getCurrentUser().setPreferredCountOfBalls(chooseNumberOfBalls.getSelectionModel().getSelectedItem());
            if (chooseNumberOfMap.getSelectionModel().getSelectedItem() != null)
                UserController.getCurrentUser().setNumberOfMapPreferred(chooseNumberOfMap.getSelectionModel().getSelectedItem());
            if (chooseShootingButton.getText() != null)
                UserController.getCurrentUser().setPreferredShootingButton(chooseShootingButton.getText());
            if (chooseFreezeButton.getText() != null)
                UserController.getCurrentUser().setPreferredFreezeButton(chooseFreezeButton.getText());
            if (chooseSecondPlayerShootingButton.getText() != null)
                UserController.getCurrentUser().setPreferredSecondPlayerShootingButton(chooseSecondPlayerShootingButton.getText());

            Database.saveUsers();
            try {
                new MainMenu().start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new MainMenu().start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(difficultyLabel, chooseDifficulty, muteCheckbox,
                numberOfBallsLabel, chooseNumberOfBalls, numberOfMapLabel, chooseNumberOfMap
                , shootingButtonLabel, chooseShootingButton , freezeButtonLabel ,
                chooseFreezeButton , secondPlayerShootingButtonLabel ,chooseSecondPlayerShootingButton,submitButton, backButton);
        borderPane.setCenter(layout);
        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
