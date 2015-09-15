package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by hbtruong on 9/15/15.
 */
public abstract class Tweet extends Object implements Moodable {
    private String currentMood;
    private String text;
    private Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tweet(String text, String currentMood, Date date) {
        this.setText(text);
        this.setCurrentMood(currentMood);
        this.setDate(date);
    }

    public String getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(String currentMood){
        if (currentMood.length() <= 140) {
            this.currentMood = currentMood;
        } else {
            // throw like raise in Python
            throw new IllegalArgumentException("Mood can't be that long!"); // Can throw only RuntimeException, otherwise use try catch
            //throw new IOException("Tweets can't be that long!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tweet(String currentMood) {
        this.currentMood = currentMood;
        this.date = new Date();
    }

    public abstract Boolean isSad(); // Generic method for subclass
}
