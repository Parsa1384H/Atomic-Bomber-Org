package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bunker extends Rectangle {
    public final double WIDTH = 60;
    public final double HEIGHT = 60;
    public final Game game;
    public Random rand = new Random();

    public Bunker(Game game) {
        super(60, 60);
        this.game = game;
        spawnAtRandom();

        setFill(new ImagePattern(new Image(Tank.class.getResource("/Illustrations/Bunker.png").toExternalForm())));
    }

    private void spawnAtRandom() {
        double maxX = game.WIDTH - WIDTH;
        double xPos = rand.nextDouble() * maxX;
        double yPos = 430;

        this.setX(xPos);
        this.setY(yPos);
    }
}