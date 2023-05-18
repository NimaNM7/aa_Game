package view;

import controller.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DifficultyLevel;

import java.net.URL;

public class SettingMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(new URL(SettingMenu.class.getResource("/FXML/SettingMenu.fxml").toExternalForm()));
        Label difficultyLabel = new Label("Difficulty Level:");
        difficultyLabel.setTextFill(Color.GRAY);
        ToggleGroup difficultyGroup = new ToggleGroup();

        RadioButton easyRadioButton = new RadioButton("Easy");
        easyRadioButton.setToggleGroup(difficultyGroup);
        easyRadioButton.setTextFill(Color.GREEN);

        RadioButton mediumRadioButton = new RadioButton("Medium");
        mediumRadioButton.setToggleGroup(difficultyGroup);
        mediumRadioButton.setTextFill(Color.YELLOW);

        RadioButton hardRadioButton = new RadioButton("Hard");
        hardRadioButton.setToggleGroup(difficultyGroup);
        hardRadioButton.setTextFill(Color.RED);

        CheckBox muteCheckbox = new CheckBox("Mute");
        muteCheckbox.setTextFill(Color.GRAY);

        Label numberOfBallsLabel = new Label("number of balls:");
        numberOfBallsLabel.setTextFill(Color.GRAY);
        TextField numberOfBallsField = new TextField();
        numberOfBallsField.setMaxWidth(50);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            RadioButton selectedRadioButton = (RadioButton) difficultyGroup.getSelectedToggle();
            if (muteCheckbox.isSelected()) {
                //TODO make the game mute
            }
            switch (selectedRadioButton.getText()) {
                case "Easy" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.EASY);
                case "Medium" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.MEDIUM);
                case "Hard" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.HARD);
            }
            try {
                new MainMenu().start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Button backButton = new Button("back");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(difficultyLabel, easyRadioButton, mediumRadioButton, hardRadioButton, muteCheckbox,
                numberOfBallsLabel, numberOfBallsField, submitButton, backButton);
        borderPane.setCenter(layout);
        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
