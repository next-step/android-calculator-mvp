package com.example.domain

import com.example.util.ExceptionMessage.OPERATOR_DIVIDE_NOT_ZERO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun `0으로 나누었을 경우 예외처리`() {
        // when
        val error = assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("1 / 0 + 3")
        }.message

        // then
        assertEquals(error, OPERATOR_DIVIDE_NOT_ZERO)
    }

    @Test
    fun `올바른 덧셈 표현식 1 + 2 + 3 = ?`() {
        // when
        val actual: Int = calculator.evaluate("1 + 2 + 3")

        // then
        assertEquals(6, actual)
    }

    @Test
    fun `올바른 뺄셈 표현식 1 - 2 - 3 = ?`() {
        // when
        val actual: Int = calculator.evaluate("1 - 2 - 3")

        // then
        assertEquals(-4, actual)
    }

    @Test
    fun `올바른 곱셈 표현식 1 * 2 * 3 = ?`() {
        // when
        val actual: Int = calculator.evaluate("1 * 2 * 3")

        // then
        assertEquals(6, actual)
    }

    @Test
    fun `올바른 나눗셈 표현식 1 + 2 ÷ 3 = ?`() {
        // when
        val actual: Int = calculator.evaluate("1 + 2 / 3")

        // then
        assertEquals(1, actual)
    }
}