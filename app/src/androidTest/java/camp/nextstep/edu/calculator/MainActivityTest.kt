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
        // when : 계산기 버튼 0을 클릭하면
        onView(withId(R.id.button0)).perform(click())

        // then : 0이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("0")))
        // onView(withText("0")).check(matches(isDisplayed()))
    }

    @Test
    fun button1() {
        // when : 계산기 버튼 1을 클릭하면
        onView(withId(R.id.button1)).perform(click())

        // then : 1이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("1")))
        // onView(withText("1")).check(matches(isDisplayed()))
    }

    @Test
    fun button2() {
        // when : 계산기 버튼 2을 클릭하면
        onView(withId(R.id.button2)).perform(click())

        // then : 2이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("2")))
        // onView(withText("2")).check(matches(isDisplayed()))
    }

    @Test
    fun button3() {
        // when : 계산기 버튼 3을 클릭하면
        onView(withId(R.id.button3)).perform(click())

        // then : 3이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("3")))
        // onView(withText("3")).check(matches(isDisplayed()))
    }

    @Test
    fun button4() {
        // when : 계산기 버튼 4을 클릭하면
        onView(withId(R.id.button4)).perform(click())

        // then : 4이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("4")))
        // onView(withText("4")).check(matches(isDisplayed()))
    }

    @Test
    fun button5() {
        // when : 계산기 버튼 5을 클릭하면
        onView(withId(R.id.button5)).perform(click())

        // then : 5이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("5")))
        // onView(withText("5")).check(matches(isDisplayed()))
    }

    @Test
    fun button6() {
        // when : 계산기 버튼 6을 클릭하면
        onView(withId(R.id.button6)).perform(click())

        // then : 6이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("6")))
        // onView(withText("6")).check(matches(isDisplayed()))
    }

    @Test
    fun button7() {
        // when : 계산기 버튼 7을 클릭하면
        onView(withId(R.id.button7)).perform(click())

        // then : 7이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("7")))
        // onView(withText("7")).check(matches(isDisplayed()))
    }

    @Test
    fun button8() {
        // when : 계산기 버튼 8을 클릭하면
        onView(withId(R.id.button8)).perform(click())

        // then : 8이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("8")))
        // onView(withText("8")).check(matches(isDisplayed()))
    }

    @Test
    fun button9() {
        // when : 계산기 버튼 9을 클릭하면
        onView(withId(R.id.button9)).perform(click())

        // then : 9이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("9")))
        // onView(withText("9")).check(matches(isDisplayed()))
    }
}