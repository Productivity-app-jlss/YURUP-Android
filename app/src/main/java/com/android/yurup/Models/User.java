package com.android.yurup.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PROFILE_IMAGE = "ProfileImage";

    public User() {
    }

    public User(String theClassName) {
        super(theClassName);
    }

    public String getUserName() {
        return getString(KEY_USERNAME);
    }

    public void setUserName(String userName){
        put(KEY_USERNAME, userName);
    }

    public String getPassword() {
        return getString(KEY_PASSWORD);
    }

    public void setPassword(String password){
        put(KEY_PASSWORD, password);
    }

    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    public void setKeyEmail(String email){
        put(KEY_EMAIL, email);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_PROFILE_IMAGE);
    }

    public void setImage(ParseFile image){
        put(KEY_PROFILE_IMAGE, image);
    }
}
