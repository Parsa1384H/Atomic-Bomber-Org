package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public class GuestMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/GuestMenu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Pane root = fxmlLoader.load(url);
        Scene scene = new Scene(root);

        String imagePath = "file:src/main/resources/BackGrounds/BG-BothTwinTowers.jpg";
        setBackGround(imagePath, root);

        String iconPath = "file:src/main/resources/BackGrounds/icon.jpg";
        stage.getIcons().add(new Image(iconPath));

        stage.setScene(scene);
        stage.setTitle("Guest Menu");
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
