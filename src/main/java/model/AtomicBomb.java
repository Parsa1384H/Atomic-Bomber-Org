package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animations.AnimationAtomicBomb;
import view.Animations.AnimationBomb;

public class AtomicBomb extends Rectangle {
    public Jet jet;
    public final Game game;
    public static AtomicBomb atomicBomb;
    public static double WIDTH = 15;
    public static double HEIGHT = 7;
    public final double speedX = 1.8;
    public final double speedY = 0.5;
    public double acc = 0.5;
    public boolean isFacingRight = false;

    public AtomicBomb(Game game, Jet jet) {
        super(25,25);
        this.game = game;
        this.jet = jet;
        atomicBomb = this;
        setX(jet.getX() + jet.WIDTH / 2);
        setY(jet.getY() + jet.HEIGHT - 15);
        if (jet.isFacingRight) {
            isFacingRight = true;
            setScaleX(-1);
        }
        setFill(new ImagePattern(new Image(NormalMissile.class.getResource("/Illustrations/atomicBomb.png").toExternalForm())));
        missileLaunch();
    }
    public void missileLaunch() {
        AnimationAtomicBomb animationAtomicBomb = new AnimationAtomicBomb(this);
        animationAtomicBomb.play();
    }
    public static void explosion(AtomicBomb atomicBomb) {

    }
}
