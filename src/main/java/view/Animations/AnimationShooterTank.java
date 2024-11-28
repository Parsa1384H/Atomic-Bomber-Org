package view.Animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.ShooterTank;
import view.GameLauncher;

import java.util.Random;

public class AnimationShooterTank extends Transition {
    public final ShooterTank shooterTank;

    public AnimationShooterTank(ShooterTank shooterTank) {
        this.shooterTank = shooterTank;
        setCycleDuration(Duration.seconds(12));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (shooterTank.isFacingRight) {
            shooterTank.moveRight();
        } else {
            shooterTank.moveLeft();
        }
        if (GameLauncher.jet.isShootable && !shooterTank.alreadyShot) {
            shooterTank.shootMissile();
            shooterTank.alreadyShot = true;
        }
    }
}
