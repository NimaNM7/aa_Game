package view;

import controller.SignUpController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.PasswordProblem;
import view.enums.SignUpMenuMessages;

public class SignUpMenuController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirmation;
    public void signUp(MouseEvent mouseEvent) {
        Alert alert;
        SignUpMenuMessages message = SignUpController.signUp(username.getText(), password.getText(),passwordConfirmation.getText());
        if (message.equals(SignUpMenuMessages.SIGN_UP_SUCCESSFUL)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("SignUp Successful!");
            alert.setHeaderText(message.getMessage());
            alert.showAndWait();
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
}
