package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Tree extends Rectangle {
    public final double WIDTH = 20;
    public final double HEIGHT = 50;
    public final Game game;
    public Random rand = new Random();

    public Tree(Game game) {
        super(20, 50);
        this.game = game;
        spawnAtRandom();

        setFill(new ImagePattern(new Image(Tank.class.getResource("/Illustrations/tree.png").toExternalForm())));
    }

    private void spawnAtRandom() {
        double maxX = game.WIDTH - WIDTH;
        double xPos = rand.nextDouble() * maxX;
        double yPos = 430;

        this.setX(xPos);
        this.setY(yPos);
    }
}
