package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Tower extends Rectangle {
    public final double WIDTH = 90;
    public final double HEIGHT = 160;
    public final Game game;
    public Random rand = new Random();

    public Tower(Game game) {
        super(90, 160);
        this.game = game;
        spawnAtRandom();

        setFill(new ImagePattern(new Image(Tank.class.getResource("/Illustrations/Tower(s).png").toExternalForm())));
    }

    private void spawnAtRandom() {
        double maxX = game.WIDTH - WIDTH;
        double xPos = rand.nextDouble() * maxX;
        double yPos = 350;

        this.setX(xPos);
        this.setY(yPos);
    }
}
