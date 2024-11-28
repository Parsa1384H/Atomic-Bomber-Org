package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animations.AnimationBomb;

public class NormalMissile extends Rectangle {
    public Jet jet;
    public final Game game;
    public static double WIDTH = 25;
    public static double HEIGHT = 25;
    public final double speedX = 1.8;
    public final double speedY = 0.5;
    public double acc = 0.5;
    public boolean isFacingRight = false;
    public static NormalMissile missile;

    public NormalMissile(Game game, Jet jet) {
        super(25, 25);
        this.game = game;
        this.jet = jet;
        missile = this;
        setX(jet.getX() + jet.WIDTH / 2);
        setY(jet.getY() + jet.HEIGHT - 15);
        if (jet.isFacingRight) {
            isFacingRight = true;
            setScaleX(-1);
        }
        setFill(new ImagePattern(new Image(NormalMissile.class.getResource("/Illustrations/normalMissile.png").toExternalForm())));
        missileLaunch();
    }
    public void missileLaunch() {
        AnimationBomb animationBomb = new AnimationBomb(this);
        animationBomb.play();
    }
    public boolean isHit(Rectangle target) {
        return getBoundsInParent().intersects(target.getBoundsInParent());
    }
}
