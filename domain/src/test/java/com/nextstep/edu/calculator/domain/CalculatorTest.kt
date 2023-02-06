package com.nextstep.edu.calculator.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun `널 테스트`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate(null) }
    }

    @Test
    fun `공백 테스트`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("") }
    }

    @Test
    fun `기호가 아닌 경우 테스트`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("5 # 3") }
    }

    @Test
    fun `사칙 연산을 모두 포함한 기능 테스트`() {
        val evaluate: Int = calculator.evaluate("3 + 2 - 1 * 4 / 8")
        assertEquals(2, evaluate)
    }

    @Test
    fun `1더하기 2더하기 3`() {
        val evaluate: Int = calculator.evaluate("1 + 2 + 3")
        assertEquals(6, evaluate)
    }

    @Test
    fun `아무값이나 입력한 경우`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("#%@#2 154345#$ $") }
    }

    @Test
    fun `음수값을 입력한 경우`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("-1 + -3") }
    }

    @Test
    fun `입력이 다 되지 않은 상태에서 계산한 경우`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("5 +") }
    }

    @Test
    fun `숫자만 입력한 경우`() {
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("5 5 5 5 5 5 5 5 5 5") }
    }
}