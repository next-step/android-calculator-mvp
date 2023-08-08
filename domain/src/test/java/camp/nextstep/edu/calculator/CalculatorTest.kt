package camp.nextstep.edu.calculator

import com.camp.nextstep.edu.domain.Calculator
import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Test

class CalculatorTest {
    @Test
    fun `1 + 2 + 3`() {
        val calculator = Calculator()
        val actual = calculator.evaluate("1 + 2 + 3")

        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun `2번이상 연산자가 수식에 있는경우`() {
        val calculator = Calculator()
        val exception = Assert.assertThrows(IllegalStateException::class.java) {
            calculator.evaluate("1 ++ 2 / 3 * 2 - 3")
        }
        assertThat(exception).isNotNull()
    }

    @Test
    fun `1+ 2 + 3 +`() {
        val calculator = Calculator()
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("1+ 2 + 3 +")
        }

        assertThat(exception).isNotNull()
    }

    @Test
    fun `+ 1+ 2 + 3`() {
        val calculator = Calculator()
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("+ 1+ 2 + 3")
        }

        assertThat(exception).isNotNull()
    }

}