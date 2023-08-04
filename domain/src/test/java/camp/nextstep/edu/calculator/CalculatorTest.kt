package camp.nextstep.edu.calculator

import com.camp.nextstep.edu.domain.Calculator
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculatorTest {
    @Test
    fun `1 + 2 + 3`() {
        val calculator = Calculator()
        val actual = calculator.evaluate("1 + 2 + 3")

        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun `곱셉 나눗셈 덧셈 뺄샘`() {
        val calculator = Calculator()
        val actual = calculator.evaluate("1 ++ 2 / 3 * 2 - 3")

        assertThat(actual).isEqualTo(-1)
    }

    @Test
    fun `1+ 2 + 3 +`() {
        val calculator = Calculator()
        val actual = calculator.evaluate("1+ 2 + 3 +")

        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun `+ 1+ 2 + 3`() {
        val calculator = Calculator()
        val actual = calculator.evaluate("+ 1+ 2 + 3")

        assertThat(actual).isEqualTo(6)
    }

}