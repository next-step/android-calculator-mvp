package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun initialize() {
        calculator = Calculator(InputTextConvertor())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `null Or Empty IllegalArgumentException`() {
        calculator.evaluate(" ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `last text Empty IllegalArgumentException`() {
        calculator.evaluate("1 * 2 ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `without blank text IllegalArgumentException`() {
        calculator.evaluate("1*2")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `If it is not a operation symbol IllegalArgumentException throw`() {
        calculator.evaluate("1 & 2")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `invalid expression IllegalArgumentException`() {
        calculator.evaluate("1 * 2 -")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `number text only IllegalArgumentException`() {
        calculator.evaluate("12345")
    }

    @Test
    fun addition() {
        val actual: Int = calculator.evaluate("1 + 2")

        assertThat(actual).isEqualTo(1 + 2)
    }

    @Test
    fun subtraction() {
        val actual: Int = calculator.evaluate("1 - 2")

        assertThat(actual).isEqualTo(1 - 2)
    }

    @Test
    fun multiplication() {
        val actual: Int = calculator.evaluate("1 * 2")

        assertThat(actual).isEqualTo(1 * 2)
    }

    @Test
    fun division() {
        val actual: Int = calculator.evaluate("1 / 2")

        assertThat(actual).isEqualTo(1 / 2)
    }

    @Test
    fun `2 + 3 * 4 ÷ 2 표현식이 주어졌을때, 계산하면, 10이 된다`() {
        val actual: Int = calculator.evaluate("2 + 3 * 4 / 2")

        assertThat(actual).isEqualTo(10)
    }
}
