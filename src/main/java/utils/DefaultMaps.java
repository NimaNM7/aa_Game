package utils;

import controller.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import model.SmallCircle;

public class DefaultMaps {
    public static void getDefaultMap(Pane pane, int x) {
        switch (x) {
            case 1 -> getMap1(pane);
            case 2 -> getMap2(pane);
            case 3 -> getMap3(pane);
        }
    }

    private static void getMap1(Pane pane) {
        SmallCircle circle;
        for (int i = 0; i < 5; i++) {
            circle = new SmallCircle();
            circle.setCenterX(100 + 70 * i);
            circle.setCenterY(Math.pow(-1,i) * Math.sqrt(32400 - Math.pow(circle.getCenterX() - 250,2)) + 350);
            pane.getChildren().add(circle);
            GameController.getBallsOnCircle().add(circle);
            GameController.rotateBall(pane,circle);
        }
    }

    private static void getMap2(Pane pane) {
        SmallCircle circle;
        for (int i = 0; i < 5; i++) {
            circle = new SmallCircle();
            circle.setCenterX(90 + 81 * i);
            circle.setCenterY(Math.pow(-1,i) * Math.sqrt(32400 - Math.pow(circle.getCenterX() - 250,2)) + 350);
            pane.getChildren().add(circle);
            GameController.getBallsOnCircle().add(circle);
            GameController.rotateBall(pane,circle);
        }
    }

    private static void getMap3(Pane pane) {
        SmallCircle circle;
        for (int i = 0; i < 5; i++) {
            circle = new SmallCircle();
            circle.setCenterX(80 + 77 * i);
            circle.setCenterY(Math.pow(-1,i) * Math.sqrt(32400 - Math.pow(circle.getCenterX() - 250,2)) + 350);
            pane.getChildren().add(circle);
            GameController.getBallsOnCircle().add(circle);
            GameController.rotateBall(pane,circle);
        }
    }

}
