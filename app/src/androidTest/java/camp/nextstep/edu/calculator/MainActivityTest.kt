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
        // when: 버튼 0을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: 1이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun button1() {
        // when: 버튼 1을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: 1이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun button2() {
        // when: 버튼 2을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: 2이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun button3() {
        // when: 버튼 3을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: 3이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun button4() {
        // when: 버튼 4을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: 4이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun button5() {
        // when: 버튼 5을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: 5이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button6() {
        // when: 버튼 6을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: 6이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun button7() {
        // when: 버튼 7을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: 7이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun button8() {
        // when: 버튼 8을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: 8이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun button9() {
        // when: 버튼 9을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: 9이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }
}