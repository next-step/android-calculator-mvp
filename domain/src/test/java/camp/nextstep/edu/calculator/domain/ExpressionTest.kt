package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionTest {
    private lateinit var expression: Expression

    @Before
    fun initialize() {
        expression = Expression(InputTextConvertor(), ExpressionItems())
    }

    @Test
    fun `피연산자 1을추가하고 반환값이 1인지 확인`() {
        val input = "1"

        val actual = expression.addExpression("1")

        assertThat(actual).isEqualTo(input)
    }

    @Test
    fun `연산자 + 을추가하고 반환값이 빈문자인지 확인`() {
        val actual = expression.addExpression("+")

        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 + 을추가하고 반환값이 1 + 인지 확인`() {
        expression.addExpression("1")

        val actual = expression.addExpression("+")

        assertThat(actual).isEqualTo("1 + ")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 + 을추가하고 피연산자 2를추가하고 반환값이 1 + 2 인지 확인`() {
        expression.addExpression("1")
        expression.addExpression("+")

        val actual = expression.addExpression("2")

        assertThat(actual).isEqualTo("1 + 2")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 +을추가하고 피연산자 2를추가하고 삭제를 호출하여 반환값이 1 + 인지 확인`() {
        expression.addExpression("1")
        expression.addExpression("+")
        expression.addExpression("2")

        val actual = expression.removeExpressionItem()

        assertThat(actual).isEqualTo("1 + ")
    }

    @Test
    fun `피연산자 1을추가하고 연산자 +을추가하고 삭제를 호출하여 반환값이 1 인지 확인`() {
        expression.addExpression("1")
        expression.addExpression("+")

        val actual = expression.removeExpressionItem()

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `피연산자 1을추가하고 삭제를 호출하여 반환값이 빈문자인지 확인`() {
        expression.addExpression("1")

        val actual = expression.removeExpressionItem()

        assertThat(actual).isEqualTo("")
    }
}
