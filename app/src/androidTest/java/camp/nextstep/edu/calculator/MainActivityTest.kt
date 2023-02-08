package camp.nextstep.edu.calculator


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runners.Parameterized.Parameters

class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun `사용자가_피연산자_0_버튼을_누르면_화면에_0이_보여야_한다`() {
        // when: '0' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: '0'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun `사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야_한다`() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun `사용자가_피연산자_2_버튼을_누르면_화면에_2이_보여야_한다`() {
        // when: '2' 버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: '2'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun `사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야_한다`() {
        // when: '3' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: '3'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun `사용자가_피연산자_4_버튼을_누르면_화면에_4이_보여야_한다`() {
        // when: '4' 버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun `사용자가_피연산자_5_버튼을_누르면_화면에_5이_보여야_한다`() {
        // when: '5' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: '5'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun `사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야_한다`() {
        // when: '6' 버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: '6'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun `사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야_한다`() {
        // when: '7' 버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: '7'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun `사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야_한다`() {
        // when: '8' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: '8'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun `사용자가_피연산자_9_버튼을_누르면_화면에_9이_보여야_한다`() {
        // when: '9' 버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: '9'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun `입력된_피연산자가_없을때_사용자가_숫자버튼을_누르면_화면에_해당숫자가_화면에_보여야_한다`() {
        // 5 + -> 1 클릭 -> 5 + 1
        // when
        onView(withId(R.id.button1)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun `입력된_피연산자가_있을때_기존숫자_뒤에_해당숫자가_화면에_보여야_한다`() {
        // 8 -> 9 클릭 -> 89
        // given
        onView(withId(R.id.button8)).perform(click())

        // when
        onView(withId(R.id.button9)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }


    @Test
    @Parameters()
    fun `입력된_피연산자가_없을때_사용자가_사칙연산자_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        // 공백 -> + 클릭 -> 공백
        // when
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    @Parameters()
    fun `입력된_피연산자가_있을때_사용자가_사칙연산자_버튼을_누르면_화면에_해딩기호가_보여야_한다1`() {
        // 1 -> + 클릭 -> 1 +
        // given
        onView(withId(R.id.button1)).perform(click())

        // when
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    @Parameters()
    fun `입력된_피연산자가_있을때_사용자가_사칙연산자_버튼을_누르면_화면에_해딩기호가_보여야_한다2`() {
        // 1 + -> - 클릭 -> 1 -
        // given
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.buttonMinus))
            .perform(click())

        // then
        onView(withId(R.id.textView))
            .check(matches(withText("1 -")))
    }

    @Test
    fun `입력된_수식이_없을때_사용자가_지우기_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        // 공백 -> 지우기 클릭 -> 공백
        // when
        onView(withId(R.id.buttonDelete)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_수식이_있을때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_연산자_또는_피연산자가_지워져야_한다`() {
        // 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
        // given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.textView)).check(matches(withText("32 +")))

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.textView)).check(matches(withText("32")))

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.textView)).check(matches(withText("3")))

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.textView)).check(matches(withText("")))

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_수신이_완전할_때_사용자가_이퀄사인_버튼을_누르면_입력된_수식의_결과가_화면에_보여야_한다`() {
        // 3 + 2 -> = 클릭 -> 5
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
    fun `입력된_수식이_완전하지_않을_때_사용자가_이퀄사인_버튼을_눌렀을_때_완성되지_않은_수식이니_아무_변화가_없어야_한다`() {
        // 3 + -> = 클릭 -> 완성되지 않은 수식입니다
        // given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("3 +")))
    }
}