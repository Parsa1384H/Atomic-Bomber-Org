package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.*;
import view.Animations.AnimationBomb;
import view.Animations.AnimationExplosion;
import view.Animations.AnimationJet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Jet extends Rectangle {
    public final double WIDTH = 105;
    public final double HEIGHT = 50;
    public final Game game;
    public AnimationJet animationJet;
    public final double moveSpeed = 1.8;
    public boolean isFacingRight = true;
    public boolean isExploded = false;
    public int atomicBombCount = 0;
    public int clusterCount = 0;
    public boolean isShootable = false;

    public Jet(Game game) {
        super(105, 50);
        this.game = game;
        setX(-WIDTH);
        setY(game.HEIGHT * 1 / 5);
        setFill(new ImagePattern(new Image(Jet.class.getResource("/Illustrations/jet.png").toExternalForm())));
        GameLauncherControllerView.staticHp.setProgress(1);
    }

    public void moveUp() {
        setY(getY() - 2 * (moveSpeed + 0.2));
        if (getY() <= 0) {
            setY(0);
        }
    }

    public void moveDown() {
        setY(getY() + 2 * (moveSpeed + 0.2));
        if (getY() >= 450) {
            setY(450);
            explosion();
        }
        if (getY() >= 450 - ShooterTank.range && Game.getWaveNumber() > 1) {
            isShootable = true;
        }
    }

    public void explosion() {
        animationJet.pause();
        isExploded = true;
        AnimationExplosion animationExplosion = new AnimationExplosion(this);
        animationExplosion.play();
        GameLauncherControllerView.staticHp.setProgress(0);
    }

    public double moveRight() {
        double currentPosition = getX() + moveSpeed;
        if (currentPosition >= game.WIDTH) {
            currentPosition -= game.WIDTH + WIDTH;
        }
        return currentPosition;
    }

    public double moveLeft() {
        double currentPosition = getX() - moveSpeed;
        if (currentPosition < -WIDTH) {
            currentPosition += WIDTH + game.WIDTH;
        }
        return currentPosition;
    }

    public void shootNormalMissile() {
        NormalMissile normalMissile = new NormalMissile(game, this);
        GameLauncher.pane.getChildren().add(normalMissile);
        GameLauncher.missilesShot++;
    }

    public void shootAtomicMissile() {
        AtomicBomb atomicBomb = new AtomicBomb(game, this);
        GameLauncher.pane.getChildren().add(atomicBomb);
        GameLauncher.missilesShot++;
    }

    public void shootCluster() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(6);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(400), event -> {
            launchMissile();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void launchMissile() {
        NormalMissile missile = new NormalMissile(game,this);
        GameLauncher.pane.getChildren().add(missile);
    }

}