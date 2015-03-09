package com.codepath.apps.twitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by abgandhi on 3/8/15.
 */
public class User {
    private String name;
    private String screenName;
    private String profileUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public User(JSONObject object) {
        try {
            this.name = object.getString("name");
            this.screenName = object.getString("screen_name");
            this.profileUrl = object.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
