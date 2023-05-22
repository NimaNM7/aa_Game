package view;

import controller.LoginMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.enums.LoginMenuMessages;

import java.net.URL;

public class LoginMenu extends Application {
    public static Stage stage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/LoginMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.isResizable();
        stage.show();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        LoginMenuMessages message = LoginMenuController.login(username.getText(),password.getText());
        if (!message.equals(LoginMenuMessages.LOGIN_DONE)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error!");
            alert.setContentText(message.getMessage());
            alert.showAndWait();
        } else {
            new MainMenu().start(LoginMenu.stage);
        }
    }

    public void signUpMenu(MouseEvent mouseEvent) throws Exception {
        new SignUpMenu().start(LoginMenu.stage);
    }

    public void playAsGuest(MouseEvent mouseEvent) throws Exception {
        LoginMenuController.playAsGuest();
        new MainMenu().start(LoginMenu.stage);
    }
}
