package view;

import controller.ProfileMenuController;
import controller.UserController;
import controller.database.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Avatar;
import utils.GraphicUtils;
import utils.PasswordProblem;
import view.enums.ProfileMenuMessages;

import java.io.File;
import java.net.URL;
import java.util.List;

public class ProfileMenu extends Application {
    static BorderPane borderPane;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        borderPane = FXMLLoader.load(new URL(ProfileMenu.class.getResource("/FXML/ProfileMenu.fxml").toExternalForm()));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Menu");
        primaryStage.show();
    }

    public void changeUsernamePassword(MouseEvent mouseEvent) {
        if (UserController.getCurrentUser().isGuest()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cant access this part!");
            alert.showAndWait();
            return;
        }
        borderPane.getChildren().clear();
        VBox vBox = new VBox();
        vBox = (VBox) GraphicUtils.initializeFields(vBox,borderPane.getWidth()/2 - 150,100,"new username",
                "new password", "change");
        Text finalMessage = GraphicUtils.makeText(null,20,Color.GRAY,20,160);
        TextField textField = (TextField) vBox.getChildren().get(0);
        PasswordField passwordField = (PasswordField) vBox.getChildren().get(1);
        Button submit = (Button) vBox.getChildren().get(2);
        VBox finalVBox = vBox;
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                finalVBox.getChildren().remove(finalMessage);
                ProfileMenuMessages message = ProfileMenuController.change(textField.getText(),passwordField.getText());
                if (!message.equals(ProfileMenuMessages.DONE) && !message.equals(ProfileMenuMessages.PASSWORD_WEAK)) {
                    finalMessage.setText(message.getMessage());
                    finalVBox.getChildren().add(finalMessage);
                } else if (message.equals(ProfileMenuMessages.PASSWORD_WEAK)) {
                    finalMessage.setText(PasswordProblem.showErrors(ProfileMenuController.passwordProblems));
                    finalVBox.getChildren().add(finalMessage);
                } else {
                    try {
                        Database.saveUsers();
                        new ProfileMenu().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        borderPane.setCenter(vBox);
        stage.show();
    }

    public void changeAvatar(MouseEvent mouseEvent) {
        if (UserController.getCurrentUser().isGuest()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cant access this part!");
            alert.showAndWait();
            return;
        }
        borderPane.getChildren().clear();
        Avatar currentAvatar = UserController.getCurrentUser().getAvatar();
        currentAvatar.setXAndY(borderPane.getWidth()/2 - 25,25);
        VBox vBox = new VBox();
        Text text1 = GraphicUtils.makeText("your current avatar",15,Color.GRAY,borderPane.getWidth()/2 - 70,50);
        Text text2 = GraphicUtils.makeText("Choose your new avatar:",20,Color.YELLOW,borderPane.getWidth()/2 - 100,60);
        HBox hBox = new HBox();
        for (int i = 0; i < 5; i++) {
            Avatar newAvatar = GraphicUtils.getAllDefaultAvatars()[i];
            newAvatar.setXAndY(50 + 20 * i, 80);
            hBox.getChildren().add(newAvatar);
            newAvatar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    UserController.getCurrentUser().setAvatarPath(newAvatar.getPath());
                    Database.saveUsers();
                    try {
                        new ProfileMenu().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        vBox.getChildren().addAll(List.of(currentAvatar,text1,text2,hBox));
        borderPane.setCenter(vBox);
        stage.show();
    }

    public void goBack(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(stage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        UserController.setCurrentUser(null);
        new LoginMenu().start(stage);
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        if (!UserController.getCurrentUser().isGuest())
            UserController.removeUser(UserController.getCurrentUser());
        UserController.setCurrentUser(null);
        new LoginMenu().start(stage);
    }
}
