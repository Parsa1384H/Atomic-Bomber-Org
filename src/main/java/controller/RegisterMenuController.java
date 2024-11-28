package controller;

import model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterMenuController {
    public static void addUser(String username, String password) {
        new User(username, password, randomAvatarGenerator(), 0, 0, 0, 0);
    }

    private static String randomAvatarGenerator() {
        File directory = new File("src/main/resources/AVATARS/Random");

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            List<File> imageFiles = new ArrayList<>();

            for (File file : files) {
                if (file.isFile()) {
                    imageFiles.add(file);
                }
            }

            if (!imageFiles.isEmpty()) {
                Random random = new Random();
                File randomAvatarFile = imageFiles.get(random.nextInt(imageFiles.size()));

                return randomAvatarFile.toURI().toString();

            } else {
                System.out.println("No avatar images found in the directory.");
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
        return null;
    }
}
