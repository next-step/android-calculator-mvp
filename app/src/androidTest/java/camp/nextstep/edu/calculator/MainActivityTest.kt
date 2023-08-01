package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 버튼0을_누르면_0이_보인다() {
        // when: '0' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: '0'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("0")))
    }

    @Test
    fun 버튼1을_누르면_1이_보인다() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("1")))
    }

    @Test
    fun 버튼2을_누르면_2이_보인다() {
        // when: '2' 버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: '2'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("2")))
    }

    @Test
    fun 버튼3을_누르면_3이_보인다() {
        // when: '3' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: '3'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("3")))
    }

    @Test
    fun 버튼4을_누르면_4이_보인다() {
        // when: '4' 버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("4")))
    }

    @Test
    fun 버튼5을_누르면_5이_보인다() {
        // when: '5' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: '5'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("5")))
    }

    @Test
    fun 버튼6을_누르면_6이_보인다() {
        // when: '6' 버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: '6'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("6")))
    }

    @Test
    fun 버튼7을_누르면_7이_보인다() {
        // when: '7' 버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: '7'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("7")))
    }

    @Test
    fun 버튼8을_누르면_8이_보인다() {
        // when: '8' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: '8'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("8")))
    }

    @Test
    fun 버튼9을_누르면_9이_보인다() {
        // when: '9' 버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: '9'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("9")))
    }

    @Test
    fun 버튼1_버튼2_버튼1_누르면_121가_보인다() {
        // when: '121'이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then: '121'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("121")))
    }

    @Test
    fun 버튼121_누르고_버튼더하기를_누르면_121_더하기_가_보인다() {
        // given: '121'이 입력하고
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: '+'를 입력하면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: '121 + '이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("121 + ")))
    }

    @Test
    fun 버튼121_더하기_누르고_버튼121_누르면_121_더하기_121가_보인다() {
        // given: '121 + '이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when: '121'를 입력하면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then: '121 + 121'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("121 + 121")))
    }

    @Test
    fun 버튼121_더하기_121_결과를_누르면_242보인다() {
        // given: '121 + 121'이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: '='를 입력하면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: '242'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("242")))
    }

    @Test
    fun 버튼121_곱하기_121_결과를_누르면_14641보인다() {
        // given: '121 + 121'이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonMultiply)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: '='를 입력하면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: '14641'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("14641")))
    }

    @Test
    fun 버튼121_지우기_누르면_12보인다() {
        // given: '121'이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: '지우기'를 입력하면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '12'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("12")))
    }

    @Test
    fun 버튼121_곱하기_지우기_누르면_121보인다() {
        // given: '121 * '이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonMultiply)).perform(click())

        // when: '지우기'를 입력하면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '121'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("121")))
    }

    @Test
    fun 버튼121_곱하기_지우기_두번_누르면_12보인다() {
        // given: '121 * '이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonMultiply)).perform(click())

        // when: '지우기'를 입력하면
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '12'이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("12")))
    }

    @Test
    fun 버튼121_리셋_누르면_빈_것으로_보인다() {
        // given: '121'이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: '리셋'를 입력하면
        onView(withId(R.id.buttonMemory)).perform(click())

        // then: ''이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("")))
    }

    @Test
    fun 버튼121_더하기_결과를_누르면_아무것도_안변한다() {
        // given: '121 + '이 입력되면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when: '='를 입력하면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: '121 + '이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("121 + ")))
    }

    @Test
    fun 초기상태_지우기_누르면_아무것도_안변한다() {
        // when: '지우기'를 입력하면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '121 + '이 보여야 한다.
        onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText("")))
    }
}