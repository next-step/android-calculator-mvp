package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.nextstep.edu.calculator.domain.Calculator
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `입력된_피연산자가_없을_때_사용자가_피연산자_0_부터_9_버튼을_누르면_화면에_해당_숫자가_화면에_보여야_한다`() {
        // when
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button7)).perform(click())
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.button9)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("0123456789")))
    }

    @Test
    fun `입력된_피연산자가_있을_때_기존_숫자_뒤에_해당_숫자가_화면에_보여야_한다_예를_들면_8이_입력되어_있을_때_9를_입력하면_89가_보여야_한다`() {
        // when
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.button9)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun `입력된_피연산자가_없을_때_사용자가_연산자_덧셈_뺄셈_곱셈_나눗셈_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        // when  -> + 클릭 ->
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_피연산자가_있을_때_사용자가_연산자_덧셈_뺄셈_곱셈_나눗셈_버튼을_누르면_해당_기호가_화면에_보여야_한다`() {
        // when
        // 1 -> + 클릭 -> 1 +
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("1 + ")))

        // 1 + -> - 클릭 -> 1 -
        onView(withId(R.id.buttonMinus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1 - ")))

    }

    @Test
    fun `입력된_수식이_없을_때_사용자가_지우기_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        // when
        onView(withId(R.id.buttonDelete)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_수식이_있을_때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_연산자_또는_피연산자가_지워져야_한다`() {
        // when
        // 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32 + ")))

        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32")))

        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3")))

        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))

        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `입력된_수신이_완전할_때_사용자가_등호_버튼을_누르면_입력된_수식의_결과가_화면에_보여야_한다`() {
        // when
        // 3 + 2 -> = 클릭 -> 5
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())


        //then
        val result = Calculator().evaluate("3 + 2").toString()
        onView(withId(R.id.textView)).check(matches(withText(result)))
    }
}