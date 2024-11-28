package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import model.Mig;
import model.ShooterTank;
import model.Tank;

public class SettingsMenuControllerView {
    @FXML
    private CheckBox blackAndWhite;
    @FXML
    private CheckBox mute;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Slider difficultySlider;

    public void initialize() {
        difficultySlider.setMinorTickCount(0);

        difficultySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int newDifficulty = newValue.intValue();
                updateDifficultyLabel(newValue.intValue());
                handleDifficulty(newDifficulty);
            }
        });

        updateDifficultyLabel(1);
        handleDifficulty(1);

        mute.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    muteChecked();
                } else {
                    muteUnChecked();
                }
            }
        });
        blackAndWhite.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    blackAndWhiteChecked();
                } else {
                    blackAndWhiteUnchecked();
                }
            }
        });
    }

    private void blackAndWhiteUnchecked() {
        System.out.println("blackAndWhiteUnchecked");
    }

    private void blackAndWhiteChecked() {
        System.out.println("blackAndWhiteChecked");

    }

    private void muteUnChecked() {
        RegisterMenu.allMenusSoundTrack.play();
    }

    private void muteChecked() {
        RegisterMenu.allMenusSoundTrack.pause();
    }

    private void updateDifficultyLabel(int value) {
        String difficulty = switch (value) {
            case 1 -> "Easy";
            case 2 -> "Medium";
            case 3 -> "Hard";
            default -> "Unknown";
        };
        difficultyLabel.setText("Difficulty: " + difficulty);
    }

    private void handleDifficulty(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1:
                handleEasyDifficulty();
                break;
            case 2:
                handleMediumDifficulty();
                break;
            case 3:
                handleHardDifficulty();
                break;
            default:
                break;
        }
    }

    private void handleEasyDifficulty() {
        Tank.moveSpeed = 0.8;
        ShooterTank.moveSpeed = 0.8;
        ShooterTank.range = 100;
        Mig.migSpeed = 1;
        Mig.migRange = 150;
    }

    private void handleMediumDifficulty() {
        Tank.moveSpeed *= 2;
        ShooterTank.moveSpeed *= 2;
        ShooterTank.range *= 2;
        Mig.migSpeed *= 2;
        Mig.migRange *= 2;
    }

    private void handleHardDifficulty() {
        Tank.moveSpeed *= 3;
        ShooterTank.moveSpeed *= 3;
        ShooterTank.range *= 3;
        Mig.migSpeed *= 3;
        Mig.migRange *= 3;
    }


    public void back(ActionEvent actionEvent) {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(RegisterMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void apply(ActionEvent actionEvent) {
        back(actionEvent);
    }

}
