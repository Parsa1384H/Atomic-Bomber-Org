package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animations.AnimationBullet;
import view.GameLauncher;

import java.util.Random;

public class ShooterTank extends Rectangle {
    public final double WIDTH = 110;
    public final double HEIGHT = 75;
    public final Game game;
    public boolean isFacingRight;
    public static double moveSpeed = 0.8;
    public static double range = 120;
    public final Jet jet;
    public boolean alreadyShot = false;

    public Random rand = new Random();

    public ShooterTank(Game game, Jet jet) {
        super(110, 75);
        this.jet = jet;
        isFacingRight = rand.nextBoolean();
        if (isFacingRight) {
            setScaleX(-1);
        } else {
            setScaleX(1);
        }
        this.game = game;
        spawnAtRandom();

        setFill(new ImagePattern(new Image(ShooterTank.class.getResource("/Illustrations/shooterTank.png").toExternalForm())));
    }

    public void shootMissile() {
        Bullet bullet = new Bullet(this, game, jet);
        GameLauncher.pane.getChildren().add(bullet);
        AnimationBullet animationBullet = new AnimationBullet(jet,this,bullet);
        animationBullet.play();
    }

    private void spawnAtRandom() {
        double maxX = game.WIDTH - WIDTH;
        double xPos = rand.nextDouble() * maxX;
        double yPos = 410;

        this.setX(xPos);
        this.setY(yPos);
    }

    public void moveRight() {
        double currentPositionX = getX() + moveSpeed;
        if (currentPositionX >= game.WIDTH) {
            currentPositionX -= game.WIDTH + WIDTH;
        }
        setX(currentPositionX);
    }

    public void moveLeft() {
        double currentPositionX = getX() - moveSpeed;
        if (currentPositionX <= -WIDTH) {
            currentPositionX += game.WIDTH + WIDTH;
        }
        setX(currentPositionX);
    }
}
