package camp.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class ExpressionTest {

	private lateinit var expression: Expression

	@Before
	fun setUp() {
		expression = Expression()
	}

	@Test
	fun `계산식이 공백일 때, 계산식을 get 하면, 예외를 던진다`() {
		// given
		expression = Expression("  ")

		// then
		assertThrows(
			Expression.EXP_IS_BLANK,
			IllegalArgumentException::class.java
		) {
			// when
			expression.getCompleteExpression()
		}
	}

	@Test
	fun `계산식이 유효하지 않을 때, 계산식을 get 하면, 예외를 던진다`() {
		// given
		expression = Expression("1+2*")

		// then
		assertThrows(
			Expression.EXP_NOT_COMPLETE,
			IllegalStateException::class.java
		) {
			// when
			expression.getCompleteExpression()
		}
	}

	@Test
	fun `계산식이 유효할 때, 계산식을 get 하면, 계산식 문자열을 반환한다`() {
		// given
		expression = Expression("1+2*4")

		// when
		val expression = expression.getCompleteExpression()

		// then
		assertThat(expression).isEqualTo("1 + 2 * 4")
	}

	@Test
	fun `피연산자를 추가하면, 그대로 추가된다`() {
		// when
		expression = expression.insertOperand("1")

		// then
		assertThat(expression.toString()).isEqualTo("1")
	}

	@Test
	fun `계산식이 빈 값일 때, 연산자를 추가하면, 추가되지 않는다`() {
		// when
		expression.insertOperator(Operator.PLUS)

		// then
		assertThat(expression.toString()).isEqualTo("")
	}

	@Test
	fun `계산식이 빈 값일 아닐때, 연산자를 추가하면, 연산자가 추가된다`() {
		// given
		expression = Expression("1")

		// when
		expression = expression.insertOperator(Operator.PLUS)

		// then
		assertThat(expression.toString()).isEqualTo("1 + ")
	}

	@Test
	fun `연산자가 이미 존재할때, 연산자를 추가하면, 덮어씌여진다`() {
		// given
		expression = Expression("1+")

		// when
		expression = expression.insertOperator(Operator.MUL)

		// then
		assertThat(expression.toString()).isEqualTo("1 * ")
	}

	@Test
	fun `계산식이 빈 값일 때, 제거하면, 아무것도 제거되지 않는다`() {
		// when
		expression = expression.delete()

		// then
		assertThat(expression.toString()).isEqualTo("")
	}

	@Test
	fun `계산식이 빈 값이 아닐때, 피연산자를 제거하면, 그대로 제거된다`() {
		// given
		expression = Expression("1+2")

		// when
		expression = expression.delete()

		// then
		assertThat(expression.toString()).isEqualTo("1 + ")
	}

	@Test
	fun `계산식이 빈 값이 아닐때, 연산자를 제거하면, 연산자가 제거된다`() {
		// given
		expression = Expression("1+")

		// when
		expression = expression.delete()

		// then
		assertThat(expression.toString()).isEqualTo("1")
	}
}