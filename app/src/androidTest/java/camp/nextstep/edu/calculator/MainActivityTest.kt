package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun button0() {
        //when 0 click
        onView(withId(R.id.button0)).perform(click())

        //then 0
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun button1() {
        //when 1 click
        onView(withId(R.id.button1)).perform(click())

        //then 1
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun button2() {
        //when 2 click
        onView(withId(R.id.button2)).perform(click())

        //then 2
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun button3() {
        //when 3 click
        onView(withId(R.id.button3)).perform(click())

        //then 3
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun button4() {
        //when 4 click
        onView(withId(R.id.button4)).perform(click())

        //then 4
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun button5() {
        //when 5 click
        onView(withId(R.id.button5)).perform(click())

        //then 5
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button6() {
        //when 6 click
        onView(withId(R.id.button6)).perform(click())

        //then 6
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun button7() {
        //when 7 click
        onView(withId(R.id.button7)).perform(click())

        //then 7
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun button8() {
        //when 8 click
        onView(withId(R.id.button8)).perform(click())

        //then 8
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun button9() {
        //when 9 click
        onView(withId(R.id.button9)).perform(click())

        //then 9
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

}