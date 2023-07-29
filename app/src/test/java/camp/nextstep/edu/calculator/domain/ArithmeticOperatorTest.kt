package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ArithmeticOperatorTest {

    @Test
    fun `문자열 "+"는 사칙연산자 이다`() {
        // given
        val param = "+"

        // when
        val actual = ArithmeticOperator.isArithmeticOperator(param)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `문자열 "-"는 사칙연산자 이다`() {
        // given
        val param = "-"

        // when
        val actual = ArithmeticOperator.isArithmeticOperator(param)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `문자열 "*"는 사칙연산자 이다`() {
        // given
        val param = "*"

        // when
        val actual = ArithmeticOperator.isArithmeticOperator(param)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `문자열 나누기는 사칙연산자 이다`() {
        // given
        val param = "/"

        // when
        val actual = ArithmeticOperator.isArithmeticOperator(param)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `문자열 +, -, *, 나누기를 제외한 문자열들은 사칙연산자가 아니다`() {
        // given
        val param1 = "a"
        val param2 = "b"
        val param3 = "US"
        val param4 = "UK"
        val param5 = "KR"

        // when
        val actual1 = ArithmeticOperator.isArithmeticOperator(param1)
        val actual2 = ArithmeticOperator.isArithmeticOperator(param2)
        val actual3 = ArithmeticOperator.isArithmeticOperator(param3)
        val actual4 = ArithmeticOperator.isArithmeticOperator(param4)
        val actual5 = ArithmeticOperator.isArithmeticOperator(param5)

        // then
        assertThat(actual1).isFalse()
        assertThat(actual2).isFalse()
        assertThat(actual3).isFalse()
        assertThat(actual4).isFalse()
        assertThat(actual5).isFalse()
    }

    @Test
    fun `3 + 6은 9이다`() {
        // given
        val operand1 = 3
        val operand2 = 6
        val expected = 9

        // when
        val actual = ArithmeticOperator.PLUS(operand1, operand2)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `6 - 3은 3이다`() {
        // given
        val operand1 = 6
        val operand2 = 3
        val expected = 3

        // when
        val actual = ArithmeticOperator.MINUS(operand1, operand2)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `3 * 6은 18이다`() {
        // given
        val operand1 = 3
        val operand2 = 6
        val expected = 18

        // when
        val actual = ArithmeticOperator.MULTIPLY(operand1, operand2)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `6 나누기 3은 2이다`() {
        // given
        val operand1 = 6
        val operand2 = 3
        val expected = 2

        // when
        val actual = ArithmeticOperator.DIVIDE(operand1, operand2)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
