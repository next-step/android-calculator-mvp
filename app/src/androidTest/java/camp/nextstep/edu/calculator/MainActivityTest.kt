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

    @Test
    fun `1이_입력되어_있었을때_2를_입력하면_12_가_되어야_한다`() {
        // when : 사용자가_피연산자_1과_2를_누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // then : 화면에_12가_보여야한다
        onView(withId(R.id.textView)).check(matches(withText("12")))
    }

    @Test
    fun `아무것도_입력되지_않은상태에서는_사칙연산_기호를_눌러도_연산이_없어야한다`() {
        // when : 사용자가_사칙연산_기호를_누르면
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())
        onView(withId(R.id.buttonMultiply)).perform(click())
        onView(withId(R.id.buttonDivide)).perform(click())

        // then : 화면 반응없음
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `아무것도_입력되지_않은상태에서는_지우기버튼을_눌러도_반응이_없어야한다`() {
        // when
        onView(withId(R.id.buttonDelete)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `숫자가_입력된_상태에서_사칙연산_기호를_누르면_숫자와_함께_기호가_보여야_한다`() {
        // when : 1이 입력된 상태에서 사용자가 사칙연산 기호를 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    fun `입력된_수식이_있을때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_연산자_또는_피연산자가_지워져야한다`() {
        // when : 32 + 1
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then
        // * 지우기
        // * 32 +
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32 +")))

        // * 지우기
        // * 32
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32")))

        // * 지우기
        // * 3
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3")))

        // * 지우기
        // *
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `유효한_표현식이_입력된_상태에서_이퀄버튼을_누르면_표현식의_결과를_보여야한다`() {
        // when : 1 + 2 =
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }
}
