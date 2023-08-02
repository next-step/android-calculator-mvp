package com.nextstep.edu.domain

import org.junit.Assert.*
import org.junit.Test

class CalculatorTest {

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
    fun `1 더하기 2 더하기 3 = 5`() {
        // given
        val testParams = "1 + 2 + 3"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(6, actual)
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
    fun `3 빼기 2 빼기 1 = 0`() {
        // given
        val testParams = "3 - 2 - 1"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(0, actual)
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
    fun `2 곱하기 3 곱하기 4 = 24`() {
        // given
        val testParams = "2 * 3 * 4"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(24, actual)
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
    fun `6 나누기 3 나누기 2 = 1`() {
        // given
        val testParams = "6 / 3 / 2"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(1, actual)
    }

    @Test
    fun `1 더하기 3 곱하기 2 나누기 4 = 2`() {
        // given
        val testParams = "1 + 3 * 2 / 4"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(2, actual)
    }

    @Test
    fun `6 빼기 10 곱하기 2 나누기 2 = -4`() {
        // given
        val testParams = "6 - 10 * 2 / 2"
        // when
        val actual = calculator.evaluate(testParams)
        // then
        assertEquals(-4, actual)
    }

    @Test
    fun `연산이 없는 경우 IllegalArgumentException 을 던진다`() {
        // given
        val testParams = "2 3 3"
        // then
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate(testParams)
        }
    }

    @Test
    fun `연산으로 시작하는 경우 IllegalArgumentException 을 던진다`() {
        // given
        val testParams = "+ 3 3"
        // then
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate(testParams)
        }
    }

    @Test
    fun `연산으로 종료하는 경우 IllegalArgumentException 을 던진다`() {
        // given
        val testParams = "2 3 +"
        // then
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate(testParams)
        }
    }

    @Test
    fun `연산만 있는 경우 IllegalArgumentException 을 던진다`() {
        // given
        val testParams = "+ - +"
        // then
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate(testParams)
        }
    }
}