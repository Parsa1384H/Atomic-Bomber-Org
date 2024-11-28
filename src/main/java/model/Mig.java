package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animations.AnimationBullet;
import view.Animations.AnimationJet;
import view.Animations.AnimationMig;
import view.Animations.AnimationMissile;
import view.GameLauncher;

public class Mig extends Rectangle {
    public final double WIDTH = 105;
    public final double HEIGHT = 50;
    public final Game game;
    public AnimationMig animationMig;
    public static double migSpeed = 1;
    public static double migRange = 150;
    public static Mig mig;
    public boolean alreadyShot = false;

    public Mig(Game game) {
        super(105, 50);
        this.game = game;
        setX(-WIDTH);
        setY(game.HEIGHT * 1 / 4);
        mig = this;
        setFill(new ImagePattern(new Image(Jet.class.getResource("/Illustrations/mig.png").toExternalForm())));
        setScaleX(-1);
        animationMig = new AnimationMig(this);
        animationMig.play();
    }
    public void shootMissile() {
        MigMissile missile = new MigMissile(this, GameLauncher.jet);
        GameLauncher.pane.getChildren().add(missile);
        AnimationMissile animationMissile = new AnimationMissile(GameLauncher.jet,this,missile);
        animationMissile.play();
    }
}
