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

    @Test
    fun `null Or Empty is null`() {
        val actual: Int? = calculator.evaluate(" ")

        assertThat(actual).isNull()
    }

    @Test
    fun `last text Empty is null`() {
        val actual: Int? = calculator.evaluate("1 * 2 ")

        assertThat(actual).isNull()
    }

    @Test
    fun `without blank text is Null`() {
        val actual: Int? = calculator.evaluate("1*2")

        assertThat(actual).isNull()
    }

    @Test
    fun `If it is not a operation symbol is Null`() {
        val actual: Int? = calculator.evaluate("1 & 2")

        assertThat(actual).isNull()
    }

    @Test
    fun `invalid expression is Null`() {
        val actual: Int? = calculator.evaluate("1 * 2 -")

        assertThat(actual).isNull()
    }

    @Test
    fun `number text only is Null`() {
        val actual: Int? = calculator.evaluate("12345")

        assertThat(actual).isNull()
    }

    @Test
    fun addition() {
        val actual: Int? = calculator.evaluate("1 + 2")

        assertThat(actual).isEqualTo(1 + 2)
    }

    @Test
    fun subtraction() {
        val actual: Int? = calculator.evaluate("1 - 2")

        assertThat(actual).isEqualTo(1 - 2)
    }

    @Test
    fun multiplication() {
        val actual: Int? = calculator.evaluate("1 * 2")

        assertThat(actual).isEqualTo(1 * 2)
    }

    @Test
    fun division() {
        val actual: Int? = calculator.evaluate("1 / 2")

        assertThat(actual).isEqualTo(1 / 2)
    }

    @Test
    fun `2 + 3 * 4 ÷ 2 표현식이 주어졌을때, 계산하면, 10이 된다`() {
        val actual: Int? = calculator.evaluate("2 + 3 * 4 / 2")

        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun `1 + 표현식이 주어졌을때, 계산하면 null 이 된다`() {
        val actual: Int? = calculator.evaluate("1 + ")

        assertThat(actual).isNull()
    }
}
