package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

fun String?.toExpression(): Expression {
    return Expression(this)
}

class CalculatorTest {

    @Test
    fun `덧셈 테스트`() {
        val actual = Calculator.evaluate("1 + 1".toExpression())
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `뺄셈 테스트`() {
        val actual = Calculator.evaluate("2 - 1".toExpression())
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `곱셈 테스트`() {
        val actual = Calculator.evaluate("2 * 3".toExpression())
        assertThat(actual).isEqualTo("6")
    }

    @Test
    fun `나눗셈 테스트`() {
        val actual = Calculator.evaluate("4 / 2".toExpression())
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `입력값이 null이거나 빈 공백 문자일 경우 IllegalArgumentException throw`() {
        assertThrows(IllegalArgumentException::class.java) {
            Calculator.evaluate(null.toExpression())
        }

        assertThrows(IllegalArgumentException::class.java) {
            Calculator.evaluate(" ".toExpression())
        }
    }

    @Test
    fun `사칙연산 기호가 아닌 경우 IllegalArgumentException throw`() {
        assertThrows(IllegalArgumentException::class.java) {
            Calculator.evaluate("1 a 1".toExpression())
        }
    }

    @Test
    fun `계산식이 중간에 끊기는 경우 IllegalStateException throw`() {
        assertThrows(IllegalStateException::class.java) {
            Calculator.evaluate("1 + 1 *".toExpression())
        }
    }

    @Test
    fun `계산 20 + 3`() {
        val actual = Calculator.evaluate("20 + 3".toExpression())
        assertThat(actual).isEqualTo("23")
    }

    @Test
    fun `계산 a + 3 IllegalArgumentException throw`() {
        assertThrows(IllegalArgumentException::class.java) {
            Calculator.evaluate("a + 3".toExpression())
        }
    }

    @Test
    fun `계산 -5 + 3`() {
        val actual = Calculator.evaluate("-5 + 3".toExpression())
        assertThat(actual).isEqualTo("-2")
    }

    @Test
    fun `계산 0 + 3`() {
        val actual = Calculator.evaluate("0 + 3".toExpression())
        assertThat(actual).isEqualTo("3")
    }

    @Test
    fun `계산 2 + 3 * 4 나누기 2 = 10`() {
        val actual = Calculator.evaluate("2 + 3 * 4 / 2".toExpression())
        assertThat(actual).isEqualTo("10")
    }
}
