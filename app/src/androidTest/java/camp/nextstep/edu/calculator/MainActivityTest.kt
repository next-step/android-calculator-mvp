package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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

        // then: 화면에_0이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun 사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야_한다() {
        // when : 사용자가_피연산자_1_버튼을_누르면
        onView(withId(R.id.button1)).perform(click())

        // then: 화면에_1이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun 사용자가_피연산자_2_버튼을_누르면_화면에_2가_보여야_한다() {
        // when : 사용자가_피연산자_2_버튼을_누르면
        onView(withId(R.id.button2)).perform(click())

        // then: 화면에_2가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun 사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야_한다() {
        // when : 사용자가_피연산자_3_버튼을_누르면
        onView(withId(R.id.button3)).perform(click())

        // then: 화면에_3이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun 사용자가_피연산자_4_버튼을_누르면_화면에_4가_보여야_한다() {
        // when : 사용자가_피연산자_4_버튼을_누르면
        onView(withId(R.id.button4)).perform(click())

        // then: 화면에_4가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun 사용자가_피연산자_5_버튼을_누르면_화면에_5가_보여야_한다() {
        // when : 사용자가_피연산자_5_버튼을_누르면
        onView(withId(R.id.button5)).perform(click())

        // then: 화면에_5가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun 사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야_한다() {
        // when : 사용자가_피연산자_6_버튼을_누르면
        onView(withId(R.id.button6)).perform(click())

        // then: 화면에_6이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun 사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야_한다() {
        // when : 사용자가_피연산자_7_버튼을_누르면
        onView(withId(R.id.button7)).perform(click())

        // then: 화면에_7이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun 사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야_한다() {
        // when : 사용자가_피연산자_8_버튼을_누르면
        onView(withId(R.id.button8)).perform(click())

        // then: 화면에_8이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun 사용자가_피연산자_9_버튼을_누르면_화면에_9가_보여야_한다() {
        // when : 사용자가_피연산자_9_버튼을_누르면
        onView(withId(R.id.button9)).perform(click())

        // then: 화면에_9가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun 입력된_피연산자가_있는_경우_기존_숫자_뒤에_해당_숫자가_화면에_표시된다() {
        // given: 피연산자_1이_있을_때
        onView(withId(R.id.button1)).perform(click())

        // when : 피연산자_0버튼을_누르면
        onView(withId(R.id.button0)).perform(click())

        // then: 화면에_10이_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("10")))
    }

    @Test
    fun 입력된_피연산자가_없을_경우_연산자_버튼_누르면_화면에_아무런_변화가_없다() {
        // given : 피연산자가_없을_때

        // when : 연산자_+버튼을_누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: 화면에_표시되는_내용이_없어야_한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_피연산자가_있을_경우_연산자_버튼_누르면_화면에_해당_연산자를_표시한다() {
        // given : 피연산자가_10이_있을_때
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button0)).perform(click())

        // when : 연산자_+버튼을_누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: 화면에_10 +가_보여야_한다
        onView(withId(R.id.textView)).check(matches(withText("10 +")))
    }

    @Test
    fun 입력된_수식이_없을_경우_지우기_버튼_누르면_화면에_아무런_변화가_없다() {
        // given : 입력된_수식이_없을_때

        // when : 지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 화면에_아무런_변화가_없다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수식_10이_있을_경우_지우기_버튼_누르면_수식에서_0을_삭제한다() {
        // given : 입력된_수식_10이_있을_경우
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button0)).perform(click())

        // when : 지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 수식에서_0을_삭제한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun 입력된_수식_10_더하기_가_있을_경우_지우기_버튼_누르면_수식에서_더하기_기호를_삭제한다() {
        // given : 입력된_수식_10이_있을_경우
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when : 지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 수식에서_+를_삭제한다
        onView(withId(R.id.textView)).check(matches(withText("10")))
    }

    @Test
    fun 입력된_수식_10_더하기_12가_있을_경우_지우기_버튼_누르면_수식에서_2를_삭제한다() {
        // given : 입력된_수식_10 + 12가_있을_경우
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when : 지우기_버튼을_누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 수식에서_2를_삭제한다
        onView(withId(R.id.textView)).check(matches(withText("10 + 1")))
    }

    @Test
    fun 입력된_완전한_수식이_10_더하기_12일_떄_결과_버튼_누르면_수식의_결과_22가_화면에_표시한다() {
        // given : 입력된_수식_10 + 12가_있을_경우
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when : =_버튼을_누르면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: 수식의_결과_22를_표시한다
        onView(withId(R.id.textView)).check(matches(withText("22")))
    }

    @Test
    fun 입력된_수식이_불완전할_경우_결과_버튼_누르면_토스트_메시지를_표시한다() {
        // given : 불완전한_수식_10 +가_있을_경우
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when : =_버튼을_누르면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: 토스트_메시지를_표시한다
        onView(withText(R.string.incomplete_expression))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}