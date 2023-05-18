package utils;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class GraphicUtils {
    public static Image defaultAvatar = new Image(GraphicUtils.class.getResource("/images/defaultAvatar.jpg").toExternalForm());

    public static Pane initializeFields(Pane pane, double x, double y, String textFieldText, String passwordFieldText, String buttonText) {
        TextField textField = new TextField();
        textField.setTranslateX(x);
        textField.setTranslateY(y);
        textField.setMaxWidth(300);
        textField.setPromptText(textFieldText);
        PasswordField passwordField = new PasswordField();
        passwordField.setTranslateX(x);
        passwordField.setTranslateY(y+20);
        passwordField.setMaxWidth(300);
        passwordField.setPromptText(passwordFieldText);
        Button button = new Button(buttonText);
        button.setTranslateX(x + 100);
        button.setTranslateY(y+40);
        pane.getChildren().addAll(List.of(textField,passwordField,button));
        return pane;
    }

    public static Text makeText(String content, int size, Color color, double x, double y) {
        Text text = new Text(content);
        text.setFill(color);
        text.setTranslateX(x);
        text.setTranslateY(y);
        text.setFont(new Font("Segoe Print",size));
        return text;
    }
}
