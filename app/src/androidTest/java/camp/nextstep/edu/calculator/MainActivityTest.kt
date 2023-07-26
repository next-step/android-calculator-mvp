package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun button0() {
        onView(withId(R.id.button0)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun button1() {
        onView(withId(R.id.button1)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun button2() {
        onView(withId(R.id.button2)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun button3() {
        onView(withId(R.id.button3)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun button4() {
        onView(withId(R.id.button4)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun button5() {
        onView(withId(R.id.button5)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button6() {
        onView(withId(R.id.button6)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun button7() {
        onView(withId(R.id.button7)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun button8() {
        onView(withId(R.id.button8)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun button9() {
        onView(withId(R.id.button9)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("9")))
    }
}
