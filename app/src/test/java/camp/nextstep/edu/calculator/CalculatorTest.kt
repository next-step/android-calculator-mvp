package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculatorTest {

    @Test
    fun evaluatesPlus() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("1 + 2 + 3")
        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun evaluatesMinus() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("10 - 1 - 2")
        assertThat(actual).isEqualTo(7)
    }

    @Test
    fun evaluatesMultiply() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("2 * 3 * 4")
        assertThat(actual).isEqualTo(24)
    }

    @Test
    fun evaluatesDivide() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("30 / 3 / 2")
        assertThat(actual).isEqualTo(5)
    }

    @Test
    fun evaluatesExpression() {
        val calculator = Calculator()
        val actual: Double = calculator.evaluate("2 + 3 * 4 / 5")
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun evaluatesDisplayText() {
        val calculator = Calculator()
        val actual1: String = calculator.displayCalculation("2 + 3 * ", "4")
        assertThat(actual1).isEqualTo("2 + 3 * 4")

        val actual2: String = calculator.displayCalculation(actual1, divide)
        assertThat(actual2).isEqualTo("2 + 3 * 4 / ")
    }
}