module HW {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    exports view;
    opens view to javafx.fxml;
    opens model to com.google.gson, com.fasterxml.jackson.databind;
    exports view.Animations;
    opens view.Animations to javafx.fxml;
}