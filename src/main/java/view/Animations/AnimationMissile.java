package view.Animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.*;
import view.GameLauncher;

public class AnimationMissile extends Transition {
    public final Jet jet;
    public final Mig mig;
    public final MigMissile missile;
    private final double destX;
    private final double destY;

    public AnimationMissile(Jet jet, Mig mig, MigMissile missile) {
        this.jet = jet;
        this.mig = mig;
        this.missile = missile;
        destX = jet.getX() + 100;
        destY = jet.getY();
        Game.addAnimations(this);
        setCycleDuration(Duration.seconds(3));
    }

    @Override
    protected void interpolate(double v) {
        double startX = mig.getX();
        double startY = mig.getY();

        double posX = startX + (destX - startX) * v;
        double posY = startY + (destY - startY) * v;
        if (missile.isHit(jet)) {
            jet.isExploded = true;
            this.stop();
            GameLauncher.pane.getChildren().remove(missile);
        }

        missile.setX(posX);
        missile.setY(posY);
        setOnFinished(actionEvent -> {
            Game.removeTargets(this);
            Game.removeTargets(missile);
            GameLauncher.pane.getChildren().remove(missile);
        });

    }
}
