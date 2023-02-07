package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `1_더하기_1은_2`() {
        val result: Int = calculator.evaluate("1 + 1")
        assertEquals(2, result)
    }

    @Test
    fun `2_빼기_1은_1`() {
        val result: Int = calculator.evaluate("2 - 1")
        assertEquals(1, result)
    }

    @Test
    fun `2_곱하기_3은_6`() {
        val result: Int = calculator.evaluate("2 * 3")
        assertEquals(6, result)
    }

    @Test
    fun `6_나누기_3은_2`() {
        val result: Int = calculator.evaluate("6 / 3")
        assertEquals(2, result)
    }

    @Test
    fun `사칙연산_이외의_기호를_입력하면_IllegalArgumentException이_발생`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("2 ^ 3")
        }
    }

    @Test
    fun `0으로_나누면_ArithmeticException이_발생`() {
        assertThrows(ArithmeticException::class.java) {
            calculator.evaluate("1 / 0")
        }
    }

    @Test
    fun `공백_입력하면_IllegalArgumentException이_발생`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("")
        }
    }

    @Test
    fun `null_입력하면_IllegalArgumentException이_발생`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate(null)
        }
    }

    @Test
    fun `사칙연산_모두_포함하는_연산_테스트`() {
        val result: Int = calculator.evaluate("2 + 1 * 3 / 3")
        assertEquals(3, result)
    }

    @Test
    fun `수식이_미완성일_경우_IllegalArgumentException이_발생`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("1 +")
        }
    }
}