package view;

import controller.DataBaseController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class MainMenuControllerView {
    public Label welcomeText;
    public void initialize() {
        welcomeText.setText("Welcome " + User.getLoggedInUser().getUsername());
    }

    public void exit(MouseEvent mouseEvent) {
        DataBaseController.saveUsers();
        System.exit(0);
    }

    public void profileMenu(MouseEvent mouseEvent) {
        ProfileMenu profileMenu = new ProfileMenu();
        try {
            profileMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SettingMenu(ActionEvent actionEvent) {
        SettingsMenu settingsMenu = new SettingsMenu();
        try {
            settingsMenu.   start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ScoresMenu(ActionEvent actionEvent) {
        ScoresMenu scoresMenu = new ScoresMenu();
        try {
            scoresMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startGame(ActionEvent actionEvent) {
        GameLauncher gameMenu = new GameLauncher(User.getLoggedInUser().getUsername() , new Pane());
        try {
            RegisterMenu.stage.close();
            gameMenu.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
