module aaGame {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.junit.jupiter.api;
    requires org.mockito;

    exports view;
    opens view to javafx.fxml;
    exports model;
    opens model to com.google.gson;
}