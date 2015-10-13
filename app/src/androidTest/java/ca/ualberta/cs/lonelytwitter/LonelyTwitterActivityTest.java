package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    EditText bodyText;
    Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditATweet() {
        // starts lonelyTwitter
        final LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        activity.getTweets().clear();

        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });


        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync(); // make sure UI thread finished

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());


        // Set up an ActivityMonitor

        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);


        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure UI thread finished


        // Validate that ReceiverActivity is started
        //TouchUtils.clickView(this, sendToReceiverButton);
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());


        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // end of test, make sure edit activity is close
        receiverActivity.finish();




        // test that tweet is being shown on screen is the tweet we click on
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
                assertEquals("hamburgers", ((Tweet) oldTweetsList.getItemAtPosition(0)).getText());
            }
        });
        getInstrumentation().waitForIdleSync();

        // edit the text
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
                Tweet editTweet = (Tweet) oldTweetsList.getItemAtPosition(0);
                editTweet.setText("donuts");
                assertEquals("donuts", editTweet.getText());
            }
        });
        getInstrumentation().waitForIdleSync();

        // save our edits
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
                Tweet editTweet = (Tweet) oldTweetsList.getItemAtPosition(0);
                editTweet.setText("dumplings");

                activity.saveInFile();
            }
        });
        getInstrumentation().waitForIdleSync();


        // assert edits are shown on screen
        activity.runOnUiThread(new Runnable() {
            public void run() {
                activity.loadFromFile();

                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
                assertEquals("dumplings", ((Tweet) oldTweetsList.getItemAtPosition(0)).getText());
            }
        });
        getInstrumentation().waitForIdleSync();



    }
}