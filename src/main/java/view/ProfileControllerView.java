package view;

import controller.DataBaseController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;

public class ProfileControllerView {

    public Label Username;
    public ImageView Image;

    public void initialize() {
        Username.setText(User.getLoggedInUser().getUsername());
        Image.setImage(new Image(User.getLoggedInUser().getAvatarPath()));
    }

    public void changeUsername(MouseEvent mouseEvent) {
        ChangeUsername changeUsername = new ChangeUsername();
        try {
            changeUsername.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        ChangePassword changePassword = new ChangePassword();
        try {
            changePassword.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void enterAvatarMenu(MouseEvent mouseEvent) {
        RegisterMenu.allMenusSoundTrack.pause();
        AvatarMenu avatarMenu = new AvatarMenu();
        try {
            avatarMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logOut(MouseEvent mouseEvent) {
        DataBaseController.saveUsers();
        RegisterMenu registerMenu = new RegisterMenu();
        try {
            registerMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to delete your account");
        alert.setContentText("Are you sure you want to delete your account?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.removeUser(User.getLoggedInUser());
                logOut(mouseEvent);
            }
        });
    }

    public void back(MouseEvent mouseEvent) {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
