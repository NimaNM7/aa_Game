package model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MainCircle extends Circle { ;
    public MainCircle() {
        super(250,200,100);
//        this.setFill(Color.BLACK);
        this.setFill(new ImagePattern(new Image(MainCircle.class.getResource("/images/defaultAvatar.jpg").toExternalForm())));
    }
}
