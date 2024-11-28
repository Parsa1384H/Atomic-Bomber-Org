package model;


import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String avatarPath;
    private static User loggedInUser;
    private double score;
    private double accuracy;
    private int kills;
    private int lastWave;

    private static ArrayList<User> users = new ArrayList<>();

    public User(String username, String password, String avatarPath, double score, double accuracy, int kills, int lastWave) {
        this.username = username;
        this.password = password;
        this.avatarPath = avatarPath;
        this.score = score;
        this.accuracy = accuracy;
        this.kills = kills;
        this.lastWave = lastWave;
        setKills(0);
        setScore(0);
        setAccuracy(0);
        addUser(this);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        ArrayList<User> copy = users;
        for (User u : users) {
            if (u.equals(user)) {
                copy.remove(u);
                break;
            }
        }
        User.setUsers(copy);
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLastWave() {
        return lastWave;
    }

    public void setLastWave(int lastWave) {
        this.lastWave = lastWave;
    }
}
