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
    fun 입력된_피연산자가_없을때_숫자를_클릭하면_숫자가_노출된다() {
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun 입력된_피연산자가_있을때_숫자를_클릭하면_연속된_숫자가_노출된다() {
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun 입력된_피연산자가_없을때_연산자를_클릭하면_아무것도_노출되지_않는다() {
        onView(withId(R.id.buttonDivide)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_피연산자가_있을때_연산자를_클릭하면_연산자가_노출된다() {
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3 + ")))
    }

    @Test
    fun 입력된_피연산자와_연산자가_있을때_연산자를_클릭하면_연산자가_대체된다() {
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3 - ")))
    }

    @Test
    fun 입력된_수식이_없을때_지우기_버튼을_클릭하면_아무_변화가_없다() {
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수식에서_지우기_버튼을_클릭하면_마지막으로_입력된_연산자_혹은_피연산자가_지워진다() {
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32 + 1")))
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32 +")))
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32")))
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3")))
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수식이_완전하고_계산버튼_클릭시_계산결과가_노출된다() {
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button0() {
        // when : 계산기 버튼 0을 클릭하면
        onView(withId(R.id.button0)).perform(click())

        // then : 0이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("0")))
        // onView(withText("0")).check(matches(isDisplayed()))
    }

    @Test
    fun button1() {
        // when : 계산기 버튼 1을 클릭하면
        onView(withId(R.id.button1)).perform(click())

        // then : 1이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("1")))
        // onView(withText("1")).check(matches(isDisplayed()))
    }

    @Test
    fun button2() {
        // when : 계산기 버튼 2을 클릭하면
        onView(withId(R.id.button2)).perform(click())

        // then : 2이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("2")))
        // onView(withText("2")).check(matches(isDisplayed()))
    }

    @Test
    fun button3() {
        // when : 계산기 버튼 3을 클릭하면
        onView(withId(R.id.button3)).perform(click())

        // then : 3이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("3")))
        // onView(withText("3")).check(matches(isDisplayed()))
    }

    @Test
    fun button4() {
        // when : 계산기 버튼 4을 클릭하면
        onView(withId(R.id.button4)).perform(click())

        // then : 4이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("4")))
        // onView(withText("4")).check(matches(isDisplayed()))
    }

    @Test
    fun button5() {
        // when : 계산기 버튼 5을 클릭하면
        onView(withId(R.id.button5)).perform(click())

        // then : 5이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("5")))
        // onView(withText("5")).check(matches(isDisplayed()))
    }

    @Test
    fun button6() {
        // when : 계산기 버튼 6을 클릭하면
        onView(withId(R.id.button6)).perform(click())

        // then : 6이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("6")))
        // onView(withText("6")).check(matches(isDisplayed()))
    }

    @Test
    fun button7() {
        // when : 계산기 버튼 7을 클릭하면
        onView(withId(R.id.button7)).perform(click())

        // then : 7이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("7")))
        // onView(withText("7")).check(matches(isDisplayed()))
    }

    @Test
    fun button8() {
        // when : 계산기 버튼 8을 클릭하면
        onView(withId(R.id.button8)).perform(click())

        // then : 8이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("8")))
        // onView(withText("8")).check(matches(isDisplayed()))
    }

    @Test
    fun button9() {
        // when : 계산기 버튼 9을 클릭하면
        onView(withId(R.id.button9)).perform(click())

        // then : 9이 보여야 한다.
        // 위 방식은 화이트 박스 테스트이며, 아래는 블랙 박스 테스트이다.
        onView(withId(R.id.textView)).check(matches(withText("9")))
        // onView(withText("9")).check(matches(isDisplayed()))
    }
}