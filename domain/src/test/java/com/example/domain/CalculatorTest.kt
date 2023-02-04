package com.example.domain

import org.junit.Assert.*

import org.junit.Test

class CalculatorTest {

    @Test
    fun `덧셈 테스트`() {
        val calculator = Calculator()

        val sum = calculator.sum(1, 1)
        assertEquals(2, sum)
    }

    @Test
    fun `뺄셈 테스트`() {
        val calculator = Calculator()

        val sub = calculator.sub(2, 1)
        assertEquals(1, sub)
    }

    @Test
    fun `곱셈 테스트`() {
        val calculator = Calculator()

        val mul = calculator.mul(3, 3)
        assertEquals(9, mul)
    }

    @Test
    fun `나눗셈 테스트`() {
        val calculator = Calculator()

        val div = calculator.div(4, 2)
        assertEquals(2, div)
    }


    @Test
    fun `나눗셈 0나누기 테스트`() {
        val calculator = Calculator()
        assertThrows(ArithmeticException::class.java) { calculator.div(4, 0) }
    }

    @Test
    fun `널 테스트`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate(null) }
    }

    @Test
    fun `공백 테스트`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("") }
    }

    @Test
    fun `기호가 아닌 경우 테스트`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("5 # 3") }
    }

    @Test
    fun `사칙 연산을 모두 포함한 기능 테스트`() {
        val calculator = Calculator()

        val evaluate: Int = calculator.evaluate("3 + 2 - 1 * 4 / 8")
        assertEquals(2, evaluate)
    }

    @Test
    fun `아무값이나 입력한 경우`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("#%@#2 154345#$ $") }
    }

    @Test
    fun `음수값을 입력한 경우`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("-1 + -3") }
    }

    @Test
    fun `입력이 다 되지 않은 상태에서 계산한 경우`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("5 +") }
    }

    @Test
    fun `숫자만 입력한 경우`() {
        val calculator = Calculator()
        assertThrows(IllegalArgumentException::class.java) { calculator.evaluate("5 5 5 5 5 5 5 5 5 5") }
    }
}