package com.android.yurup.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Challenge")
public class Challenge extends ParseObject {
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_START = "start";
    public static final String KEY_END = "end";
    public static final String KEY_JOIN_CODE = "joinCode";
    public static final String KEY_HOST_STATUS = "hostStatus";
    public static final String KEY_IS_ACTIVE = "isActive";
    public static final String KEY_CREATOR = "creator";


    public Challenge() {
    }

    public Challenge(String theClassName) {
        super(theClassName);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setTitle(String title){
        put(KEY_TITLE, title);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public void setStart(String start){
        put(KEY_TITLE, start);
    }

    public String getStart() {
        return getString(KEY_START);
    }

    public String getEnd() {
        return getString(KEY_END);
    }

    public void setEnd(String end){
        put(KEY_END, end);
    }

    public String getJoinCode() {
        return getString(KEY_JOIN_CODE);
    }

    public void setJoinCode(String joinCode){
        put(KEY_JOIN_CODE, joinCode);
    }

    public void setCreator(ParseUser user){
        put(KEY_CREATOR, user);
    }

    public ParseUser getCreator() {
        return getParseUser(KEY_CREATOR);
    }

    public String getHostStatus() {
        return getString(KEY_HOST_STATUS);
    }

    public void setHostStatus(String hostStatus){
        put(KEY_HOST_STATUS, hostStatus);
    }

    public boolean getIsActive() {
        return getBoolean(KEY_IS_ACTIVE);
    }

    public void setIsActive(boolean isActive){
        put(KEY_IS_ACTIVE, isActive);
    }

//    public ParseFile getImage() {
//        return getParseFile(KEY_IMAGE);
//    }

//    public void setImage(ParseFile image){
//        put(KEY_IMAGE, image);
//    }

}
