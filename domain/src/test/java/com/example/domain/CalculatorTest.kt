package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun `공백일 경우`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("")
        }
    }

    @Test
    fun `빈 공간이 들어갈 경우`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("  ")
        }
    }

    @Test
    fun `잘못된 수식일 경우`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("5 + + 5")
        }
    }

    @Test
    fun `잘못된 연산 기호가 들어 간 경우`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("5 % 5")
        }
    }

    @Test
    fun `연산 기호가 안 들어 간 경우`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("5 5 5")
        }
    }

    @Test
    fun `피연산자가 엄청 큰 수(Int 타입이 넘어설 경우)인 경우`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.evaluate("5555555555555555555 * 5")
        }
    }

    @Test
    fun `사칙 연산이 잘 계산된 경우`() {
        // when
        val actual = calculator.evaluate("1 + 1 - 2 + 3 / 3 * 2")

        // then
        assertEquals(2, actual)
    }
}

