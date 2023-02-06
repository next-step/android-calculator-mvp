package camp.nextstep.edu.domain.calculator

import org.junit.Assert.*
import org.junit.Test


class ExpressionParserTest {

    private val expressionParser = ExpressionParser()


    @Test
    fun `1 + 1`() {
        val expression = "1 + 1"
        val parsed = expressionParser.parse(expression)
        assertEquals(
            listOf(Num(1), Operators.Plus, Num(1)),
            parsed
        )
    }

    @Test
    fun `1 - 1`() {
        val expression = "1 - 1"
        val parsed = expressionParser.parse(expression)
        assertEquals(
            listOf(Num(1), Operators.Minus, Num(1)),
            parsed
        )
    }

    @Test
    fun `1 * 1`() {
        val expression = "1 * 1"
        val parsed = expressionParser.parse(expression)
        assertEquals(
            listOf(Num(1), Operators.Multiply, Num(1)),
            parsed
        )
    }

    @Test
    fun `1 나누기 1`() {
        val expression = "1 / 1"
        val parsed = expressionParser.parse(expression)
        assertEquals(
            listOf(Num(1), Operators.Divide, Num(1)),
            parsed
        )
    }

    @Test
    fun `빈 문자열을 입력하는 경우 IllegalArgumentException 이 발생한다_0`() {
        val expression = ""
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `빈 문자열을 입력하는 경우 IllegalArgumentException 이 발생한다_1`() {
        val expression = " "
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 IllegalArgumentException 을 뱉는다_1`() {
        val expression_0 = "+ 1"
        val expression_1 = "+"
        val expression_2 = " + 1 *"
        val expression_3 = "1 + + + 1"
        val expression_4 = "1 + - + 1"
        val expression_5 = "1 1 - + 1"
        val expression_6 = "1 + "
        val expression_7 = "1 + "
        val expressions = listOf(
            expression_0, expression_1, expression_2,
            expression_3, expression_4, expression_5,
            expression_6, expression_7
        )

        expressions.forEach {
            assertThrows(IllegalArgumentException::class.java) {
                expressionParser.parse(it)
            }
        }
    }
}
