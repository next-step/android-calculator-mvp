package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class ExpressionTest {
    private lateinit var expression: Expression

    @Test
    fun `마지막에 연산자가 입력된 상태에서 피연산자가 입력되면 기존 수식에 피연산자를 추가한다`() {
        // given
        expression = Expression("1 + 3 + ")
        // when
        expression.setOperand("4")
        val expected = "1 + 3 + 4"
        // then
        assertThat(expression.value).isEqualTo(expected)
    }

    @Test
    fun `마지막에 피연산자가 입력된 상태에서 피연산자가 입력되면 기존 수식에 피연산자를 추가한다`() {
        // given
        expression = Expression("1 + 3")
        // when
        expression.setOperand("4")
        val expected = "1 + 34"
        // then
        assertThat(expression.value).isEqualTo(expected)
    }

    @Test
    fun `수식이 비어있는 상태에서 연산자가 입력되면 아무런 값도 추가되지 않는다`() {
        // given
        expression = Expression("")
        // when
        expression.setOperator("+")
        val expected = ""
        // then
        assertThat(expression.value).isEqualTo(expected)
    }

    @Test
    fun `피연산자가 있는 상태에서 연산자가 입력되면 기존 수식에 연산자를 추가한다`() {
        // given
        expression = Expression("3")
        // when
        expression.setOperator("+")
        val expected = "3 + "
        // then
        assertThat(expression.value).isEqualTo(expected)
    }

    @Test
    fun `연산자가 있는 상태에서 연산자가 입력되면 기존 수식에 있던 연산자를 지우고 입력된 연산자를 추가한다`() {
        // given
        expression = Expression("3 + ")
        // when
        expression.setOperator("-")
        val expected = "3 - "
        // then
        assertThat(expression.value).isEqualTo(expected)
    }

    @Test
    fun `Equals가 입력되면 기존 수식에 계산한 값이 설정된다`() {
        // given
        expression = Expression("1 + 2 + 3")
        // when
        expression.setEquals("6")
        val expected = "6"
        // then
        assertThat(expression.value).isEqualTo(expected)
    }

    @Test
    fun `삭제가 입력되면 기존 수식의 마지막 값이 지워진다`() {
        // given
        expression = Expression("1 + 2 + ")
        // when
        expression.setDelete()
        val expected = "1 + 2"
        // then
        assertThat(expression.value).isEqualTo(expected)
    }
}
