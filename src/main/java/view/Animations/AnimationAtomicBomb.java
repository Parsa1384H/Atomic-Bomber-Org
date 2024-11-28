package view.Animations;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.AtomicBomb;
import model.Game;
import view.GameLauncher;

import java.util.ArrayList;


public class AnimationAtomicBomb extends Transition {
    public AtomicBomb atomicBomb;
    public ArrayList<Rectangle> targets = new ArrayList<>(Game.targets);
    public static AnimationAtomicBomb animationAtomicBomb;

    public AnimationAtomicBomb(AtomicBomb atomicBomb) {
        this.atomicBomb = atomicBomb;
        animationAtomicBomb = this;
        setCycleDuration(Duration.seconds(12));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double currentPositionX;
        double currentPositionY;

        if (atomicBomb.isFacingRight) {
            currentPositionX = atomicBomb.getX() + atomicBomb.speedX;
            currentPositionY = atomicBomb.getY() + (atomicBomb.speedY * atomicBomb.acc);
        } else {
            currentPositionX = atomicBomb.getX() - atomicBomb.speedX;
            currentPositionY = atomicBomb.getY() + (atomicBomb.speedY * atomicBomb.acc);
        }
        atomicBomb.acc += 0.1;

        if (currentPositionY >= 445) {
            AtomicBomb.explosion(atomicBomb);
            GameLauncher.pane.getChildren().remove(atomicBomb);
            for (Rectangle item : targets) {
                double distance = Math.abs(atomicBomb.getX() - item.getX());
                if (distance <= 150) {
                    GameLauncher.destroyAtomic(item);
                }
            }
        }
        atomicBomb.setX(currentPositionX);
        atomicBomb.setY(currentPositionY);
    }
}
