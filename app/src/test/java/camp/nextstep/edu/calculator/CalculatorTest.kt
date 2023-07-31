package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.Calculator
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.fail
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

		try {
			// when
			calculator.calculate(expression)
			fail()
		} catch (e: IllegalArgumentException) {
			// then
			assertThat(e.message).isEqualTo(Calculator.EXP_NULL_OR_BLANK)
		}
	}

	@Test
	fun `공백 입력 테스트`() {
		// given
		val expression = "    "

		try {
			// when
			calculator.calculate(expression)
			fail()
		} catch (e: IllegalArgumentException) {
			// then
			assertThat(e.message).isEqualTo(Calculator.EXP_NULL_OR_BLANK)
		}
	}

	@Test
	fun `유효하지 않은 입력 테스트`() {
		// given
		val expression = "1 + 2 + "

		try {
			// when
			calculator.calculate(expression)
			fail()
		} catch (e: IllegalStateException) {
			// then
			assertThat(e.message).isEqualTo(Calculator.EXP_NOT_COMPLETE)
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

	@Test(expected = ArithmeticException::class)
	fun `0으로 나눴을 때 테스트`() {
		// given
		val expression = "2 + 2 / 0"

		// when
		calculator.calculate(expression)

		// then: throw ArithmeticException
	}
}