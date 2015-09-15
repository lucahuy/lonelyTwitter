package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by hbtruong on 9/15/15.
 */
public class HappyTweet extends Tweet {
    public HappyTweet(String text, String mood, Date date) {
        super(text, mood, date);
    }

    public HappyTweet(String text) {
        super(text);
    }

    public Boolean isSad() {
        return false;
    }

    @Override
    public String getCurrentMood() {
        return super.getCurrentMood() + " | Happy";
    }
}
