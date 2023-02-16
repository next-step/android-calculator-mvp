package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class CalculatorTest {

    @Test
    fun _1_더하기_2_더하기_3의_계산결과는_6이_되어야한다() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("1 + 2 + 3")
        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun _10_빼기_1_빼기_2의_계산결과는_7이_되어야한다() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("10 - 1 - 2")
        assertThat(actual).isEqualTo(7)
    }

    @Test
    fun _2_곱하기_3_곱하기_4의_계산결과는_24가_되어야한다() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("2 * 3 * 4")
        assertThat(actual).isEqualTo(24)
    }

    @Test
    fun _30_나누기_3_나누기_2의_계산결과는_5가_되어야한다() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("30 / 3 / 2")
        assertThat(actual).isEqualTo(5)
    }

    @Test
    fun _2_더하기_3_곱하기_4_나누기_5의_계산결과는_4가_되어야한다() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("2 + 3 * 4 / 5")
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun 사용자가_2_더하기_3_곱하기_를_입력_후_4를_입력하면_순서대로_입력한_수식이_보여야한다() {
        val calculator = Calculator()
        val actual1: String = calculator.displayCalculation("2 + 3 * ", "4")
        assertThat(actual1).isEqualTo("2 + 3 * 4")

        val actual2: String = calculator.displayCalculation(actual1, divide)
        assertThat(actual2).isEqualTo("2 + 3 * 4 / ")
    }

    @Test
    fun 사용자가_사칙연산_이외의_문자를_입력하면_IllegalArgumentException이_발생해야_한다() {
        assertThrows(IllegalArgumentException::class.java) {
            val calculator = Calculator()
            calculator.displayCalculation("1 + 2 + 3 ", " ")
            calculator.displayCalculation("1 + 2 + 3", " & ")
        }
    }
}