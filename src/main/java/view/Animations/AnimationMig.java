package view.Animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Jet;
import model.Mig;

public class AnimationMig extends Transition {
    public static Mig mig;

    public AnimationMig(Mig mig) {
        AnimationMig.mig = mig;
        setCycleDuration(Duration.seconds(12));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double currentXPosition;
        currentXPosition = mig.getX() + mig.migSpeed;
        if (currentXPosition >= mig.game.WIDTH) {
            currentXPosition -= mig.game.WIDTH + mig.WIDTH;
        }
        mig.setX(currentXPosition);
    }
}
