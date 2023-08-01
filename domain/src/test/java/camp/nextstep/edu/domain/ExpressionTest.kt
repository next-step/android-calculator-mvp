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
		// then
		assertThrows(
			Expression.EXP_IS_BLANK,
			IllegalArgumentException::class.java
		) {
			// when
			expression.getOrThrow()
		}
	}

	@Test
	fun `계산식이 유효하지 않을 때, 계산식을 get 하면, 예외를 던진다`() {
		// given
		expression.insertOperand("1")
		expression.insertOperator("+")
		expression.insertOperand("2")
		expression.insertOperator("*")

		// then
		assertThrows(
			Expression.EXP_NOT_COMPLETE,
			IllegalStateException::class.java
		) {
			// when
			expression.getOrThrow()
		}
	}

	@Test
	fun `계산식이 유효할 때, 계산식을 get 하면, 계산식 문자열을 반환한다`() {
		// given
		expression.insertOperand("1")
		expression.insertOperator("+")
		expression.insertOperand("2")
		expression.insertOperator("*")
		expression.insertOperand("4")

		// when
		val expression = expression.getOrThrow()

		// then
		assertThat(expression).isEqualTo("1 + 2 * 4")
	}
}