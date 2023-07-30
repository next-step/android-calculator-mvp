package camp.nextstep.edu.calculator

import com.example.domain.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class OperatorTest {

	@Test
	fun `유효하지 않은 연산자 테스트`() {
		// given
		val op = "&"

		try {
			// when
			Operator.getOrThrow(op)
		} catch (e: IllegalArgumentException) {
			// then
			assertThat(e.message).isEqualTo(Operator.INVALID_OPERATOR)
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