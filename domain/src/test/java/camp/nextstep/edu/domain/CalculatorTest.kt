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
	fun `사칙연산 우선순위를 무시하고 계산하는지 테스트`() {
		// given
		val expression = "2 + 3 * 4 / 2"

		// when
		val result = calculator.calculate(expression)

		// then
		assertThat(result).isEqualTo(10)
	}
}