package view.Animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game;
import view.GameLauncher;

import java.util.ArrayList;
import java.util.Objects;

public class AnimationExplosion extends Transition {
    public Rectangle target;

    public AnimationExplosion(Rectangle target) {
        this.target = target;
        Game.addAnimations(this);
        setCycleDuration(Duration.seconds(2));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
//        int number = (int) Math.floor(v * 19) + 1;
//        if (number == 19) {
//            this.stop();
//            Game.animations.remove(this);
//            GameLauncher.pane.getChildren().remove(target);
//        }
        int number = 1;
        if (v >= 0 && v < 0.05) number = 1;
        if (v >= 0.05 && v < 0.1) number = 2;
        if (v >= 0.1 && v < 0.15) number = 3;
        if (v >= 0.15 && v < 0.2) number = 4;
        if (v >= 0.2 && v < 0.25) number = 5;
        if (v >= 0.25 && v < 0.3) number = 6;
        if (v >= 0.3 && v < 0.35) number = 7;
        if (v >= 0.35 && v < 0.4) number = 8;
        if (v >= 0.4 && v < 0.45) number = 9;
        if (v >= 0.45 && v < 0.5) number = 10;
        if (v >= 0.5 && v < 0.55) number = 11;
        if (v >= 0.55 && v < 0.6) number = 12;
        if (v >= 0.6 && v < 0.65) number = 13;
        if (v >= 0.65 && v < 0.7) number = 14;
        if (v >= 0.7 && v < 0.75) number = 15;
        if (v >= 0.75 && v < 0.8) number = 16;
        if (v >= 0.8 && v < 0.85) number = 17;
        if (v >= 0.85 && v < 0.9) number = 18;
        if (v >= 0.9 && v < 1.0) number = 19;
        if (v == 1) number = 19;
        target.setFill(new ImagePattern(new Image
                (Objects.requireNonNull(GameLauncher.class.getResource("/Illustrations/Fire/Fire" + number + ".png")).toExternalForm())));
        setOnFinished(actionEvent -> {
            this.stop();
            Game.removeTargets(this);
            GameLauncher.pane.getChildren().remove(target);
        });
    }
}
