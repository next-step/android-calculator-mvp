package com.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Test

class CalculatorTest {
    @Test
    fun `덧셈을 한다`() {
        val calculator = Calculator()
        assertThat(calculator.calculate("1 + 2")).isEqualTo(3)
        assertThat(calculator.calculate("2 + 3")).isEqualTo(5)
    }

    @Test
    fun `뺄셈을 한다`() {
        val calculator = Calculator()
        assertThat(calculator.calculate("3 - 2")).isEqualTo(1)
        assertThat(calculator.calculate("2 - 3")).isEqualTo(-1)
    }

    @Test
    fun `곱셈을 한다`() {
        val calculator = Calculator()
        assertThat(calculator.calculate("1 * 2")).isEqualTo(2)
        assertThat(calculator.calculate("1 * -2")).isEqualTo(-2)
        assertThat(calculator.calculate("1 * 0")).isEqualTo(0)
    }

    @Test
    fun `나눗셈을 한다`() {
        val calculator = Calculator()
        assertThat(calculator.calculate("6 / 2")).isEqualTo(3)
        assertThat(calculator.calculate("2 / 4")).isEqualTo(0)
    }

    @Test
    fun `나눗셈 0으로 나누는 경우에 ArithmeticException이 발생한다`() {
        Assert.assertThrows(ArithmeticException::class.java) {
            Calculator().calculate("2 / 0")
        }
    }
}