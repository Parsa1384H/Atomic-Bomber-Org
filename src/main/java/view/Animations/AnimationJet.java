package view.Animations;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.*;
import view.GameLauncher;
import view.GameLauncherControllerView;

import java.security.Key;

public class AnimationJet extends Transition {
    private static Jet jet;
    private boolean jetShootState;
    private boolean jetExplosionState;

    public AnimationJet(Jet jet) {
        AnimationJet.jet = jet;
        setCycleDuration(Duration.seconds(12));
        setCycleCount(-1);
    }

    private void attachKeyListeners() {
        jet.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                jet.isFacingRight = false;
                jet.setScaleX(-1);
            } else if (event.getCode() == KeyCode.D) {
                jet.isFacingRight = true;
                jet.setScaleX(1);
            } else if (event.getCode() == KeyCode.W) {
                jet.moveUp();
            } else if (event.getCode() == KeyCode.S) {
                jet.moveDown();
            } else if (event.getCode() == KeyCode.SPACE) {
                jet.shootNormalMissile();
            } else if (event.getCode() == KeyCode.T) {
                randomTank();
            } else if (event.getCode() == KeyCode.P) {
                GameLauncher.changeWave(Game.getWaveNumber());
            } else if (event.getCode() == KeyCode.R) {
                if (jet.atomicBombCount > 0) {
                    jet.shootAtomicMissile();
                    jet.atomicBombCount--;
                }
            } else if (event.getCode() == KeyCode.G) {
                jet.atomicBombCount++;
            } else if (event.getCode() == KeyCode.C) {
                if (jet.clusterCount > 0) {
                    jet.shootCluster();
                    jet.clusterCount--;
                }
            } else if (event.getCode() == KeyCode.CONTROL) {
                jet.clusterCount++;
            } else if (event.getCode() == KeyCode.H) {
                System.out.println(GameLauncherControllerView.staticHp.getProgress());
                if (GameLauncherControllerView.staticHp.getProgress() <= 0) {
                    GameLauncher.jetInitialize();
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                GameLauncher.pause();
            } else if (event.getCode() == KeyCode.TAB) {
                jetShootState = jet.isShootable;
                jet.isShootable = false;
                if (GameLauncherControllerView.staticFreeze.getProgress() >= 1) {
                    freeze();
                }
            }
        });
    }

    private void freeze() {
        for (Transition transition : Game.animations) {
            transition.pause();
        }
        GameLauncherControllerView.staticFreeze.setProgress(0);
        Timeline delayTimeline = new Timeline();
        delayTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(3), ae -> {
                    for (Transition transition : Game.animations) {
                        transition.playFromStart();
                    }
                    jet.isShootable = jetShootState;
                })
        );

        delayTimeline.setCycleCount(1);
        delayTimeline.play();
    }

    private void randomTank() {
        Tank tank = new Tank(GameLauncher.jet.game);
        Game.addTargets(tank);
        GameLauncher.pane.getChildren().add(tank);

        AnimationTank animationTank = new AnimationTank(tank);
        animationTank.play();
    }

    @Override
    protected void interpolate(double frac) {
        attachKeyListeners();
        jetExplosionState = jet.isExploded;
        double currentPositionX;

        if (jet.isFacingRight) {
            currentPositionX = jet.moveRight();
        } else {
            currentPositionX = jet.moveLeft();
        }
        double accuracy = (double) GameLauncher.targetsHit / GameLauncher.missilesShot;
        GameLauncherControllerView.staticAccuracy.setText("Accuracy : " + accuracy * 100 + "%");
        GameLauncherControllerView.staticAtomicCount.setText("Atomic Bombs : " + jet.atomicBombCount);
        GameLauncherControllerView.staticClusterCount.setText("Cluster Bombs : " + jet.clusterCount);

        if (jetExplosionState) {
            jet.explosion();
            jetExplosionState = false;
        }
        if (Game.getWaveNumber() == 3) {
            if (currentPositionX - Mig.mig.getX() < Mig.migRange && !Mig.mig.alreadyShot) {
                Mig.mig.shootMissile();
                Mig.mig.alreadyShot = true;
            }
        }

        jet.setX(currentPositionX);
    }
}
