package view;

import controller.UserController;
import controller.database.Database;
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

        Label numberOfBallsLabel = new Label("number of balls in each phase:");
        numberOfBallsLabel.setTextFill(Color.GRAY);
        TextField numberOfBallsField = new TextField();
        numberOfBallsField.setMaxWidth(50);
        Slider numberOfBalls = new Slider(10,20,UserController.getCurrentUser().getPreferredCountOfBalls());
        numberOfBalls.setShowTickLabels(true);
        numberOfBalls.setMaxWidth(200);

        Label numberOfMapLabel = new Label("number of map:");
        numberOfMapLabel.setTextFill(Color.GRAY);

        ToggleGroup numberOfMap = new ToggleGroup();
        RadioButton map1 = new RadioButton("1");
        map1.setToggleGroup(numberOfMap);
        map1.setTextFill(Color.WHITE);

        RadioButton map2 = new RadioButton("2");
        map2.setToggleGroup(numberOfMap);
        map2.setTextFill(Color.GRAY);

        RadioButton map3 = new RadioButton("3");
        map3.setToggleGroup(numberOfMap);
        map3.setTextFill(Color.BLACK);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            if (muteCheckbox.isSelected()) {
                UserController.getCurrentUser().setMutePreferred(true);
            }
            RadioButton selectedRadioButton = (RadioButton) difficultyGroup.getSelectedToggle();
            switch (selectedRadioButton.getText()) {
                case "Easy" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.EASY);
                case "Medium" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.MEDIUM);
                case "Hard" -> UserController.getCurrentUser().setDifficultyLevel(DifficultyLevel.HARD);
            }
            UserController.getCurrentUser().setPreferredCountOfBalls((int) numberOfBalls.valueProperty().get());
            RadioButton selectedRadioButton2 = (RadioButton) numberOfMap.getSelectedToggle();
            switch (selectedRadioButton2.getText()) {
                case "1" -> UserController.getCurrentUser().setNumberOfMapPreffered(1);
                case "2" -> UserController.getCurrentUser().setNumberOfMapPreffered(2);
                case "3" -> UserController.getCurrentUser().setNumberOfMapPreffered(3);
            }
            Database.saveUsers();
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
                numberOfBallsLabel, numberOfBalls, numberOfMapLabel, map1 , map2, map3, submitButton, backButton);
        borderPane.setCenter(layout);
        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
