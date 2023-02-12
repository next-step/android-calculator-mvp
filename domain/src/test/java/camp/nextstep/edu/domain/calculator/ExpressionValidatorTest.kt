package camp.nextstep.edu.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


class ExpressionValidatorTest {

    private val expressionValidator = ExpressionValidator()


    @Test
    fun `1 + 1`() {
        val expression = listOf(Num(1), Operators.of("+"), Num(1))
        val isValid = expressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `1 - 1`() {
        val expression = listOf(Num(1), Operators.of("-"), Num(1))
        val isValid = expressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `1 * 1`() {
        val expression = listOf(Num(1), Operators.of("*"), Num(1))
        val isValid = expressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `1 나누기 1`() {
        val expression = listOf(Num(1), Operators.of("/"), Num(1))
        val isValid = expressionValidator.validOrError(expression)
        assertEquals(true, isValid)
    }

    @Test
    fun `빈 표현식을 입력하는 경우 IllegalArgumentException 이 발생한다_1`() {
        val expression = listOf<ExpressionItem>()
        assertThrows(IllegalArgumentException::class.java) {
            expressionValidator.validOrError(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 IllegalArgumentException 을 뱉는다_1`() {
        val expression_0 = listOf(Operators.of("+"), Num(1))
        val expression_1 = listOf(Operators.of("+"))
        val expression_2 = listOf(Operators.of("+"), Num(1), Operators.of("*"))
        val expression_3 =
            listOf(Num(1), Operators.of("+"), Operators.of("+"), Operators.of("+"), Num(1))
        val expression_4 =
            listOf(Num(1), Operators.of("+"), Operators.of("-"), Operators.of("+"), Num(1))
        val expression_5 =
            listOf(Num(1), Num(1), Operators.of("-"), Operators.of("+"), Num(1))
        val expression_6 = listOf(Num(1), Operators.of("+"))
        val expressions = listOf(
            expression_0, expression_1, expression_2,
            expression_3, expression_4, expression_5,
            expression_6
        )

        expressions.forEach {
            assertThrows(IllegalArgumentException::class.java) {
                expressionValidator.validOrError(it)
            }
        }
    }
}
