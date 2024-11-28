package view;


import controller.DataBaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.User;

import java.io.File;
import java.net.URL;
import java.util.Random;

public class RegisterMenu extends Application {
    public static Stage stage;
    public static MediaPlayer allMenusSoundTrack;
    public static boolean isMusicPlaying = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        User.setUsers(DataBaseController.loadUsers());
        RegisterMenu.stage = stage;

        musicPlay();

        URL url = RegisterMenu.class.getResource("/FXML/RegisterMenu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(url);
        stage.setTitle("Register Menu");

        String imagePath = "file:src/main/resources/BackGrounds/BG-3jets.jpg";
        setBackGround(imagePath, root);

        String iconPath = "file:src/main/resources/BackGrounds/icon.jpg";
        stage.getIcons().add(new Image(iconPath));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void musicPlay() {
        if (!isMusicPlaying) {
            File musicDir = new File("src/main/resources/SoundEffects/SoundTracks/MenuSoundTracks"); // Change path accordingly
            File[] files = musicDir.listFiles((dir, name) -> name.endsWith(".mp3")); // Filter for MP3 files
            if (files != null && files.length > 0) {
                File fileToPlay = files[new Random().nextInt(files.length)];
                Media hit = new Media(fileToPlay.toURI().toString());
                allMenusSoundTrack = new MediaPlayer(hit);
                allMenusSoundTrack.setCycleCount(MediaPlayer.INDEFINITE);
                allMenusSoundTrack.play();
                isMusicPlaying = true;
            }
        }
    }

    private static void setBackGround(String imagePath, Pane root) {
        Image image = new Image(imagePath);
        BackgroundImage background = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
    }
}