package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.GameLauncher;

import java.util.Random;

public class Tank extends Rectangle {
    public final double WIDTH = 70;
    public final double HEIGHT = 35;
    public final Game game;
    public boolean isFacingRight;
    public static double moveSpeed = 0.8;

    public Random rand = new Random();

    public Tank(Game game) {
        super(70, 35);
        isFacingRight = rand.nextBoolean();
        if (isFacingRight) {
            setScaleX(-1);
        } else {
            setScaleX(1);
        }
        this.game = game;
        spawnAtRandom();

        setFill(new ImagePattern(new Image(Tank.class.getResource("/Illustrations/normalTank.png").toExternalForm())));
    }

    private void spawnAtRandom() {
        double maxX = game.WIDTH - WIDTH;
        double xPos = rand.nextDouble() * maxX;
        double yPos = 450;

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
