package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.User;

import java.util.ArrayList;

import static java.lang.Math.min;

public class ScoresMenuControllerView {

    @FXML
    Label username1;
    @FXML
    Label score1;
    @FXML
    Label kills1;
    @FXML
    Label username2;
    @FXML
    Label score2;
    @FXML
    Label kills2;
    @FXML
    Label username3;
    @FXML
    Label score3;
    @FXML
    Label kills3;
    @FXML
    Label username4;
    @FXML
    Label score4;
    @FXML
    Label kills4;
    @FXML
    Label username5;
    @FXML
    Label score5;
    @FXML
    Label kills5;
    @FXML
    Label username6;
    @FXML
    Label score6;
    @FXML
    Label kills6;
    @FXML
    Label username7;
    @FXML
    Label score7;
    @FXML
    Label kills7;
    @FXML
    Label username8;
    @FXML
    Label score8;
    @FXML
    Label kills8;
    @FXML
    Label username9;
    @FXML
    Label score9;
    @FXML
    Label kills9;
    @FXML
    Label username10;
    @FXML
    Label score10;
    @FXML
    Label kills10;

    public void initialize() {
        int[] possible = new int[User.getUsers().size()];
        int maxNumber = -1;
        for (int i = 0; i < User.getUsers().size(); i++)
            possible[i] = 1;
        int size = min(10, User.getUsers().size());
        for (int i = 0; i < size; i++) {
            User max = null;
            for (int j = 0; j < User.getUsers().size(); j++) {
                if (max == null && possible[j] == 1) {
                    max = User.getUsers().get(j);
                    maxNumber = j;
                } else {
                    if (possible[j] == 1 && max.getScore() < User.getUsers().get(j).getScore()) {
                        max = User.getUsers().get(j);
                        maxNumber = j;
                    } else if (possible[j] == 1 && max.getScore() == User.getUsers().get(j).getScore() && max.getKills() > User.getUsers().get(j).getKills()) {
                        max = User.getUsers().get(j);
                        maxNumber = j;
                    }
                }
            }
            possible[maxNumber] = 0;
            String username = max.getUsername();
            String score = String.valueOf(max.getScore());
            String kills = String.valueOf(max.getKills());

            if (i == 0) {
                username1.setText(username);
                score1.setText(score);
                kills1.setText(kills);

            }
            if (i == 1) {
                username2.setText(username);
                score2.setText(score);
                kills2.setText(kills);
            }
            if (i == 2) {
                username3.setText(max.getUsername());
                score3.setText(String.valueOf(max.getScore()));
                kills3.setText(kills);
            }
            if (i == 3) {
                username4.setText(max.getUsername());
                score4.setText(String.valueOf(max.getScore()));
                kills4.setText(kills);
            }
            if (i == 4) {
                username5.setText(max.getUsername());
                score5.setText(String.valueOf(max.getScore()));
                kills5.setText(kills);
            }
            if (i == 5) {
                username6.setText(max.getUsername());
                score6.setText(String.valueOf(max.getScore()));
                kills6.setText(kills);
            }
            if (i == 6) {
                username7.setText(max.getUsername());
                score7.setText(String.valueOf(max.getScore()));
                kills7.setText(kills);
            }
            if (i == 7) {
                username8.setText(max.getUsername());
                score8.setText(String.valueOf(max.getScore()));
                kills8.setText(kills);
            }
            if (i == 8) {
                username9.setText(max.getUsername());
                score9.setText(String.valueOf(max.getScore()));
                kills9.setText(kills);
            }
            if (i == 9) {
                username10.setText(max.getUsername());
                score10.setText(String.valueOf(max.getScore()));
                kills10.setText(kills);
            }
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(RegisterMenu.stage);
    }
}

