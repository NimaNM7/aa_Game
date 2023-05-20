module aaGame {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.junit.jupiter.api;
    requires org.mockito;

    exports view;
    exports model;
    opens model to com.google.gson;
    opens view to com.google.gson, javafx.fxml;
}