package com.example.soundlesscheck_in;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SetCompanyActivityTest {

    @Rule
    public ActivityTestRule<SetCompanyActivity> rule = new ActivityTestRule<>(SetCompanyActivity.class);

    @Test
    public void testOkBtn() {
        Espresso.onView(ViewMatchers.withId(R.id.btnGetCompanyInfo)).perform(click());
    }

    @Test
    public void testCancleBtn() {
        Espresso.onView(ViewMatchers.withId(R.id.btnCancleCompanyInfo)).perform(click());
    }

    @Test
    public void testTextViewNum() {
        Espresso.onView(ViewMatchers.withId(R.id.companyNumTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewName() {
        Espresso.onView(ViewMatchers.withId(R.id.companyNameTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewLoc() {
        Espresso.onView(ViewMatchers.withId(R.id.companyLocTextView)).check(matches(isDisplayed()));
    }
}