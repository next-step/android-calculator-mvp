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
    fun `공백은 입력할 수 없다_0`() {
        val expression = ""
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `공백은 입력할 수 없다_1`() {
        val expression = " "
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 에러를 뱉는다_0`() {
        val expression = "1 + "
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 에러를 뱉는다_1`() {
        val expression = "+ 1"
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 에러를 뱉는다_2`() {
        val expression = "+"
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }

    @Test
    fun `유효하지 않은 표현식을 입력했을 경우는 에러를 뱉는다_3`() {
        val expression = " + 1 *"
        assertThrows(IllegalArgumentException::class.java) {
            expressionParser.parse(expression)
        }
    }
}
