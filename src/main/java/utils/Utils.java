package utils;

import javafx.scene.control.Label;
import javafx.scene.media.Media;

public class Utils {
    public static int getTimeFromLabel(Label label) {
        String labelText = label.getText();
        int minutes = Integer.parseInt(labelText.substring(0,2));
        int seconds = Integer.parseInt(labelText.substring(3,5));
        return minutes * 60 + seconds;
    }

}
