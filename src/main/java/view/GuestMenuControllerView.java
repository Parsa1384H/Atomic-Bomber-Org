package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GuestMenuControllerView {

    public Label welcomeText;

    public void initialize() {
        welcomeText.setText("Welcome Guest");
    }

    public void newGame(ActionEvent actionEvent) {
        GameLauncher gameMenu = new GameLauncher("Guest", new Pane());
        try {
            gameMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void logOut(ActionEvent actionEvent) {
        RegisterMenu registerMenu = new RegisterMenu();
        try {
            registerMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
