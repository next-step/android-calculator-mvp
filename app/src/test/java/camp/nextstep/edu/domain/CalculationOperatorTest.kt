package camp.nextstep.edu.domain

import camp.nextstep.edu.domain.CalculationOperator.Companion.getCalculationResult
import org.junit.Assert.*
import org.junit.Test

class CalculationOperatorTest {
    @Test
    fun `사칙연산 기호가 아닌 다른 기호인 경우, IllegalArgumentException 이 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            val operator = "tan"
            val firstNumber = 30L
            val secondNumber = 1L
            getCalculationResult(
                firstNumber = firstNumber,
                secondNumber = secondNumber,
                operator = CalculationOperator.valueOf(operator),
            )
        }
    }

    @Test
    fun `정상적인 수식이 입력된 경우, 예외가 발생하지 않고 정상적인 연산 결과가 출력된다 - 1`() {
        val operator = "+"
        val firstNumber = 5L
        val secondNumber = 3L

        val result = getCalculationResult(
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            operator = CalculationOperator.valueOf(operator),
        )

        assertEquals(result, 8)
    }

    @Test
    fun `정상적인 수식이 입력된 경우, 예외가 발생하지 않고 정상적인 연산 결과가 출력된다 - 2`() {
        val operator = "-"
        val firstNumber = 3L
        val secondNumber = 5L

        val result = getCalculationResult(
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            operator = CalculationOperator.valueOf(operator),
        )
        assertEquals(result, -2)
    }

    @Test
    fun `정상적인 수식이 입력된 경우, 예외가 발생하지 않고 정상적인 연산 결과가 출력된다 - 3`() {
        val operator = "×"
        val firstNumber = 3L
        val secondNumber = 5L

        val result = getCalculationResult(
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            operator = CalculationOperator.valueOf(operator),
        )
        assertEquals(result, 15)
    }

    @Test
    fun `정상적인 수식이 입력된 경우, 예외가 발생하지 않고 정상적인 연산 결과가 출력된다 - 4`() {
        val operator = "÷"
        val firstNumber = 9L
        val secondNumber = 3L

        val result = getCalculationResult(
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            operator = CalculationOperator.valueOf(operator),
        )
        assertEquals(result, 3)
    }
}