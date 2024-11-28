package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MigMissile extends Rectangle {
    public final double WIDTH = 25;
    public final double HEIGHT = 25;
    public final Mig mig;
    public final Jet jet;

    public MigMissile(Mig mig, Jet jet) {
        super(25, 25);
        this.mig = mig;
        this.jet = jet;
        setX(mig.getX() + mig.WIDTH);
        setY(mig.getY() + (mig.HEIGHT / 2));
        setFill(new ImagePattern(new Image(NormalMissile.class.getResource("/Illustrations/normalMissile.png").toExternalForm())));
    }

    public boolean isHit(Rectangle target) {
        return getBoundsInParent().intersects(target.getBoundsInParent());
    }
}
