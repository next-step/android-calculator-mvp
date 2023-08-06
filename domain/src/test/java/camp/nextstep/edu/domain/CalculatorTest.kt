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
	fun `계산식이 주어졌을때, 계산을 하면, 사칙연산 우선순위를 무시하고 계산한다`() {
		// given
		val expression = "2 + 3 * 4 / 2"

		// when
		val result = calculator.calculate(expression)

		// then
		assertThat(result).isEqualTo(10)
	}

	@Test
	fun `0으로 나누었을때, 계산을 하면, 예외를 발생한다`() {
		// given
		val expression = "4 / 0"

		// given
		assertThrows(ArithmeticException::class.java) {
			// when
			calculator.calculate(expression)
		}
	}
}