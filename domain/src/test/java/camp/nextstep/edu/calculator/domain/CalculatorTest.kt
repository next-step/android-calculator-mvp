package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculatorTest {

    @Test(expected = IllegalArgumentException::class)
    fun `null Or Empty IllegalArgumentException`() {
        val calculator = Calculator()

        calculator.evaluate(" ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `If it is not a operation symbol IllegalArgumentException throw`() {
        val calculator = Calculator()

        calculator.evaluate("1 & 2")
    }

    @Test
    fun addition() {
        val calculator = Calculator()

        val actual: Int = calculator.evaluate("1 + 2")

        assertThat(actual).isEqualTo(1 + 2)
    }

    @Test
    fun subtraction() {
        val calculator = Calculator()

        val actual: Int = calculator.evaluate("1 - 2")

        assertThat(actual).isEqualTo(1 - 2)
    }

    @Test
    fun multiplication() {
        val calculator = Calculator()

        val actual: Int = calculator.evaluate("1 * 2")

        assertThat(actual).isEqualTo(1 * 2)
    }

    @Test
    fun division() {
        val calculator = Calculator()

        val actual: Int = calculator.evaluate("1 / 2")

        assertThat(actual).isEqualTo(1 / 2)
    }

    @Test
    fun evaluatesExpression() {
        val calculator = Calculator()

        val actual: Int = calculator.evaluate("2 + 3 * 4 / 2")

        assertThat(actual).isEqualTo(10)
    }
}
