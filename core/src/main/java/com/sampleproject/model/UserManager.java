package com.sampleproject.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String credentials = "credentials.ser";

    public static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        private String username;
        private String password;
        private boolean soundStatus;
        private boolean musicStatus;
        private boolean difficulty; // 1 -> hard 0->easy
        private int level1score = 0;
        private int level2score = 0;
        private int level3score = 0;
        private int coins;
        private String catatype = "normal";

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public User() {}

        public String getUsername() {
            return username;
        }

        public int getCoins() {
            return coins;
        }

        public void setCoins(int coins) {
            this.coins = coins;
        }

        public boolean isDifficulty() {
            return difficulty;
        }

        public void setDifficulty(boolean difficulty) {
            this.difficulty = difficulty;
        }

        public String getCatatype() {
            return catatype;
        }

        public void setCatatype(String catatype) {
            this.catatype = catatype;
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

        public boolean isSoundStatus() {
            return soundStatus;
        }

        public void setSoundStatus(boolean soundStatus) {
            this.soundStatus = soundStatus;
        }

        public boolean isMusicStatus() {
            return musicStatus;
        }

        public void setMusicStatus(boolean musicStatus) {
            this.musicStatus = musicStatus;
        }

        public int getLevel1score() {
            return level1score;
        }

        public void setLevel1score(int level1score) {
            this.level1score = level1score;
        }

        public int getLevel2score() {
            return level2score;
        }

        public void setLevel2score(int level2score) {
            this.level2score = level2score;
        }

        public int getLevel3score() {
            return level3score;
        }

        public void setLevel3score(int level3score) {
            this.level3score = level3score;
        }

    }

    public void addUser(User user) {
        List<User> users = loadUsers();
        users.add(user);
        saveUsers(users);
    }

    public void saveUserData(List<User> userData) {
        saveUsers(userData);
    }

    public User getUsers(String username) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    public void changeUserName(String username, String newUsername) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                u.username = newUsername;
            }
        }
        saveUsers(users);
    }

    public void changeCatapult(String username, String catapult) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                u.setCatatype(catapult);
            }
        }
        saveUsers(users);
    }

    public String getCatapult(String username) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                return u.getCatatype();
            }
        }
        return "normal";
    }

    public boolean getDifficulty(String username) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                return u.isDifficulty();
            }
        }
        return false;
    }

    public void updateUserScore(int level, int score, String username) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                switch (level) {
                    case 1:
                        u.level1score = score;
                        break;
                    case 2:
                        u.level2score = score;
                        break;
                    case 3:
                        u.level3score = score;
                        break;
                }
                break;
            }
        }
        saveUsers(users);
    }

    public void addCoins(int coins, String username) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                u.setCoins(u.getCoins() + coins);
            }
        }
        saveUsers(users);
    }

    public int getUserScore(int level, String username) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                switch (level) {
                    case 1:
                        return u.level1score;
                    case 2:
                        return u.level2score;
                    case 3:
                        return u.level3score;
                }
            }
        }
        return 0;
    }

    public void setSetting(String what, String username) {
        what = what.toLowerCase();
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                switch (what) {
                    case "sound":
                        System.out.println("sound");
                        u.setSoundStatus(!u.soundStatus);
                        break;
                    case "music":
                        u.setMusicStatus(!u.musicStatus);
                        break;
                    case "difficulty":
                        u.setDifficulty(!u.difficulty);
                        break;
                }
            }
        }
        saveUsers(users);
    }

    public boolean getSettings(String what, String username) {
        what = what.toLowerCase();
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                switch (what) {
                    case "sound":
                        return u.soundStatus;
                    case "music":
                        return u.musicStatus;
                    case "difficulty":
                        return u.isDifficulty();
                }
            }
        }
        return false;
    }

    private List<User> loadUsers() {
        try {
            File file = new File(credentials);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                return (List<User>) ois.readObject();
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveUsers(List<User> users) {
        try {
            File file = new File(credentials);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
