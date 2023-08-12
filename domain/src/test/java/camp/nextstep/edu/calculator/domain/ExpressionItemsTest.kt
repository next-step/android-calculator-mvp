package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionItemsTest {
    private lateinit var expressionItems: ExpressionItems

    @Before
    fun initialize() {
        expressionItems = ExpressionItems(emptyList())
    }

    @Test
    fun `피연산자 1을추가하고 반환값이 1인지 확인`() {
        val actual = (expressionItems + Operand(1)).getText()

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `연산자 + 을추가하고 반환값이 빈문자인지 확인`() {
        val actual = (expressionItems + Operation.Addition).getText()

        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 + 을추가하고 반환문자가 1 + 인지 확인`() {
        val actual = (expressionItems + Operand(1) + Operation.Addition).getText()

        assertThat(actual).isEqualTo("1 + ")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 + 을추가하고 피연산자 2를추가하고 반환값이 1 + 2 인지 확인`() {
        val actual = (
            expressionItems +
                Operand(1) +
                Operation.Addition +
                Operand(2)
            ).getText()

        assertThat(actual).isEqualTo("1 + 2")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 +을추가하고 피연산자 2를추가하고 삭제를 호출하여 반환값이 1 + 인지 확인`() {
        expressionItems = expressionItems + Operand(1) + Operation.Addition + Operand(2)

        val actual = expressionItems.removeLastExpression().getText()

        assertThat(actual).isEqualTo("1 + ")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 +을추가하고 삭제를 호출하여 반환값이 1 인지 확인`() {
        expressionItems = expressionItems + Operand(1) + Operation.Addition

        val actual = expressionItems.removeLastExpression().getText()

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `피연산자 1을추가하고 삭제를 호출하여 반환값이 빈문자인지 확인`() {
        expressionItems += Operand(1)

        val actual = expressionItems.removeLastExpression().getText()

        assertThat(actual).isEqualTo("")
    }
}
