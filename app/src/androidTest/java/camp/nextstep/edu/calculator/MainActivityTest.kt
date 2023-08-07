package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.domain.Calculator
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.IllegalArgumentException

class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val calculator = Calculator

    @Test
    fun `사용자가_피연산자_0_버튼을_누르면_화면에_0이_보여야_한다`() {
        // when: '0' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: '0'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야_한다`() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_2_버튼을_누르면_화면에_2이_보여야_한다`() {
        // when: '2' 버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: '2'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야_한다`() {
        // when: '3' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: '3'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_4_버튼을_누르면_화면에_4이_보여야_한다`() {
        // when: '4' 버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_5_버튼을_누르면_화면에_5이_보여야_한다`() {
        // when: '5' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: '5'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야_한다`() {
        // when: '6' 버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: '6'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야_한다`() {
        // when: '7' 버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: '7'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야_한다`() {
        // when: '8' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: '8'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `사용자가_피연산자_5_PLUS_1_버튼을_누르면_해당_숫자가_화면에_보여야_한다`() {
        // when: '5', '+', '1' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then: '5 + 1' 이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `기존_숫자_뒤에_해당_숫자가_화면에_보여야_한다`() {
        // when: '8', '9' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.button9)).perform(click())

        // then: '89'가 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("89")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_피연산자가_없을때_연산자_버튼을_누르면_변화가_없어야_한다`() {
        // when: '+', '-', '*', '/' 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())
        onView(withId(R.id.buttonMultiply)).perform(click())
        onView(withId(R.id.buttonDivide)).perform(click())

        // then: 공백이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_피연산자가_있을때_연산자_버튼을_누르면_해당_기호가_화면에_보여야_한다1`() {
        // when: '1', '+' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: '1 +'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_피연산자가_있을때_연산자_버튼을_누르면_해당_기호가_화면에_보여야_한다2`() {
        // when: '1', '+', '-' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())

        // then: '1 -'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1 -")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_수식이_없을때_지우기_버튼을_누르면_화면에_변화가_없어야_한다`() {
        // when: '지우기' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '0' 이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_수식이_있을떄_지우기_버튼을_누르면_마지막으로_입력된_연산자_피연산자가_지워진다`() {
        // when: '3', '2', '+', '1' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32 +' 이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32 +")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_수식이_있을떄_지우기_버튼을_누르면_마지막으로_입력된_연산자_피연산자가_지워진다2`() {
        // when: '3', '2', '+', '1', 'Delete', 'Delete' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32' 이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_수식이_완전할떄_사용자가_결과_버튼을_누르면_결과가_화면에_보여야_한다`() {
        // when: '3', '+', '2', 'equals' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: '5' 이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
        calculator.clearCurrentOperandList()
    }

    @Test
    fun `입력된_수식이_완전하지_않을때_경고_문구가_화면에_보여야_한다`() {
        var error: Throwable? = null

        // when: '3', '+', 'equals' 버튼을 누르면
        runCatching {
            onView(withId(R.id.button3)).perform(click())
            onView(withId(R.id.buttonPlus)).perform(click())
            onView(withId(R.id.buttonEquals)).perform(click())
        }.onFailure { error = it }


        // then: '완성되지 않은 수식입니다' 토스트 메시지가 보여야 한다
        error?.let {
            assert(it is IllegalArgumentException)
            assertEquals(it.message, "완성되지 않은 수식입니다")
        }
        calculator.clearCurrentOperandList()
    }
}
