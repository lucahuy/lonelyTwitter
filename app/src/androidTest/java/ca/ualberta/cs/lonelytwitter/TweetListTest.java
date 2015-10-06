package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hbtruong on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver{
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

    private Boolean weWereNotified;

    public void myNotify(MyObservable observable) {
        weWereNotified = Boolean.TRUE;
    }

    public void testObservable() {
        TweetList list = new TweetList();
        // needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test"); //
        weWereNotified = Boolean.FALSE;
        list.add(tweet);
        // we should have been notified here
        assertTrue(weWereNotified);
    }

    public void testModifyTweetInList() {
        TweetList list = new TweetList();
        // needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        weWereNotified = Boolean.FALSE;
        tweet.setText("test");
        list.add(tweet);
        // we should have been notified here
        assertTrue(weWereNotified);
    }
}