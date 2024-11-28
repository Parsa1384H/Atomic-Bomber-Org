package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public class DragMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        DragMenu.stage = stage;
        URL url = AvatarMenu.class.getResource("/FXML/DragAvatar.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Pane root = fxmlLoader.load(url);

        String imagePath = "file:src/main/resources/BackGrounds/BG-drag.png";
        setBackGround(imagePath, root);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Drag and Drop");
        stage.show();
    }

    private static void setBackGround(String imagePath, Pane root) {
        Image image = new Image(imagePath);
        BackgroundImage background = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
    }
}
