package camp.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class CalculatorTest {

	private lateinit var calculator: Calculator

	@Before
	fun setUp() {
		calculator = Calculator()
	}

	@Test
	fun `null 입력 테스트`() {
		// given
		val expression: String? = null

		// then
		assertThrows(
			Calculator.EXP_NULL_OR_BLANK,
			IllegalArgumentException::class.java
		) {
			// when
			calculator.calculate(expression)
		}
	}

	@Test
	fun `공백 입력 테스트`() {
		// given
		val expression = "    "

		// then
		assertThrows(
			Calculator.EXP_NULL_OR_BLANK,
			IllegalArgumentException::class.java
		) {
			// when
			calculator.calculate(expression)
		}
	}

	@Test
	fun `유효하지 않은 입력 테스트`() {
		// given
		val expression = "1 + 2 + "

		// then
		assertThrows(
			Calculator.EXP_NOT_COMPLETE,
			IllegalStateException::class.java
		) {
			// when
			calculator.calculate(expression)
		}
	}

	@Test
	fun `유효한 입력 테스트`() {
		// given
		val expression = "1 + 2 + 3 "

		// when
		val result = calculator.calculate(expression)

		// then
		assertThat(result).isEqualTo(6)
	}

	@Test
	fun `사칙연산 우선순위를 무시하고 계산하는지 테스트`() {
		// given
		val expression = "2 + 3 * 4 / 2"

		// when
		val result = calculator.calculate(expression)

		// then
		assertThat(result).isEqualTo(10)
	}
}