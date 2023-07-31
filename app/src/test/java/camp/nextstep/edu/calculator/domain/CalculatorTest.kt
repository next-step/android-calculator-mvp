package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculatorTest {

    @Test
    fun `덧셈 테스트`() {
        val actual = Calculator.evaluate("1 + 1")
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `뺄셈 테스트`() {
        val actual = Calculator.evaluate("2 - 1")
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `곱셈 테스트`() {
        val actual = Calculator.evaluate("2 * 3")
        assertThat(actual).isEqualTo("6")
    }

    @Test
    fun `나눗셈 테스트`() {
        val actual = Calculator.evaluate("4 / 2")
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `입력값이 null이거나 빈 공백 문자일 경우 IllegalArgumentException throw`() {
        runCatching {
            Calculator.evaluate(null)
        }.getOrElse {
            assertThat(it).isInstanceOf(IllegalArgumentException::class.java)
        }

        runCatching {
            Calculator.evaluate(" ")
        }.getOrElse {
            assertThat(it).isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Test
    fun `사칙연산 기호가 아닌 경우 IllegalArgumentException throw`() {
        runCatching {
            Calculator.evaluate("1 a 1")
        }.getOrElse {
            assertThat(it).isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Test
    fun `계산 2 + 3 * 4 나누기 2 = 10`() {
        val actual = Calculator.evaluate("2 + 3 * 4 / 2")
        assertThat(actual).isEqualTo("10")
    }
}
