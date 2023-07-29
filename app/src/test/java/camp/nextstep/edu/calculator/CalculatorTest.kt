package camp.nextstep.edu.calculator

import com.example.calculatorlib.Calculator
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun initCalculator() {
        calculator = Calculator()
    }

    @Test
    fun operatorTest1() {
        // when : calculator를 통해 수식을 계산한다.
        val actual = calculator.evaluate("1 + 2 + 3")

        // then : 사칙 연산의 우선 순위가 아닌 입력 우선 순위로 계산을 진행한다.
        assertEquals(6, actual)
    }

    @Test
    fun operatorTest2() {
        // when : calculator를 통해 수식을 계산한다.
        val actual = calculator.evaluate("3 * 2 + 8 / 3")

        // then : 사칙 연산의 우선 순위가 아닌 입력 우선 순위로 계산을 진행한다.
        assertThat(actual).isEqualTo(4)
    }

    @Test(expected = IllegalArgumentException::class)
    fun operatorErrorTest1() {
        // when : 수식을 공백 문자열로 채운다.
        calculator.evaluate("     ")

        // then : IllegalArgumentException이 발생한다.
    }

    @Test(expected = IllegalArgumentException::class)
    fun operatorErrorTest2() {
        // when : 사칙연산 기호가 아닌 값이 들어간다.
        calculator.evaluate("1 - 2 ( 3 % 3")

        // then : IllegalArgumentException이 발생한다.

    }

    @Test(expected = IllegalArgumentException::class)
    fun operatorErrorTest3() {
        // when : 입력값이 null이다.
        calculator.evaluate(null)

        // then : IllegalArgumentException이 발생한다.
    }
}