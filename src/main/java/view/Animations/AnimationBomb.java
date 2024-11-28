package view.Animations;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.AtomicBomb;
import model.Game;
import model.NormalMissile;
import model.Tank;
import view.GameLauncher;

import java.util.ArrayList;

public class AnimationBomb extends Transition {
    public final NormalMissile normalMissile;
    public ArrayList<Rectangle> targets = new ArrayList<>(Game.targets);
    public static AnimationBomb animationBomb;

    public AnimationBomb(NormalMissile normalMissile) {
        this.normalMissile = normalMissile;
        animationBomb = this;
        setCycleDuration(Duration.millis(100));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double currentPositionX;
        double currentPositionY;

        if (normalMissile.isFacingRight) {
            currentPositionX = normalMissile.getX() + normalMissile.speedX;
            currentPositionY = normalMissile.getY() + (normalMissile.speedY * normalMissile.acc);
        } else {
            currentPositionX = normalMissile.getX() - normalMissile.speedX;
            currentPositionY = normalMissile.getY() + (normalMissile.speedY * normalMissile.acc);
        }
        normalMissile.acc += 0.1;

        if (currentPositionY >= 445) {
            this.stop();
            AnimationExplosion animationExplosion = new AnimationExplosion(normalMissile);
            animationExplosion.play();

        }
        for (Rectangle item : targets) {
            if (normalMissile.isHit(item)) {
                GameLauncher.destroyTarget(item);
            }
        }

        normalMissile.setX(currentPositionX);
        normalMissile.setY(currentPositionY);
    }
}
