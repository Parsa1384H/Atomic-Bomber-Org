package view.Animations;

import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.GameLauncher;

public class AnimationBullet extends Transition {
    public final Jet jet;
    public final ShooterTank shooterTank;
    public final Bullet bullet;
    private final double destX;
    private final double destY;


    public AnimationBullet(Jet jet, ShooterTank shooterTank, Bullet bullet) {
        this.jet = jet;
        this.shooterTank = shooterTank;
        this.bullet = bullet;
        setCycleDuration(Duration.seconds(3));
        destX = jet.getX() + 100;
        destY = jet.getY();
        Game.addAnimations(this);
    }

    @Override
    protected void interpolate(double v) {
        double startX = shooterTank.getX();
        double startY = shooterTank.getY();

        double posX = startX + (destX - startX) * v;
        double posY = startY + (destY - startY) * v;
        if (bullet.isHit(jet)) {
            jet.isExploded = true;
            this.stop();
            GameLauncher.pane.getChildren().remove(bullet);
        }

        bullet.setX(posX);
        bullet.setY(posY);
        setOnFinished(actionEvent -> {
            Game.removeTargets(this);
            Game.removeTargets(bullet);
            GameLauncher.pane.getChildren().remove(bullet);
        });
    }

}
