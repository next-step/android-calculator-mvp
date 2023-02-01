package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `사용자가_피연산자_0_버튼을_누르면_화면에_0_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 0 버튼을 누르면
        onView(withId(R.id.button0)).perform(ViewActions.click())

        // then : 화면에 0이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("0")))
    }

    @Test
    fun `사용자가_피연산자_1_버튼을_누르면_화면에_1_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 1 버튼을 누르면
        onView(withId(R.id.button1)).perform(ViewActions.click())

        // then : 화면에 1이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("1")))
    }

    @Test
    fun `사용자가_피연산자_2_버튼을_누르면_화면에_2_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 2 버튼을 누르면
        onView(withId(R.id.button2)).perform(ViewActions.click())

        // then : 화면에 2이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("2")))
    }

    @Test
    fun `사용자가_피연산자_3_버튼을_누르면_화면에_3_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 3 버튼을 누르면
        onView(withId(R.id.button3)).perform(ViewActions.click())

        // then : 화면에 3이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("3")))
    }

    @Test
    fun `사용자가_피연산자_4_버튼을_누르면_화면에_4_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 4 버튼을 누르면
        onView(withId(R.id.button4)).perform(ViewActions.click())

        // then : 화면에 4이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("4")))
    }

    @Test
    fun `사용자가_피연산자_5_버튼을_누르면_화면에_5_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 5 버튼을 누르면
        onView(withId(R.id.button5)).perform(ViewActions.click())

        // then : 화면에 5이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("5")))
    }

    @Test
    fun `사용자가_피연산자_6_버튼을_누르면_화면에_6_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 6 버튼을 누르면
        onView(withId(R.id.button6)).perform(ViewActions.click())

        // then : 화면에 6이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("6")))
    }

    @Test
    fun `사용자가_피연산자_7_버튼을_누르면_화면에_7_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 7 버튼을 누르면
        onView(withId(R.id.button7)).perform(ViewActions.click())

        // then : 화면에 7이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("7")))
    }

    @Test
    fun `사용자가_피연산자_8_버튼을_누르면_화면에_8_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 8 버튼을 누르면
        onView(withId(R.id.button8)).perform(ViewActions.click())

        // then : 화면에 8이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("8")))
    }

    @Test
    fun `사용자가_피연산자_9_버튼을_누르면_화면에_9_숫자가_화면에_보여야_한다`() {
        // when : 사용자가 피 연산자 9 버튼을 누르면
        onView(withId(R.id.button9)).perform(ViewActions.click())

        // then : 화면에 9이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(ViewMatchers.withText("9")))
    }
}