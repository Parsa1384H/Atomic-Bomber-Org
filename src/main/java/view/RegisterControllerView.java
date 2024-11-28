package view;


import controller.DataBaseController;
import controller.RegisterMenuController;
import enums.RegisterMenuEnums;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterControllerView {

    public TextField userNameFiled;
    public PasswordField passwordField;


    public void loginClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String userName = userNameFiled.getText();
        String password = passwordField.getText();

        if (User.getUserByName(userName) == null) {
            alert.setTitle("Invalid username");
            alert.setHeaderText("invalid username");
            alert.setContentText("Username not found!");
            alert.showAndWait();
            userNameFiled.setText("");
            passwordField.setText("");
        } else if (!User.getUserByName(userName).getPassword().equals(password)) {
            alert.setTitle("Invalid password");
            alert.setHeaderText("invalid password");
            alert.setContentText("Your password is incorrect");
            alert.showAndWait();
            passwordField.setText("");
        } else {
            User.setLoggedInUser(User.getUserByName(userName));
            MainMenu profileMenu = new MainMenu();
            try {
                profileMenu.start(RegisterMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void signInClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String userName = userNameFiled.getText();
        String password = passwordField.getText();

        if (User.getUserByName(userName) != null) {
            alert.setTitle("Invalid username");
            alert.setHeaderText("invalid username");
            alert.setContentText("Username already exists!");
            alert.showAndWait();
        } else if (!getCommandMatcher(userName, RegisterMenuEnums.VALID_USERNAME.getRegex()).matches()) {
            alert.setTitle("Invalid username");
            alert.setHeaderText("invalid username");
            alert.setContentText("Please enter a valid username!");
            alert.showAndWait();
        } else if (!getCommandMatcher(password, RegisterMenuEnums.STRONG_PASSWORD.getRegex()).matches()) {
            alert.setTitle("Invalid password");
            alert.setHeaderText("invalid password");
            alert.setContentText("Please enter a valid password!");
            alert.showAndWait();
        } else {
            RegisterMenuController.addUser(userName, password);

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("successful");
            alert.setHeaderText("successful");
            alert.setContentText("user successfully signed in!");
            alert.showAndWait();
            userNameFiled.setText("");
            passwordField.setText("");
        }
    }

    public void guestClicked(MouseEvent mouseEvent) {
        GuestMenu guestMenu = new GuestMenu();
        try {
            guestMenu.start(RegisterMenu.stage);
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
