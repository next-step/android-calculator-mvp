package com.example.domain

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CalculatorTest {

    @Test
    fun `덧셈_1_더하기_1은_2`() {
        val calculator = Calculator()
        val result: Int = calculator.calculate("1 + 1")
        assertEquals(2, result)
    }

    @Test
    fun `뺄셈_1_빼기_1은_0`() {
        val calculator = Calculator()
        val result: Int = calculator.calculate("1 - 1")
        assertEquals(0, result)
    }

    @Test
    fun `곱셈_1_곱하기_1은_1`() {
        val calculator = Calculator()
        val result: Int = calculator.calculate("1 * 1")
        assertEquals(1, result)
    }

    @Test
    fun `나눗셈_1_나누기_1은_1`() {
        val calculator = Calculator()
        val result: Int = calculator.calculate("1 / 1")
        assertEquals(1, result)
    }

    @Test
    fun `2_더하기_3_곱하기_4_나누기_2는_10`() {
        val calculator = Calculator()
        val result: Int = calculator.calculate("2 + 3 * 4 / 2")
        assertEquals(10, result)
    }


    @Test
    fun `0으로_나누면_에러를_던진다`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("1 / 0")
        }
    }
}