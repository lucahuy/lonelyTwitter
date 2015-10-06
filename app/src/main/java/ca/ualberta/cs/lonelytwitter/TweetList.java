package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hbtruong on 9/29/15.
 */
// want to make observable
public class TweetList implements MyObservable, MyObserver {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
        tweet.addObserver(this);
        notifyAllObservers();
    }

    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int count() {
        return tweets.size();
    }

    public void addTweet(Tweet tweet) {

        for(int i=0; i<tweets.size(); i++) {
            if (tweets.get(i).getText().equals(tweet.getText())) {
                throw new IllegalArgumentException();
            }
        }

        tweets.add(tweet);
    }

    public void getTweets() {
        // from Stackoverflow
        Collections.sort(tweets, new Comparator<Tweet>() {
            public int compare(Tweet tweet1, Tweet tweet2) {
                return tweet1.getText().compareTo(tweet2.getText());
            }
        });

        for(int i=0; i<tweets.size(); i++) {
            System.out.println(tweets.get(i).getText());
        }
    }

    public Boolean hasTweet(Tweet tweet) {
        for(int i=0; i<tweets.size(); i++) {
            if (tweets.get(i) == tweet) {
                return true;
            }
        }

        return false;
    }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    // in interface
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    // in interface
    public void myNotify(MyObservable observable) {
        notifyAllObservers();
    }
}
