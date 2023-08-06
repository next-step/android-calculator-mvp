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
        // when: '0' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: '0'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun button1() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun button2() {
        // when: '2' 버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: '2'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun button3() {
        // when: '3' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: '3'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun button4() {
        // when: '4' 버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun button5() {
        // when: '5' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: '5'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button6() {
        // when: '6' 버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: '6'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun button7() {
        // when: '7' 버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: '7'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun button8() {
        // when: '8' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: '8'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun button9() {
        // when: '9' 버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: '9'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun click5_plus_1() {
        // when: '5 + 1' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then: '5 + 1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
    }

    @Test
    fun click8_and_9() {
        // when: '89' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.button9)).perform(click())

        // then: '89'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun click1_plus() {
        // when: '1 + ' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: '1 +'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    fun click1_minus() {
        // when: '1 - ' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())

        // then: '1 -'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1 -")))
    }

    @Test
    fun click1_remove() {
        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '1 -'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun click32_plus_1_remove() {
        // when: '32 + 1' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32 +'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32 +")))

        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32")))

        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '3'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))

        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: ''이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun click3_plus_2_equal() {
        // when: '3 + 2 =' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: '5'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun click0_and_4() {
        // when: '0 4' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun clickplus_first() {
        // when: 'plus' 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: ''이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }
}
