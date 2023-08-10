package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionTest {
    private lateinit var expression: Expression

    @Before
    fun initialize() {
        expression = Expression(InputTextConvertor())
    }

    @Test
    fun `피연산자 1을추가하고 반환값이 1인지 확인`() {
        val actual = expression.addExpression(
            ExpressionItems(emptyList()), "1"
        ).getText()

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `연산자 + 을추가하고 반환값이 빈문자인지 확인`() {
        val actual = expression.addExpression(
            ExpressionItems(emptyList()), "+"
        ).getText()

        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 + 을추가하고 반환값이 1 + 인지 확인`() {
        val expressionItems = ExpressionItems(listOf(Operand(1)))

        val actual = expression.addExpression(expressionItems, "+").getText()

        assertThat(actual).isEqualTo("1 + ")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 + 을추가하고 피연산자 2를추가하고 반환값이 1 + 2 인지 확인`() {
        val expressionItems = ExpressionItems(
            listOf(Operand(1), Operation.Addition)
        )

        val actual = expression.addExpression(expressionItems, "2").getText()

        assertThat(actual).isEqualTo("1 + 2")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 +을추가하고 피연산자 2를추가하고 삭제를 호출하여 반환값이 1 + 인지 확인`() {
        val expressionItems = ExpressionItems(
            listOf(Operand(1), Operation.Addition, Operand(2))
        )

        val actual = expression.removeExpressionItem(expressionItems).getText()

        assertThat(actual).isEqualTo("1 + ")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 +을추가하고 삭제를 호출하여 반환값이 1 인지 확인`() {
        val expressionItems = ExpressionItems(
            listOf(Operand(1), Operation.Addition)
        )

        val actual = expression.removeExpressionItem(expressionItems).getText()

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `피연산자 1을추가하고 삭제를 호출하여 반환값이 빈문자인지 확인`() {
        val expressionItems = ExpressionItems(listOf(Operand(1)))

        val actual = expression.removeExpressionItem(expressionItems).getText()

        assertThat(actual).isEqualTo("")
    }
}
