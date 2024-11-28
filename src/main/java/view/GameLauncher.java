package view;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import view.Animations.*;

import java.io.File;
import java.net.URL;
import java.util.Random;

public class GameLauncher extends Application {
    public static MediaPlayer inGameMusic;
    public static Game game = null;
    public static Jet jet;
    public static Pane pane;
    private static boolean isMusicPaused = false;
    public static int targetsHit = 0;
    public static int missilesShot = 0;
    public static Stage gameStage;


    @Override
    public void start(Stage stage) throws Exception {
        musicPlay();
        gameStage = stage;
        URL url = GameLauncher.class.getResource("/FXML/GameMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        pane = loader.load(url);
        Scene scene = new Scene(pane);

        String imagePath = "file:src/main/resources/BackGrounds/BG-GameLauncher.jpg";
        setBackGround(imagePath, pane);

        PaneSetSize(pane);

        jetInitialize();

        firstWave();

        String iconPath = "file:src/main/resources/BackGrounds/icon.jpg";
        stage.getIcons().add(new Image(iconPath));

        stage.setScene(scene);
        stage.setTitle("Game");
        stage.show();
        jet.requestFocus();
    }

    public GameLauncher(String username, Pane pane) {
        game = new Game(username, this);
        GameLauncher.pane = pane;
    }

    public static void destroyAtomic(Rectangle item) {
        atomicExplosionSoundEffect();
        pane.getChildren().remove(AtomicBomb.atomicBomb);
        AnimationAtomicBomb.animationAtomicBomb.stop();
        explosionEffects(item);
    }

    public static void destroyTarget(Rectangle item) {
        explosionSoundEffect();
        pane.getChildren().remove(NormalMissile.missile);
        AnimationBomb.animationBomb.stop();
        explosionEffects(item);
    }

    private static void explosionEffects(Rectangle item) {
        if (User.getLoggedInUser() != null && item.getWidth() > 20) {
            User.getLoggedInUser().setKills(User.getLoggedInUser().getKills() + 1);
            System.out.println(User.getLoggedInUser().getKills());
        }
        if (item.getWidth() > 20) {
            GameLauncherControllerView.staticFreeze.setProgress(
                    GameLauncherControllerView.staticFreeze.getProgress() + 0.2
            );
            targetsHit++;
            GameLauncherControllerView.staticKills.setText("Kills : " + targetsHit);
        }
        if (item.getWidth() == 90) {
            jet.atomicBombCount++;
        }
        if (item.getWidth() == 60) {
            jet.clusterCount++;
        }
        AnimationExplosion animationExplosion = new AnimationExplosion(item);
        animationExplosion.play();

        Game.removeTargets(item);

        if (Game.targets.isEmpty()) {
            changeWave(Game.getWaveNumber());
        }
    }

    private static void atomicExplosionSoundEffect() {
        String soundEffectPath = "/SoundEffects/large-explosion-100420.mp3";
        MediaPlayer soundEffect = new MediaPlayer(new Media(GameLauncher.class.getResource(soundEffectPath).toExternalForm()));
        soundEffect.play();
    }

    private static void explosionSoundEffect() {
        String soundEffectPath = "/SoundEffects/medium-explosion-40472.mp3";
        MediaPlayer soundEffect = new MediaPlayer(new Media(GameLauncher.class.getResource(soundEffectPath).toExternalForm()));
        soundEffect.play();
    }

    public static void changeWave(int waveNumber) {
        if (waveNumber == 1) {
            secondWave();
        } else if (waveNumber == 2) {
            thirdWave();
        } else {
            Game.setWaveNumber(4);
            endGame();
        }
    }

    private static void eachWave() {
        for (int i = 0; i < 3; i++) {
            tankInitialize();
            truckInitialize();
        }
        for (int i = 0; i < 4; i++) {
            treeInitialize();
        }
        buildingInitialize();
        bunkerInitialize();
    }

    private static void firstWave() {
        Game.setWaveNumber(1);
        GameLauncherControllerView.staticWaveNumber.setText("Wave number 1");
        eachWave();
    }

    private static void secondWave() {
        Game.setWaveNumber(2);
        GameLauncherControllerView.staticWaveNumber.setText("Wave number 2");
        eachWave();
        shooterTankInitialize();
    }

    private static void thirdWave() {
        Game.setWaveNumber(3);
        GameLauncherControllerView.staticWaveNumber.setText("Wave number 3");
        eachWave();
        shooterTankInitialize();
        migInitialize();
    }

    private static void endGame() {
        jet.animationJet.stop();
        Stage endGame = new Stage();
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);
        endGame.setScene(scene);

        pane.setMinWidth(200);
        pane.setMinHeight(200);
        pane.setMaxWidth(200);
        pane.setMaxHeight(200);

        endGame.centerOnScreen();
        endGameInformation(pane, endGame);

        if (User.getLoggedInUser() != null) {
            User.getLoggedInUser().setKills(
                    User.getLoggedInUser().getKills() + targetsHit);
            User.getLoggedInUser().setAccuracy(
                    User.getLoggedInUser().getAccuracy() + (double) targetsHit / missilesShot * 100);
            User.getLoggedInUser().setScore(
                    User.getLoggedInUser().getScore() + targetsHit * 10);
        }
        endGame.show();
    }

    private static void endGameInformation(StackPane pane, Stage endGame) {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);

        Label winLose = new Label();
        if (Game.getWaveNumber() == 4) {
            winLose.setText("Victory!");
        } else {
            winLose.setText("You Lost!");
        }
        double accuarcy = (double) targetsHit / missilesShot * 100;
        Label lastWave = new Label("your last wave : " + Game.getWaveNumber());
        Label kills = new Label("your kills : " + targetsHit);
        Label accuracy = new Label("your accuracy : " + accuarcy);
        Button back = new Button("Back");

        vbox.getChildren().addAll(winLose, lastWave, kills, accuracy, back);
        pane.getChildren().add(vbox);

        back.setOnAction(e -> back(endGame));
    }

    private static void back(Stage endGame) {
        if (User.getLoggedInUser() != null) {
            MainMenu mainMenu = new MainMenu();
            try {
                mainMenu.start(RegisterMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            GuestMenu guestMenu = new GuestMenu();
            try {
                guestMenu.start(RegisterMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        endGame.close();
    }

    private static void shooterTankInitialize() {
        ShooterTank shooterTank = new ShooterTank(game, jet);
        Game.addTargets(shooterTank);
        pane.getChildren().add(shooterTank);

        AnimationShooterTank animationShooterTank = new AnimationShooterTank(shooterTank);
        Game.addAnimations(animationShooterTank);
        animationShooterTank.play();
    }

    private static void migInitialize() {
        Mig mig = new Mig(game);
        Game.addTargets(mig);
        pane.getChildren().add(mig);
    }

    public static void buildingInitialize() {
        Tower tower = new Tower(game);
        Game.addTargets(tower);
        pane.getChildren().add(tower);
    }

    private static void bunkerInitialize() {
        Bunker bunker = new Bunker(game);
        Game.addTargets(bunker);
        pane.getChildren().add(bunker);
    }

    private static void treeInitialize() {
        Tree tree = new Tree(game);
        Game.addTargets(tree);
        pane.getChildren().add(tree);
    }

    private static void truckInitialize() {
        Truck truck = new Truck(game);
        Game.addTargets(truck);
        pane.getChildren().add(truck);

        AnimationTruck animationTruck = new AnimationTruck(truck);
        Game.addAnimations(animationTruck);
        animationTruck.play();
    }

    public static void tankInitialize() {
        Tank tank = new Tank(game);
        Game.addTargets(tank);
        pane.getChildren().add(tank);

        AnimationTank animationTank = new AnimationTank(tank);
        Game.addAnimations(animationTank);
        animationTank.play();
    }

    public static void jetInitialize() {
        jet = new Jet(game);
        pane.getChildren().add(jet);

        AnimationJet animationJet = new AnimationJet(jet);
        jet.animationJet = animationJet;
        animationJet.play();
        jet.requestFocus();
    }


    private void PaneSetSize(Pane pane) {
        pane.setMaxHeight(game.HEIGHT);
        pane.setMinHeight(game.HEIGHT);
        pane.setMinWidth(game.WIDTH);
        pane.setMaxWidth(game.WIDTH);
    }

    private static void musicPlay() {
        RegisterMenu.allMenusSoundTrack.pause();
        RegisterMenu.isMusicPlaying = false;

        File musicDir = new File("src/main/resources/SoundEffects/SoundTracks/GameSoundTracks"); // Change path accordingly
        File[] files = musicDir.listFiles((dir, name) -> name.endsWith(".mp3")); // Filter for MP3 files
        if (files != null && files.length > 0) {
            File fileToPlay = files[new Random().nextInt(files.length)];
            Media hit = new Media(fileToPlay.toURI().toString());
            inGameMusic = new MediaPlayer(hit);
            inGameMusic.setCycleCount(MediaPlayer.INDEFINITE);
            inGameMusic.play();
        }
    }

    private static void setBackGround(String imagePath, Pane root) {
        Image image = new Image(imagePath);
        BackgroundImage background = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
    }

    public static void pause() {
        for (Transition animation : Game.animations) {
            animation.pause();
        }
        jet.animationJet.pause();
        HBox hbox = new HBox();
        pane.getChildren().add(hbox);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        buttons(hbox);
    }

    private static void buttons(HBox hBox) {
        Button exit = new Button("Exit");
        Button resume = new Button("Resume");
        Button pauseMusic = new Button("PauseMusic");
        Button changeMusic = new Button("ChangeMusic");
        hBox.getChildren().addAll(exit, resume, pauseMusic, changeMusic);

        exit.setOnAction(e -> exit());
        resume.setOnAction(e -> resume(hBox));
        pauseMusic.setOnAction(e -> pauseMusic());
        changeMusic.setOnAction(e -> changeMusic());
    }

    private static void changeMusic() {
        FileChooser musicChanger = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac");
        musicChanger.getExtensionFilters().add(filter);

        File music = musicChanger.showOpenDialog(new Stage());
        if (music != null) {
            inGameMusic.stop();
            inGameMusic = null;
            Media media = new Media(music.toURI().toString());
            inGameMusic = new MediaPlayer(media);
            inGameMusic.play();
            inGameMusic.setCycleCount(-1);
        }
    }

    private static void pauseMusic() {
        if (!isMusicPaused) {
            inGameMusic.pause();
            isMusicPaused = true;
        } else {
            inGameMusic.play();
            isMusicPaused = false;
        }
    }

    private static void resume(HBox hBox) {
        pane.getChildren().remove(hBox);
        for (Transition animation : Game.animations) {
            animation.play();
        }
        jet.animationJet.play();
        jet.requestFocus();
    }

    private static void exit() {

        endGame();

        if (User.getLoggedInUser() == null) {
            GuestMenu menu = new GuestMenu();
            try {
                menu.start(RegisterMenu.stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            MainMenu menu = new MainMenu();
            try {
                menu.start(RegisterMenu.stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
