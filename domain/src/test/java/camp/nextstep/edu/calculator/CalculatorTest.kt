package camp.nextstep.edu.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun `덧셈 연산 결과를 얻는다`() {
        val calculator = Calculator()

        val actual = calculator.run("1 + 2")

        val expected = 3
        assertEquals(expected, actual)
    }

    @Test
    fun `뺄셈 연산 결과를 얻는다`() {
        val calculator = Calculator()

        val actual = calculator.run("1 - 2")

        val expected = -1
        assertEquals(expected, actual)
    }

    @Test
    fun `나눗셈 연산 결과를 얻는다`() {
        val calculator = Calculator()

        val actual = calculator.run("10 ÷ 2")

        val expected = 5
        assertEquals(expected, actual)
    }

    @Test
    fun `곱셉 연산 결과를 얻는다`() {
        val calculator = Calculator()

        val actual = calculator.run("1 × 2")

        val expected = 2
        assertEquals(expected, actual)
    }

    @Test
    fun `모든 사칙 연산 값에 대한 연산 결과를 얻는다`() {
        val calculator = Calculator()

        val actual = calculator.run("2 + 3 × 4 ÷ 2")

        val expected = 10
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `null을 입력 했을 때 IllegalArgumentException exception 반환 한다`() {
        val calculator = Calculator()

        calculator.run(null)

    }

    @Test(expected = IllegalArgumentException::class)
    fun `빈 공백을 입력 했을 때 IllegalArgumentException exception 반환 한다`() {
        val calculator = Calculator()

        calculator.run(" ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `사칙 연산이 아닐 경우  IllegalArgumentException exception 반환 한다`() {
        val calculator = Calculator()

        calculator.run("1 & 2 × 3")

    }

    @Test(expected = IllegalArgumentException::class)
    fun `유효하지 않은 수식에 대해서 IllegalArgumentException exception을 반환 한다`() {
        val calculator = Calculator()
        calculator.run("+ 1 - × 3")
    }


}