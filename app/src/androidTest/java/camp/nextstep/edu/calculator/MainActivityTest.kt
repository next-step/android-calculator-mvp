package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("0")))
    }

    @Test
    fun button1() {
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("1")))
    }

    @Test
    fun button2() {
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("2")))
    }

    @Test
    fun button3() {
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("3")))
    }

    @Test
    fun button4() {
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("4")))
    }

    @Test
    fun button5() {
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("5")))
    }

    @Test
    fun button6() {
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("6")))
    }

    @Test
    fun button7() {
        onView(withId(R.id.button7)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("7")))
    }

    @Test
    fun button8() {
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("8")))
    }

    @Test
    fun button9() {
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("9")))
    }

}