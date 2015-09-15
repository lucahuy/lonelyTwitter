package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by hbtruong on 9/15/15.
 */
public class SadTweet extends Tweet {

    public SadTweet(String text, String currentMood, Date date) {
        super(text, currentMood, date);
    }

    public SadTweet(String text) {
        super(text);
    }

    public Boolean isSad() {
        return true;
    }

    @Override
    public String getCurrentMood() {
        return super.getCurrentMood() + " | Sad";
    }
}
