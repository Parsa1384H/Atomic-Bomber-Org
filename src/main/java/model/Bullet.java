package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animations.AnimationBullet;

public class Bullet extends Rectangle {
    public final double WIDTH = 25;
    public final double HEIGHT = 25;
    public final Game game;
    public final Jet jet;


    public Bullet(ShooterTank shooterTank, Game game, Jet jet) {
        super(25, 25);
        this.game = game;
        this.jet = jet;
        setX(shooterTank.getX());
        setY(shooterTank.getY());
        setFill(new ImagePattern(new Image(NormalMissile.class.getResource("/Illustrations/normalMissile.png").toExternalForm())));
    }
    public boolean isHit(Rectangle target) {
        return getBoundsInParent().intersects(target.getBoundsInParent());
    }
}
