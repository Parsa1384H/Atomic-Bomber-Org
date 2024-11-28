package view;

import enums.RegisterMenuEnums;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

import java.util.regex.*;

public class ChangeUsernameControllerView {

    public TextField newUsername;

    public void changeUsername(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (newUsername.getText().equals(User.getLoggedInUser().getUsername())) {
            alert.setTitle("Invalid Username");
            alert.setHeaderText("Invalid Username");
            alert.setContentText("Your username can not be the same");
            alert.showAndWait();
        } else if (!getCommandMatcher(newUsername.getText(), RegisterMenuEnums.VALID_USERNAME.getRegex()).matches()) {
            alert.setTitle("Invalid Username");
            alert.setHeaderText("Invalid Username");
            alert.setContentText("Your chosen username is invalid");
            alert.showAndWait();
        } else {
            User.getLoggedInUser().setUsername(newUsername.getText());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setHeaderText("success");
            alert.setContentText("Your username has been changed successfully");
            alert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) {
        ProfileMenu profileMenu = new ProfileMenu();
        try {
            profileMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        return matcher;
    }
}
