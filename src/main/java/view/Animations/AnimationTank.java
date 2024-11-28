package view.Animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Tank;

public class AnimationTank extends Transition {
    public final Tank tank;

    public AnimationTank(Tank tank) {
        this.tank = tank;
        setCycleDuration(Duration.seconds(12));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (tank.isFacingRight) {
            tank.moveRight();
        } else {
            tank.moveLeft();
        }
    }
}
