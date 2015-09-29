package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hbtruong on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testHoldsStuff() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.count(), 2);
    }

    public void testDuplicate() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        list.addTweet(new NormalTweet("test"));
    }

    public void testSort() {
        TweetList list = new TweetList();

        list.add(new NormalTweet("A"));
        list.add(new NormalTweet("C"));
        list.add(new NormalTweet("B"));

        list.getTweets();
    }

    public void testEqual() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        list.addTweet(new NormalTweet("test2"));
        System.out.println("check equal: " + list.hasTweet(tweet));
    }
}