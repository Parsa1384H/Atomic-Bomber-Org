package model;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import view.GameLauncher;

import java.util.ArrayList;

public class Game {
    public final double WIDTH = 900;
    public final double HEIGHT = 600;
    public static int waveNumber;
    public String username;
    public double score = 0;
    public GameLauncher gameLauncher;
    public static ArrayList<Rectangle> targets = new ArrayList<>();
    public static ArrayList<Transition> animations = new ArrayList<>();

    public Game(String username, GameLauncher gameLauncher) {
        this.username = username;
        this.gameLauncher = gameLauncher;
    }

    public static void addTargets(Rectangle target) {
        Game.targets.add(target);
    }

    public static void removeTargets(Rectangle target) {
        Game.targets.remove(target);
    }
    public static void setWaveNumber(int waveNumber) {
        Game.waveNumber = waveNumber;
    }
    public static int getWaveNumber() {
        return waveNumber;
    }
    public static void addAnimations(Transition transition) {
        Game.animations.add(transition);
    }

    public static void removeTargets(Transition transition) {
        Game.animations.remove(transition);
    }
}
