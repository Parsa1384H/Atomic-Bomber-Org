package view;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

import java.io.File;

public class AvatarControllerView {

    public void back(ActionEvent mouseEvent) {
        AvatarMenu.avatarMenuSoundTrack.pause();
        RegisterMenu.isMusicPlaying = false;
        RegisterMenu.musicPlay();

        ProfileMenu profileMenu = new ProfileMenu();
        try {
            profileMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SaddamPicture(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to change your avatar");
        alert.setContentText("Are you sure you want to change your avatar?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String imagePath = "src/main/resources/AVATARS/ToChoose/Avatar-Taliban4.jpg";
                try {
                    User.getLoggedInUser().setAvatarPath(new File(imagePath).toURI().toString());
                } catch (Exception e) {
                    System.out.println("Error setting avatar: " + e.getMessage());
                }
            }
        });
    }

    public void MoammarPicture(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to change your avatar");
        alert.setContentText("Are you sure you want to change your avatar?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String imagePath = "src/main/resources/AVATARS/ToChoose/Avatar-Taliban3.jpg";
                try {
                    User.getLoggedInUser().setAvatarPath(new File(imagePath).toURI().toString());
                } catch (Exception e) {
                    System.out.println("Error setting avatar: " + e.getMessage());
                }
            }
        });
    }

    public void BinladenPicture(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to change your avatar");
        alert.setContentText("Are you sure you want to change your avatar?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String imagePath = "src/main/resources/AVATARS/ToChoose/Avatar-Taliban2.jpg";
                try {
                    User.getLoggedInUser().setAvatarPath(new File(imagePath).toURI().toString());
                } catch (Exception e) {
                    System.out.println("Error setting avatar: " + e.getMessage());
                }
            }
        });

    }

    public void AlBaghdadiPicture(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to change your avatar");
        alert.setContentText("Are you sure you want to change your avatar?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String imagePath = "src/main/resources/AVATARS/ToChoose/Avatar-Taliban1.jpg";
                try {
                    User.getLoggedInUser().setAvatarPath(new File(imagePath).toURI().toString());
                } catch (Exception e) {
                    System.out.println("Error setting avatar: " + e.getMessage());
                }
            }
        });
    }

    public void selectFromFiles(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(RegisterMenu.stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            User.getLoggedInUser().setAvatarPath(file.toURI().toString());
        }
    }

    public void drag(ActionEvent actionEvent) {
        DragMenu dragMenu = new DragMenu();
        try {
            dragMenu.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
