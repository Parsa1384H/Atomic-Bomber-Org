package view;

import enums.RegisterMenuEnums;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordControllerView {
    public Label newPassword;
    public Label confirmPassword;
    public Label currentPassword;

    public void changePassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (!User.getLoggedInUser().getPassword().equals(currentPassword.getText())) {
            alert.setTitle("Wrong Password");
            alert.setHeaderText("Wrong Password");
            alert.setContentText("Your password is incorrect");
            alert.showAndWait();
        } else if (newPassword.getText().equals(newPassword.getText())) {
            alert.setTitle("Wrong Password");
            alert.setHeaderText("Wrong Password");
            alert.setContentText("Your password cannot be the same");
            alert.showAndWait();
        } else if (!newPassword.getText().equals(confirmPassword.getText())) {
            alert.setTitle("Wrong Password");
            alert.setHeaderText("Wrong Password");
            alert.setContentText("Your need to confirm your password");
            alert.showAndWait();
        } else if (newPassword.getText().equals(confirmPassword.getText()) &&
        !getCommandMatcher(newPassword.getText(), RegisterMenuEnums.STRONG_PASSWORD.getRegex()).matches()) {
            alert.setTitle("Wrong Password");
            alert.setHeaderText("Wrong Password");
            alert.setContentText("Your password is too weak");
            alert.showAndWait();
        } else {
            User.getLoggedInUser().setPassword(newPassword.getText());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Your password has been changed successfully");
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
