package view;

import controller.*;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.enums.LoginMenuMessages;

public class LoginMenuController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public void login(MouseEvent mouseEvent) throws Exception {
        LoginMenuMessages message = LoginController.login(username.getText(),password.getText());
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
}
