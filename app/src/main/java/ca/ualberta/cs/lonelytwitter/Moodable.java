package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;

/**
 * Created by hbtruong on 9/15/15.
 */
public interface Moodable {
    public void setCurrentMood(String mood);
    public String getCurrentMood();
    public void setText(String text);
    public String getText();
}
