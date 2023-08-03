package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
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

        // then: '0'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun button1() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun button2() {
        // when: '2' 버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: '2'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun button3() {
        // when: '3' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: '3'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun button4() {
        // when: '4' 버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun button5() {
        // when: '5' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: '5'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button6() {
        // when: '6' 버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: '6'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun button7() {
        // when: '7' 버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: '7'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun button8() {
        // when: '8' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: '8'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun button9() {
        // when: '9' 버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: '9'이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun `입력된_피연산자가_있을_때_기존_숫자_뒤에_해당_숫자가_화면에_보여야_한다`() {
        // given: '8' 버튼을 누른 상태일 때
        onView(withId(R.id.button8)).perform(click())

        // when: '9' 버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: '89'가 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun `입력된_피연산자가_없을_때_연산자_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        // when: '입력된 숫자가 없을 때 연산자 버튼을 누르면 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: '화면에 변화가 없어야 한다'
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_피연산자가_있을_때_연산자_버튼을_누르면_해당_기호가_화면에_보여야_한다`() {
        // given: '입력된 피연산자가 있을 때'
        onView(withId(R.id.button9)).perform(click())

        // when: '연산자 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: '화면에 해당 기호가 보여야 한다'
        onView(withId(R.id.textView)).check(matches(withText("9 + ")))
    }

    @Test
    fun `입력된_피연산자와_연산자가_있을_때_연산자_버튼을_누르면_해당_기호가_화면에_보여야_한다`() {
        // given: '입력된 피연산자가 있을 때'
        onView(withId(R.id.button9)).perform(click())

        // when: '연산자 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())

        // then: '화면에 해당 기호가 보여야 한다'
        onView(withId(R.id.textView)).check(matches(withText("9 - ")))
    }

    @Test
    fun `입력된_수식이_없을_때_사용자가_지우기_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        // when: 사용자가 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 화면에 아무런 변화가 없어야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_수식이_있을_때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_연산자_또는_피연산자가_지워져야_한다`() {
        // given: 입력된 수식이 있을 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 마지막으로 입력된 연산자 또는 피연산자가 지워진다.
        onView(withId(R.id.textView)).check(matches(withText("32 + ")))

        // 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32")))
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3")))
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_수신이_완전할_때_사용자가_대입연산자_버튼을_누르면_입력된_수식의_결과가_화면에_보여야_한다`() {
        // given: 입력된 수신이 완전할 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when: 사용자가 = 버튼을 누르면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: 입력된 수식의 결과가 화면에 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }
}
