package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Truck extends Rectangle {
    public final double WIDTH = 85;
    public final double HEIGHT = 35;
    public final Game game;
    public boolean isFacingRight;
    public static final double moveSpeed = 1.1;

    public Random rand = new Random();

    public Truck(Game game) {
        super(85, 35);
        this.game = game;
        isFacingRight = rand.nextBoolean();
        if (isFacingRight) {
            setScaleX(-1);
        } else {
            setScaleX(1);
        }
        spawnAtRandom();

        setFill(new ImagePattern(new Image(Tank.class.getResource("/Illustrations/militaryTruck.png").toExternalForm())));
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
