package view;

import controller.SignUpController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.enums.SignUpMenuMessages;

public class SignUpMenuController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirmation;
    public void signUp(MouseEvent mouseEvent) {
        SignUpMenuMessages message = SignUpController.signUp(username.getText(), password.getText(),passwordConfirmation.getText());

    }
}
