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
}