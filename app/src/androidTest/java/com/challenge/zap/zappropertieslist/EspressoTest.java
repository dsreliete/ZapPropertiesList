package com.challenge.zap.zappropertieslist;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.challenge.zap.zappropertieslist.property.MainActivity;
import com.challenge.zap.zappropertieslist.propertyDetail.PropertyDetailActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by eliete on 9/1/16.
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity>
            activityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.no_item)).check((matches(not(isDisplayed()))));
        onView(withId(R.id.progressBar)).check((matches(not(isDisplayed()))));
    }

    @Test
    public void checkProperyViewItem_isDisplayed(){
        onView(allOf(withId(R.id.category_text), isDisplayed()));
        onView(allOf(withId(R.id.prop_image), isDisplayed()));
        onView(allOf(withId(R.id.location_text), isDisplayed()));
        onView(allOf(withId(R.id.offer_text), isDisplayed()));
    }

    @Test
    public void checkContentOfPropertyViewItem_isDisplayed(){
        onView(allOf(withId(R.id.space_text), hasSibling(withText("166 metros quadrado de área total")))).
                check(matches(isDisplayed()));

    }

    @Test
    public void whenTouchOnItemList_shouldStartPropertyDetailActivity_withExtra() {
        activityRule.launchActivity(new Intent());
        Intents.init();
        Matcher<Intent> matcher = allOf(
                hasComponent(PropertyDetailActivity.class.getName()),
                hasExtraWithKey(MainActivity.EXTRA_CODE),
                hasExtraWithKey(MainActivity.EXTRA_URL)
        );

        Instrumentation.ActivityResult
                result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(matcher).respondWith(result);

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));

        intended(matcher);
        Intents.release();
    }
}
