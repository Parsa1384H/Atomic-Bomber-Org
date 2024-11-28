package view.Animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Game;
import model.Truck;

public class AnimationTruck extends Transition {
    public final Truck truck;

    public AnimationTruck(Truck truck) {
        this.truck = truck;
        setCycleDuration(Duration.seconds(12));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (truck.isFacingRight) {
            truck.moveRight();
        } else {
            truck.moveLeft();
        }
    }
}
