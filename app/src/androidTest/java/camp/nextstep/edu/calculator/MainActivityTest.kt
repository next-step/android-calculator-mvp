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

    @get:Rule // Rule : 테스트가 잘 돌아가게 할 수 있도록 도와주는 장치
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `사용자가_피연산자_0_버튼을_누르면_화면에_0이_보여야한다`() {
        // when : 사용자가_피연산자_0_버튼을_누르면
        onView(withId(R.id.button0)).perform(click())

        // then : 화면에_0이_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun `사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야한다`() {
        // when : 사용자가_피연산자_1_버튼을_누르면
        onView(withId(R.id.button1)).perform(click())

        // then : 화면에_1이_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun `사용자가_피연산자_2_버튼을_누르면_화면에_2이_보여야한다`() {
        // when : 사용자가_피연산자_2_버튼을_누르면
        onView(withId(R.id.button2)).perform(click())

        // then : 화면에_2가_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun `사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야한다`() {
        // when : 사용자가_피연산자_3_버튼을_누르면
        onView(withId(R.id.button3)).perform(click())

        // then : 화면에_3이_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun `사용자가_피연산자_4_버튼을_누르면_화면에_4이_보여야한다`() {
        // when : 사용자가_피연산자_4_버튼을_누르면
        onView(withId(R.id.button4)).perform(click())

        // then : 화면에_4가_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun `사용자가_피연산자_5_버튼을_누르면_화면에_5이_보여야한다`() {
        // when : 사용자가_피연산자_5_버튼을_누르면
        onView(withId(R.id.button5)).perform(click())

        // then : 화면에_5가_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun `사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야한다`() {
        // when : 사용자가_피연산자_6_버튼을_누르면
        onView(withId(R.id.button6)).perform(click())

        // then : 화면에_6이_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun `사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야한다`() {
        // when : 사용자가_피연산자_7_버튼을_누르면
        onView(withId(R.id.button7)).perform(click())

        // then : 화면에_7이_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun `사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야한다`() {
        // when : 사용자가_피연산자_8_버튼을_누르면
        onView(withId(R.id.button8)).perform(click())

        // then : 화면에_8이_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun `사용자가_피연산자_9_버튼을_누르면_화면에_9이_보여야한다`() {
        // when : 사용자가_피연산자_9_버튼을_누르면
        onView(withId(R.id.button9)).perform(click())

        // then : 화면에_9가_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }
}
