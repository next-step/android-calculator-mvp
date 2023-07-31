package camp.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class OperatorTest {

	@Test
	fun `유효하지 않은 연산자 테스트`() {
		// given
		val op = "&"

		// then
		assertThrows(
			Operator.INVALID_OPERATOR,
			IllegalArgumentException::class.java
		) {
			// when
			Operator.getOrThrow(op)
		}
	}

	@Test
	fun `유효한 연산자 테스트`() {
		// given
		val op = "*"

		// when
		val operator = Operator.getOrThrow(op)

		// then
		assertThat(operator).isEqualTo(Operator.MUL)
	}

	@Test
	fun `덧셈 테스트`() {
		// when
		val result = Operator.PLUS.evaluate(3, 2)

		// then
		assertThat(result).isEqualTo(5)
	}

	@Test
	fun `뺄셈 테스트`() {
		// when
		val result = Operator.MINUS.evaluate(3, 2)

		// then
		assertThat(result).isEqualTo(1)
	}

	@Test
	fun `곱셈 테스트`() {
		// when
		val result = Operator.MUL.evaluate(3, 2)

		// then
		assertThat(result).isEqualTo(6)
	}

	@Test
	fun `나눗셈 테스트`() {
		// when
		val result = Operator.DIV.evaluate(4, 2)

		// then
		assertThat(result).isEqualTo(2)
	}

	@Test
	fun `0으로 나눴을때 테스트`() {
		// then
		assertThrows(ArithmeticException::class.java) {
			// when
			Operator.DIV.evaluate(4, 0)
		}
	}
}