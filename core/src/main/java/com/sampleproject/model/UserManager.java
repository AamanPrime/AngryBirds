package com.sampleproject.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class UserManager {
    private static final String credentials = "db/credentials.json";
    public static class User {
        public String username;
        public String password;
        public boolean soundStatus;
        public boolean musicStatus;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        public User() {

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
    }



    public void addUser(User user) {
        FileHandle fileHandle = Gdx.files.local(credentials);
        Array<User> users = new Array<User>();

        if(fileHandle.exists()) {
            Json json = new Json();
            users = json.fromJson(Array.class, fileHandle.readString());
        }

        if (users == null) {
            users = new Array<User>();
        }
        users.add(user);


        Json json = new Json();

        String jsonString = json.toJson(users);
        System.out.println(jsonString);
        FileHandle fileHandle2 = Gdx.files.local(credentials);
        fileHandle2.writeString(jsonString, false);

    }

    public User getUsers(String username) {
        FileHandle fileHandle = Gdx.files.local(credentials);
        if (fileHandle.exists()) {

            Json json = new Json();
            List<User> users = json.fromJson(List.class,fileHandle.readString());
            for (User u: users) {
                if (u.username.equalsIgnoreCase(username)) {
                    return u ;
                }
            }
        }
        return null;


    }



    public User loadUser() {
        FileHandle file = Gdx.files.internal("user.json");
        if (file.exists()) {
            Json json = new Json();
            return json.fromJson(User.class, file.readString());
        }
        return null; // No user saved
    }
}
