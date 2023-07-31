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
            operator.getCalculationResult(firstNumber, secondNumber)
        }
    }

    @Test
    fun `5 + 3 이 들어온 경우 IllegalArgumentException 이 발생하지 않고 8이 리턴된다`() {
        val operator = "+"
        val firstNumber = 5L
        val secondNumber = 3L

        val result = operator.getCalculationResult(firstNumber, secondNumber)
        assertEquals(result, 8)
    }

    @Test
    fun `3 - 5 이 들어온 경우 IllegalArgumentException 이 발생하지 않고 -2가 리턴된다`() {
        val operator = "-"
        val firstNumber = 3L
        val secondNumber = 5L

        val result = operator.getCalculationResult(firstNumber, secondNumber)
        assertEquals(result, -2)
    }

    @Test
    fun `3 × 5 이 들어온 경우 IllegalArgumentException 이 발생하지 않고 15가 리턴된다`() {
        val operator = "×"
        val firstNumber = 3L
        val secondNumber = 5L

        val result = operator.getCalculationResult(firstNumber, secondNumber)
        assertEquals(result, 15)
    }

    @Test
    fun `9 ÷ 3 이 들어온 경우 IllegalArgumentException 이 발생하지 않고 3이 리턴된다`() {
        val operator = "÷"
        val firstNumber = 9L
        val secondNumber = 3L

        val result = operator.getCalculationResult(firstNumber, secondNumber)
        assertEquals(result, 3)
    }
}