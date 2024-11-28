package view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class GameLauncherControllerView {
    public Label waveNumber;
    public static Label staticWaveNumber;
    public Label kills;
    public static Label staticKills;
    public Label clusterCount;
    public static Label staticClusterCount;
    public Label atomicCount;
    public static Label staticAtomicCount;
    public Label accuracy;
    public static Label staticAccuracy;
    public ProgressBar hpBar;
    public static ProgressBar staticHp;
    public ProgressBar freezeBar;
    public static ProgressBar staticFreeze;

    public void initialize() {
        staticWaveNumber = waveNumber;
        staticKills = kills;
        staticClusterCount = clusterCount;
        staticAtomicCount = atomicCount;
        staticAccuracy = accuracy;
        staticHp = hpBar;
        staticFreeze = freezeBar;
        atomicCount.setText(atomicCount.getText() + 0);
        clusterCount.setText(clusterCount.getText() + 0);
        kills.setText(kills.getText() + 0);
        accuracy.setText(accuracy.getText() + 0);
        freezeBar.setProgress(0);
        hpBar.setStyle("-fx-accent: red;");
    }
}

