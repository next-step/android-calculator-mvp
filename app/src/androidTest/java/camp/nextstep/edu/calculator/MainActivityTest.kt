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

    // 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.
    @Test
    fun 사용자가_피연산자_0_버튼을_누르면_화면에_0이_보여야_한다() {
        // when : 사용자가_피연산자_0_버튼을_누르면
        onView(withId(R.id.button0)).perform(click())

        // then : 화면에_0이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun 사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야_한다() {
        // when : 사용자가_피연산자_1_버튼을_누르면
        onView(withId(R.id.button1)).perform(click())

        // then : 화면에_1이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun 사용자가_피연산자_2_버튼을_누르면_화면에_2이_보여야_한다() {
        // when : 사용자가_피연산자_2_버튼을_누르면
        onView(withId(R.id.button2)).perform(click())

        // then : 화면에_2가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun 사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르면
        onView(withId(R.id.button3)).perform(click())

        // then : 화면에_3이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun 사용자가_피연산자_4_버튼을_누르면_화면에_4이_보여야_한다() {
        // when : 사용자가_피연산자_4_버튼을_누르면
        onView(withId(R.id.button4)).perform(click())

        // then : 화면에_4가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun 사용자가_피연산자_5_버튼을_누르면_화면에_5이_보여야_한다() {
        // when : 사용자가_피연산자_5_버튼을_누르면
        onView(withId(R.id.button5)).perform(click())

        // then : 화면에_5가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun 사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야_한다() {
        // when : 사용자가_피연산자_6_버튼을_누르면
        onView(withId(R.id.button6)).perform(click())

        // then : 화면에_6이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun 사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야_한다() {
        // when : 사용자가_피연산자_7_버튼을_누르면
        onView(withId(R.id.button7)).perform(click())

        // then : 화면에_7이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun 사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야_한다() {
        // when : 사용자가_피연산자_8_버튼을_누르면
        onView(withId(R.id.button8)).perform(click())

        // then : 화면에_8이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun 사용자가_피연산자_9_버튼을_누르면_화면에_9이_보여야_한다() {
        // when : 사용자가_피연산자_9_버튼을_누르면
        onView(withId(R.id.button9)).perform(click())

        // then : 화면에_9가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun 입력된_피연산자가_없을_때_사용자가_피연산자_버튼을_누르면_화면에_해당_숫자가_화면에_보여야_한다() {
        // when : 사용자가_피연산자_5_버튼을_누르고
        onView(withId(R.id.button5)).perform(click())
        // when : 사용자가_+버튼을_누른 후
        onView(withId(R.id.buttonPlus)).perform(click())
        // when : 사용자가_피연산자_1_버튼을_누르면
        onView(withId(R.id.button1)).perform(click())

        // then : 화면에_5 + 1이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
    }

    @Test
    fun 입력된_피연산자가_있을_때_기존_숫자_뒤에_해당_숫자가_화면에_보여야_한다() {
        // when : 사용자가_피연산자_8_버튼을_누르고
        onView(withId(R.id.button8)).perform(click())
        // when : 사용자가_피연산자_9_버튼을_누르면
        onView(withId(R.id.button9)).perform(click())

        // then : 화면에_89가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun 입력된_피연산자가_없을_때_사용자가_연산자_버튼을_누르면_화면에_아무런_변화가_없어야_한다() {
        // when : 사용자가_연산자_+_버튼을_누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then : 화면에_아무런_변화가_없어야_한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_피연산자가_있을_때_사용자가_연산자_버튼을_누르면_해당_기호가_화면에_보여야_한다() {
        // when : 사용자가_피연산자_1_버튼을_누르고
        onView(withId(R.id.button1)).perform(click())
        // when : 사용자가_연산자_+_버튼을_누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then : 화면에_1 +가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("1 + ")))

        // when : 사용자가_연산자_-_버튼을_누르면
        onView(withId(R.id.buttonMinus)).perform(click())

        // then : 화면에_1 -가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("1 - ")))
    }

    @Test
    fun 입력된_수식이_없을_때_사용자가_지우기_버튼을_누르면_화면에_아무런_변화가_없어야_한다() {
        // when : 사용자가_지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then : 화면에_아무런_변화가_없어야_한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수식이_있을_때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_피연산자가_지워져야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르고
        onView(withId(R.id.button3)).perform(click())
        // when : 사용자가_피연산자_2_버튼을_누르고
        onView(withId(R.id.button2)).perform(click())
        // when : 사용자가_연산자_+_버튼을_누르고
        onView(withId(R.id.buttonPlus)).perform(click())
        // when : 사용자가_피연산자_1_버튼을_누르고
        onView(withId(R.id.button1)).perform(click())

        // when : 사용자가_지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then : 화면에_32 + 가 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32 + ")))
    }

    @Test
    fun 입력된_수식이_있을_때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_연산자가_지워져야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르고
        onView(withId(R.id.button3)).perform(click())
        // when : 사용자가_피연산자_2_버튼을_누르고
        onView(withId(R.id.button2)).perform(click())
        // when : 사용자가_연산자_+_버튼을_누르고
        onView(withId(R.id.buttonPlus)).perform(click())

        // when : 사용자가_지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then : 화면에_32가 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32")))
    }

    @Test
    fun 두자리수_이상의_피연산자가_있을_때_사용자가_지우기_버튼을_누르면_피연산자_중_마지막으로_입력된_피연산자가_지워져야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르고
        onView(withId(R.id.button3)).perform(click())
        // when : 사용자가_피연산자_2_버튼을_누르고
        onView(withId(R.id.button2)).perform(click())

        // when : 사용자가_지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then : 화면에_32가 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun 한자리_수의_피연산자가_있을_때_사용자가_지우기_버튼을_누르면_모든_입력값이_지워져야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르고
        onView(withId(R.id.button3)).perform(click())

        // when : 사용자가_지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then :모든_입력값이_지워져야_한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수신이_완전할_때_사용자가_버튼을_누르면_입력된_수식의_결과가_화면에_보여야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르고
        onView(withId(R.id.button3)).perform(click())
        // when : 사용자가_연산자_+_버튼을_누르고
        onView(withId(R.id.buttonPlus)).perform(click())
        // when : 사용자가_피연산자_2_버튼을_누르고
        onView(withId(R.id.button2)).perform(click())
        // when : 사용자가_=_버튼을_누르면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then : 입력된_수식의_결과가_화면에_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("3 + 2 = 5.0")))
    }
}
