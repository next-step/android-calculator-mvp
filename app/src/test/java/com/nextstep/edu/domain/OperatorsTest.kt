package com.nextstep.edu.domain

import org.junit.Assert.*
import org.junit.Test

class OperatorsTest {
    private val calculator = Calculator

    @Test
    fun `2 더하기 3 = 5`() {
        // given
        val testParams = "2 + 3"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(5, actual)
    }

    @Test
    fun `3 빼기 2 = 1`() {
        // given
        val testParams = "3 - 2"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(1, actual)
    }

    @Test
    fun `2 곱하기 3 = 6`() {
        // given
        val testParams = "2 * 3"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(6, actual)
    }

    @Test
    fun `6 나누기 3 = 2`() {
        // given
        val testParams = "6 / 3"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(2, actual)
    }

    @Test
    fun `연산에 맞게 문자가 들어 오면 연산을 할 수 있다`() {
        assertEquals(Operators.Add.calculate(1, 2), calculator.evaluate("1 + 2"))
        assertEquals(Operators.Minus.calculate(1, 2), calculator.evaluate("1 - 2"))
        assertEquals(Operators.Multiply.calculate(1, 2), calculator.evaluate("1 * 2"))
        assertEquals(Operators.Divider.calculate(1, 2), calculator.evaluate("1 / 2"))
    }

    @Test
    fun `연산에 없는 문자가 들어오면 IllegalArgumentException 을 던진다`() {
        // given
        val testParams = "6 ! 3"

        // then
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate(testParams)
        }
    }
}