package camp.nextstep.edu.domain

import org.junit.Assert.*
import org.junit.Test

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun `5 더하기 3를 하면 8이 나온다`() {
        calculator.addNumberOnExpressions(5)
        calculator.addBasicOperationOnExpression("+")
        calculator.addNumberOnExpressions(3)
        calculator.execute()

        assertEquals(calculator.getExpressions(), "8")
    }

    @Test
    fun `5 빼기 3을 하면 2가 나온다`() {
        calculator.addNumberOnExpressions(5)
        calculator.addBasicOperationOnExpression("-")
        calculator.addNumberOnExpressions(3)
        calculator.execute()

        assertEquals(calculator.getExpressions(), "2")
    }

    @Test
    fun `5 곱하기 3을 하면 15가 나온다`() {
        calculator.addNumberOnExpressions(5)
        calculator.addBasicOperationOnExpression("×")
        calculator.addNumberOnExpressions(3)
        calculator.execute()

        assertEquals(calculator.getExpressions(), "15")
    }

    @Test
    fun `5 나누기 3을 하면 1이 나온다`() {
        calculator.addNumberOnExpressions(5)
        calculator.addBasicOperationOnExpression("÷")
        calculator.addNumberOnExpressions(3)
        calculator.execute()

        assertEquals(calculator.getExpressions(), "1")
    }

    @Test
    fun `입력값이 null 이면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.addNumberOnExpressions(null)
            calculator.execute()
        }
    }

    @Test
    fun `입력값이 공백 문자이면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.addNumberOnExpressions(5)
            calculator.addBasicOperationOnExpression("")
            calculator.addNumberOnExpressions(5)
            calculator.execute()
        }
    }

    @Test
    fun `사칙연산 기호가 아닌 경우 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.addNumberOnExpressions(5)
            calculator.addBasicOperationOnExpression("?")
            calculator.addNumberOnExpressions(5)
            calculator.execute()
        }
    }

    @Test
    fun `2 더하기 8 나누기 2 곱하기 5 의 결과값은 25 이다`() {
        calculator.addNumberOnExpressions(2)
        calculator.addBasicOperationOnExpression("+")
        calculator.addNumberOnExpressions(8)
        calculator.addBasicOperationOnExpression("÷")
        calculator.addNumberOnExpressions(2)
        calculator.addBasicOperationOnExpression("×")
        calculator.addNumberOnExpressions(5)
        calculator.execute()

        assertEquals(calculator.getExpressions(), "25")
    }
}