package utils;

import controller.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import model.SmallCircle;
import view.RotateAnimation;

public class DefaultMaps {
    public static Pane getDefaultMap(Pane pane, int x) {
        switch (x) {
            case 1:
                return getMap1(pane);
            case 2:
                return getMap2(pane);
            case 3:
                return getMap3(pane);
        }
        return null;
    }

    private static Pane getMap1(Pane pane) {
        SmallCircle circle;
        for (int i = 0; i < 5; i++) {
            circle = new SmallCircle();
            circle.setCenterX(100 + 70 * i);
            circle.setCenterY(Math.pow(-1,i) * Math.sqrt(32400 - Math.pow(circle.getCenterX() - 250,2)) + 350);
            pane.getChildren().add(circle);
            GameController.getBallsOnCircle().add(circle);
            GameController.ballRotation(circle);
        }
        return pane;
    }

    private static Pane getMap2(Pane pane) {
        SmallCircle circle;
        for (int i = 0; i < 5; i++) {
            circle = new SmallCircle();
            circle.setCenterX(90 + 81 * i);
            circle.setCenterY(Math.pow(-1,i) * Math.sqrt(32400 - Math.pow(circle.getCenterX() - 250,2)) + 350);
            pane.getChildren().add(circle);
            GameController.getBallsOnCircle().add(circle);
            GameController.ballRotation(circle);
        }
        return pane;
    }

    private static Pane getMap3(Pane pane) {
        SmallCircle circle;
        for (int i = 0; i < 5; i++) {
            circle = new SmallCircle();
            circle.setCenterX(80 + 77 * i);
            circle.setCenterY(Math.pow(-1,i) * Math.sqrt(32400 - Math.pow(circle.getCenterX() - 250,2)) + 350);
            pane.getChildren().add(circle);
            GameController.getBallsOnCircle().add(circle);
            GameController.ballRotation(circle);
        }
        return pane;
    }

}
