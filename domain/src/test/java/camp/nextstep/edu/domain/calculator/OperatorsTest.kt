package camp.nextstep.edu.domain.calculator

import org.junit.Assert.*
import org.junit.Test


class OperatorsTest {

    @Test
    fun `1 더하기 1 = 2`() {
        val result = Operators.Plus.calculate(1, 1)
        assertEquals(2, result)
    }

    @Test
    fun `1 빼기 1 = 0`() {
        val result = Operators.Minus.calculate(1, 1)
        assertEquals(0, result)
    }

    @Test
    fun `1 곱하기 1 = 1`() {
        val result = Operators.Multiply.calculate(1, 1)
        assertEquals(1, result)
    }

    @Test
    fun `1 나누기 1 = 1`() {
        val result = Operators.Divide.calculate(1, 1)
        assertEquals(1, result)
    }

    @Test
    fun `1 나누기 0 은 에러`() {
        assertThrows(ArithmeticException::class.java) {
            Operators.Divide.calculate(1, 0)
        }
    }

    @Test
    fun `입력된 기호가 유효한 연산기호라면 알맞게 파싱할 수 있다`() {
        assertEquals(Operators.Plus, Operators.of("+"))
        assertEquals(Operators.Minus, Operators.of("-"))
        assertEquals(Operators.Multiply, Operators.of("*"))
        assertEquals(Operators.Divide, Operators.of("/"))
    }

    @Test
    fun `지원하지 않는 연산기호를 입력하면 에러를 뱉는다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Operators.of("!")
        }
    }
}
