package view;

import controller.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.PasswordProblem;
import view.enums.SignUpMenuMessages;

import java.net.URL;

public class SignUpMenu extends Application {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirmation;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = SignUpMenu.class.getResource("/FXML/SignUpMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(MouseEvent mouseEvent) throws Exception {
        Alert alert;
        SignUpMenuMessages message = SignUpController.signUp(username.getText(), password.getText(),passwordConfirmation.getText());
        if (message.equals(SignUpMenuMessages.SIGN_UP_SUCCESSFUL)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("SignUp Successful!");
            alert.setHeaderText(message.getMessage());
            alert.showAndWait();
            new MainMenu().start(LoginMenu.stage);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("SignUp failed!");
            if (message.equals(SignUpMenuMessages.WEAK_PASSWORD))
                alert.setContentText(PasswordProblem.showErrors(SignUpController.passwordProblems));
            else alert.setContentText(message.getMessage());
            alert.showAndWait();
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }
}
