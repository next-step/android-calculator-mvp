package camp.nextstep.edu.domain.calculator

import org.junit.Assert.*
import org.junit.Test
import org.junit.Assert.assertThrows


class CalculatorTest {

    private val calculator = Calculator()


    @Test
    fun `1 더하기 1 = 2`() {
        val actual: Int = calculator.evaluate("1 + 1")
        assertEquals(2, actual)
    }

    @Test
    fun `1 빼기 1 = 0`() {
        val actual: Int = calculator.evaluate("1 - 1")
        assertEquals(0, actual)
    }

    @Test
    fun `1 곱하기 1 = 1`() {
        val actual: Int = calculator.evaluate("1 * 1")
        assertEquals(1, actual)
    }

    @Test
    fun `1 나누기 1 = 1`() {
        val actual: Int = calculator.evaluate("1 / 1")
        assertEquals(1, actual)
    }

    @Test
    fun `1 더하기 2 더하기 3 = 6`() {
        val actual: Int = calculator.evaluate("1 + 2 + 3")
        assertEquals(6, actual)
    }

    @Test
    fun `2 더하기 3 곱하기 4 나누기 2 = 10`() {
        val actual: Int = calculator.evaluate("2 + 3 * 4 / 2")
        assertEquals(10, actual)
    }

    @Test
    fun `0 빼기 1 = -1`() {
        val actual: Int = calculator.evaluate("0 - 1")
        assertEquals(-1, actual)
    }

    @Test
    fun `-1 더하기 -1 = -2`() {
        val actual: Int = calculator.evaluate("-1 + -1")
        assertEquals(-2, actual)
    }

    @Test
    fun `-1 곱하기 -1 은 1`() {
        val actual: Int = calculator.evaluate("-1 * -1")
        assertEquals(1, actual)
    }

    @Test
    fun `-1 나누기 -1 은 1`() {
        val actual: Int = calculator.evaluate("-1 / -1")
        assertEquals(1, actual)
    }

    @Test
    fun `0 으로 나누면 ArithmeticException 이 발생한다`() {
        assertThrows(ArithmeticException::class.java) {
            calculator.evaluate("1 / 0")
        }
    }
}
