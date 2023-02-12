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
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 사용자가_피연산자_0_버튼을_누르면_화면에_0이_보여야_한다() {
        // when: 사용자가_피연산자_0_버튼을_누르면
        onView(withId(R.id.button0)).perform(click())

        // then: 화면에_0이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    // 사용자가 피연산자 0~9 버튼을 누르면 화면에 해당 숫자가 보여야 한다.
    @Test
    fun 사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야_한다() {
        // when: 사용자가_피연산자_1_버튼을_누르면
        onView(withId(R.id.button1)).perform(click())

        // then: 화면에_1가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun 사용자가_피연산자_2_버튼을_누르면_화면에_2이_보여야_한다() {
        // when: 사용자가_피연산자_2_버튼을_누르면
        onView(withId(R.id.button2)).perform(click())

        // then: 화면에_2가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun 사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야_한다() {
        // when: 사용자가_피연산자_3_버튼을_누르면
        onView(withId(R.id.button3)).perform(click())

        // then: 화면에_3이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun 사용자가_피연산자_4_버튼을_누르면_화면에_4이_보여야_한다() {
        // when: 사용자가_피연산자_4_버튼을_누르면
        onView(withId(R.id.button4)).perform(click())

        // then: 화면에_4가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun 사용자가_피연산자_5_버튼을_누르면_화면에_5이_보여야_한다() {
        // when: 사용자가_피연산자_5_버튼을_누르면
        onView(withId(R.id.button5)).perform(click())

        // then: 화면에_5가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun 사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야_한다() {
        // when: 사용자가_피연산자_6_버튼을_누르면
        onView(withId(R.id.button6)).perform(click())

        // then: 화면에_6이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun 사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야_한다() {
        // when: 사용자가_피연산자_7_버튼을_누르면
        onView(withId(R.id.button7)).perform(click())

        // then: 화면에_7이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun 사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야_한다() {
        // when: 사용자가_피연산자_8_버튼을_누르면
        onView(withId(R.id.button8)).perform(click())

        // then: 화면에_8이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun 사용자가_피연산자_9_버튼을_누르면_화면에_9이_보여야_한다() {
        // when: 사용자가_피연산자_9_버튼을_누르면
        onView(withId(R.id.button9)).perform(click())

        // then: 화면에_9가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun `5_더하기가_입력되어_있을때_1_버튼을_누르면_5_더하기_1이_보여야_한다`() {
        // given
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.button1)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
    }

    @Test
    fun `8이_입력되어_있을때_9를_입력하면_89가_보여야_한다`() {
        // given
        onView(withId(R.id.button8)).perform(click())

        // when
        onView(withId(R.id.button9)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun `아무것도_입력이_되지_않았을때_연산자_버튼을_누르면_입력되지_않는다`() {
        // when
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `1이_입력되어_있을때_더하기_버튼을_누르면_1_더하기가_보여야_한다`() {
        // given
        onView(withId(R.id.button1)).perform(click())

        // when
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    fun `1_더하기가_입력되어_있을때_빼기_버튼을_누르면_1_빼기가_보여야_한다`() {
        // given
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.buttonMinus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1 -")))
    }

    @Test
    fun `아무것도_입력이_되지_않았을때_지우기_버튼을_누르면_화면에_아무런_변화가_없다`() {
        // when
        onView(withId(R.id.buttonDelete)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `32_더하기_1이_입력되어_있을때_지우기_버튼을_누르면_32_더하기가_보여야_한다`() {
        // given: 32 + 1
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: 지우기
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 32 +
        onView(withId(R.id.textView)).check(matches(withText("32 +")))
    }

    @Test
    fun `32_더하기가_입력되어_있을때_지우기_버튼을_누르면_32가_보여야_한다`() {
        // given: 32 +
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when: 지우기
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 32
        onView(withId(R.id.textView)).check(matches(withText("32")))
    }

    @Test
    fun `3_더하기_2가_입력되어_있을때_등호_버튼을_누르면_5가_보여야_한다`() {
        // given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun `3_더하기가_입력되어_있을때_등호_버튼을_누르면_화면에_아무런_변화가_없다`() {
        // given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("3 +")))
    }
}