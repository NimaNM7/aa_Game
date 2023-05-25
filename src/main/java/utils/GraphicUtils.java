package utils;

import controller.UserController;
import controller.database.Database;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Avatar;
import view.ProfileMenu;

import java.util.List;

public class GraphicUtils {
    public static Avatar getAvatarWithAddress(String address) {
        return new Avatar(new Image(GraphicUtils.class.getResource(address).toExternalForm()));
    }

    public static Avatar[] getAllDefaultAvatars() {
        Avatar[] allAvatars = new Avatar[5];
        for (int i = 0; i < 5; i++) {
            Avatar newAvatar = new Avatar(new Image(ProfileMenu.class.getResource("/images/avatar" + i + ".jpg").toExternalForm()));
            newAvatar.setPath("/images/avatar" + i + ".jpg");
            newAvatar.setXAndY(50 + 20 * i, 80);
            allAvatars[i] = newAvatar;
        }
        return allAvatars;
    }

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

    public static TextField makeControlSettings() {
        TextField choose = new TextField();
        choose.setPromptText("click the button you want");
        choose.setMaxWidth(300);
        choose.setEditable(false);
        choose.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                choose.setText(event.getCode().getName());
            }
        });
        return choose;
    }

    public static void makeAllLabelsColored(Color color, Label... labels) {
        for (Label label : labels) {
            label.setTextFill(color);
        }
    }
}
