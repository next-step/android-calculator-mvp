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
    fun 입력된_피연산자가_없을_떄_숫자_버튼_클릭_테스트() {
        // when : 피연산가 없는 상태에서 숫자 버튼을 누른다.
        onView(withId(R.id.button2)).perform(click())

        // then : 선택한 숫자가 화면에 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun 마지막_입력이_연산자일_때_숫자_버튼_클릭_테스트() {
        // given : 가장 마지막 입력이 연산자이다.
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when : 숫자 버튼을 누른다.
        onView(withId(R.id.button7)).perform(click())

        // then : 연산자 이후 숫자가 추가되어 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("5 + 7")))
    }

    @Test
    fun 입력된_피연산자가_있을_떄_숫자_버튼_클릭_테스트() {
        // given : 피연산자가 입력되어있다.
        onView(withId(R.id.button1)).perform(click())

        // when : 숫자 버튼을 누른다.
        onView(withId(R.id.button7)).perform(click())

        // then : 연결된 피연산자의 문자가 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("17")))
    }

    @Test
    fun 입력된_피연산자가_없을_떄_연산자_버튼_클릭_테스트() {
        // when : 입련된 피연산 자 없을 때 연산자 버튼 클릭
        onView(withId(R.id.buttonPlus)).perform(click())

        // then : 화면에 연산가자 보여지면 안된다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_피연산자가_있을_때_연산자_버튼_클릭_테스트1() {
        // given : 피연산자가 입력되어있다. "5"
        onView(withId(R.id.button5)).perform(click())

        // when : 연산자 버튼을 클릭
        onView(withId(R.id.buttonMinus)).perform(click())

        // than : 화면에 연산자가 추가되어 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("5 -")))
    }

    @Test
    fun 입력된_피연산자가_있을_때_연산자_버튼_클릭_테스트2() {
        // given : 피연산자가 입력되어있다. "5 -"
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())

        // when : 연산자 버튼을 클릭
        onView(withId(R.id.buttonDivide)).perform(click())

        // than : 화면에 연산자가 추가되어 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("5 /")))
    }

    @Test
    fun 입력된_수식이_없을_때_지우기_버튼_클릭_테스트() {
        // given : 입력된 수식이 없습니다.
        // when : 지우기 버튼을 클릭
        onView(withId(R.id.buttonDelete)).perform(click())

        // than : 아무 변화가 없다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    // 여기부터 재시도
    @Test
    fun 입력된_수식이_있을_때_지우기_버튼_클릭_테스트() {
        // given : 입력된 수식이 존재한다. "32 + 1"
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when : 지우기 버튼을 반복 클릭
        onView(withId(R.id.buttonDelete)).perform(click()) // 32 +
        onView(withId(R.id.buttonDelete)).perform(click()) // 32
        onView(withId(R.id.buttonDelete)).perform(click()) // 3
        onView(withId(R.id.buttonDelete)).perform(click()) //

        // than : 마지막 입력된 항목이 삭제 되고 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수식이_완전할_때_계산_테스트() {
        // given : 정상적인 수식이 입력되어있다.
        // 32 + 12
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when : = 버튼을 클릭한다.
        onView(withId(R.id.buttonEquals)).perform(click()) // 3

        // than : 계산된 수식 값이 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("20")))
    }


    @Test
    fun button0() {
        // when: 버튼 0을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: 1이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun button1() {
        // when: 버튼 1을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: 1이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun button2() {
        // when: 버튼 2을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: 2이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun button3() {
        // when: 버튼 3을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: 3이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun button4() {
        // when: 버튼 4을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: 4이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun button5() {
        // when: 버튼 5을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: 5이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun button6() {
        // when: 버튼 6을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: 6이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun button7() {
        // when: 버튼 7을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: 7이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun button8() {
        // when: 버튼 8을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: 8이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun button9() {
        // when: 버튼 9을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: 9이 보여야 한다.

        // 화이트 박스 스타일
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }


}