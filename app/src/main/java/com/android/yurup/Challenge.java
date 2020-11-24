package com.android.yurup;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Challenge")
public class Challenge extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CREATOR = "creator";
    public static final String KEY_START_DATE = "start";
    public static final String KEY_END_DATE = "end";
    public static final String KEY_JOIN_CODE = "joinCode";
    public static final String KEY_HOST_STATUS = "hostStatus";
    public static final String KEY_IS_ACTIVE = "isActive";


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

    public void setStartDate(String startDate){
        put(KEY_TITLE, startDate);
    }

    public String getStartDate() {
        return getString(KEY_START_DATE);
    }

    public String getEndDate() {
        return getString(KEY_END_DATE);
    }

    public void setEndDate(String endDate){
        put(KEY_END_DATE, endDate);
    }

    public String getJoinCode() {
        return getString(KEY_JOIN_CODE);
    }

    public void setJoinCode(String joinCode){
        put(KEY_JOIN_CODE, joinCode);
    }

    public void setUser(ParseUser user){
        put(KEY_CREATOR, user);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_CREATOR);
    }

    public String getHostStatus() {
        return getString(KEY_HOST_STATUS);
    }

    public void setHostStatus(String hostStatus){
        put(KEY_HOST_STATUS, hostStatus);
    }

    public String getIsActive() {
        return getString(KEY_IS_ACTIVE);
    }

    public void setIsActive(String isActive){
        put(KEY_IS_ACTIVE, isActive);
    }

//    public ParseFile getImage() {
//        return getParseFile(KEY_IMAGE);
//    }

//    public void setImage(ParseFile image){
//        put(KEY_IMAGE, image);
//    }

}
