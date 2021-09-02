package com.example.soundlesscheck_in;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SetCompanyActivityTest {

    @Rule
    public ActivityTestRule<SetCompanyActivity> rule = new ActivityTestRule<>(SetCompanyActivity.class);

    @Test
    public void testOkBtn() {
        onView(withId(R.id.btnGetCompanyInfo)).perform(click());
    }

    @Test
    public void testCancleBtn() {
        onView(withId(R.id.btnCancleCompanyInfo)).perform(click());
    }

    @Test
    public void testTextViewNum() {
        onView(withId(R.id.companyNumTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewName() {
        onView(withId(R.id.companyNameTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewLoc() {
        onView(withId(R.id.companyLocTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextNum() {
        onView(withId(R.id.companyNumEditText))
                .perform(typeText("123-45-67890"), closeSoftKeyboard());

        onView(withId(R.id.companyNumEditText))
                .check(matches(withText("123-45-67890")));
    }

    @Test
    public void testEditTextName() {
        onView(withId(R.id.companyNameEditText))
                .perform(typeText("Starbucks"), closeSoftKeyboard());

        onView(withId(R.id.companyNameEditText))
                .check(matches(withText("Starbucks")));
    }
}