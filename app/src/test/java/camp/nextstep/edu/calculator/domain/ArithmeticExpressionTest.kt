package camp.nextstep.edu.calculator.domain

import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.Exception

class ArithmeticExpressionTest {

    @Test
    fun `1 + + 2 + 3은 올바르지 않은 수식이다`() {
        assertThrows(IllegalArgumentException::class.java) { ArithmeticExpression("1 + + 2 + 3") }
    }

    @Test
    fun `+ 2 + 3은 올바르지 않은 수식이다`() {
        assertThrows(IllegalArgumentException::class.java) { ArithmeticExpression("+ 2 + 3") }
    }

    @Test
    fun `1 + 2 +은 올바르지 않은 수식이다`() {
        assertThrows(IllegalArgumentException::class.java) { ArithmeticExpression("1 + 2 +") }
    }

    @Test
    fun `1 a 2 + 은 올바르지 않은 수식이다`() {
        assertThrows(IllegalArgumentException::class.java) { ArithmeticExpression("1 a 2 +") }
    }

    @Test
    fun `1 + 2 + 3 + 5는 올바른 수식이다`() {
        // given
        var isExceptionThrown = false

        // when
        try {
            ArithmeticExpression("1 + 2 + 3 + 5")
        } catch (e: Exception) {
            isExceptionThrown = true
        }

        // then
        assertThat(isExceptionThrown).isFalse()
    }
}
