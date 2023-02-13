package camp.nextstep.edu.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


class ExpressionValidatorTest {

    @Test
    fun `1 + 1`() {
        val expression = listOf(Num(1), Operators.Plus, Num(1))
        val isValid = ExpressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `1 - 1`() {
        val expression = listOf(Num(1), Operators.Minus, Num(1))
        val isValid = ExpressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `1 * 1`() {
        val expression = listOf(Num(1), Operators.Multiply, Num(1))
        val isValid = ExpressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `1 나누기 1`() {
        val expression = listOf(Num(1), Operators.Divide, Num(1))
        val isValid = ExpressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `빈 표현식을 입력하는 경우 IllegalArgumentException 이 발생한다_1`() {
        val expression = listOf<ExpressionItem>()
        assertThrows(IllegalArgumentException::class.java) {
            ExpressionValidator.validOrError(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 IllegalArgumentException 을 뱉는다_1`() {
        val expression_0 = listOf(Operators.Plus, Num(1))
        val expression_1 = listOf(Operators.Plus)
        val expression_2 = listOf(Operators.Plus, Num(1), Operators.Multiply)
        val expression_3 =
            listOf(Num(1), Operators.Plus, Operators.Plus, Operators.Plus, Num(1))
        val expression_4 =
            listOf(Num(1), Operators.Plus, Operators.Minus, Operators.Plus, Num(1))
        val expression_5 =
            listOf(Num(1), Num(1), Operators.Minus, Operators.Plus, Num(1))
        val expression_6 = listOf(Num(1), Operators.Plus)
        val expressions = listOf(
            expression_0, expression_1, expression_2,
            expression_3, expression_4, expression_5,
            expression_6
        )

        expressions.forEach {
            assertThrows(IllegalArgumentException::class.java) {
                ExpressionValidator.validOrError(it)
            }
        }
    }
}
