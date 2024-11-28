package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class AvatarMenu extends Application {
    public static MediaPlayer avatarMenuSoundTrack;

    @Override
    public void start(Stage stage) throws Exception {
        musicPlay();

        URL url = AvatarMenu.class.getResource("/FXML/AvatarMenu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Pane root = fxmlLoader.load(url);
        Scene scene = new Scene(root);

        String imagePath = "file:src/main/resources/BackGrounds/BG-OneTowerRemaining.jpg";
        setBackGround(imagePath, root);

        String iconPath = "file:src/main/resources/BackGrounds/icon.jpg";
        stage.getIcons().add(new Image(iconPath));

        stage.setScene(scene);
        stage.setTitle("Avatar");
        stage.show();
    }

    private void musicPlay() {
        String musicPath = "/SoundEffects/SoundTracks/And_Justice_for_All.1979.720p.x264(MoboMovies).mkv";
        avatarMenuSoundTrack = new MediaPlayer(new Media(AvatarMenu.class.getResource(musicPath).toExternalForm()));
        avatarMenuSoundTrack.setCycleCount(MediaPlayer.INDEFINITE);
        avatarMenuSoundTrack.play();
    }

    private static void setBackGround(String imagePath, Pane root) {
        Image image = new Image(imagePath);
        BackgroundImage background = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
    }
}
