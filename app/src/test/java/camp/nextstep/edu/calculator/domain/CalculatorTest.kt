package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun `2 + 3 * 4 나누기 2는 10이다`() {
        // given
        val expression = ArithmeticExpression("2 + 3 * 4 / 2")
        val expected = 10

        // when
        val actual = calculator.calculate(expression)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `10 - 4 나누기 3 * 5는 10이다`() {
        // given
        val expression = ArithmeticExpression("10 - 4 / 3 * 5")
        val expected = 10

        // when
        val actual = calculator.calculate(expression)

        // then
        assertThat(actual).isEqualTo(expected)
    }

}
