package com.codepath.apps.twitterclient.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/*
 * This is a temporary, sample model that demonstrates the basic structure
 * of a SQLite persisted Model object. Check out the ActiveAndroid wiki for more details:
 * https://github.com/pardom/ActiveAndroid/wiki/Creating-your-database-model
 * 
 */
@Table(name = "items")
public class Tweet extends Model {
	// Define table fields
	@Column(name = "name")
    private User user;
	private String text;
    private String createdAt;
    private int retweetCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    // Parse model from JSON
	public Tweet(JSONObject object){
		super();

		try {
			this.text = object.getString("text");
            this.createdAt = object.getString("created_at");
            this.retweetCount = object.getInt("retweet_count");
            this.user = new User(object.getJSONObject("user"));

           // this.createdAt = DateUtils.getRelativeTimeSpanString(temp * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

        } catch (JSONException e) {
			e.printStackTrace();
		}
	}

    public static ArrayList<Tweet> parseJsonArray(JSONArray ajson) {
        ArrayList<Tweet> array = new ArrayList<Tweet>();
        for (int i = 0; i < ajson.length(); i++) {
            Tweet tweet = null;
            try {
                tweet = new Tweet((JSONObject)ajson.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.add(tweet);
        }
        return array;
    }


	// Record Finders
	public static Tweet byId(long id) {
		return new Select().from(Tweet.class).where("id = ?", id).executeSingle();
	}

	public static List<Tweet> recentItems() {
		return new Select().from(Tweet.class).orderBy("id DESC").limit("300").execute();
	}
}
