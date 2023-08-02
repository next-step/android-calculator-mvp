package com.nextstep.edu.domain

import org.junit.Assert.*
import org.junit.Test

class CalculationExpressionTest {

    private val calculatorExpression = CalculatorExpression

    @Test
    fun `2 더하기 3`() {
        // given
        val testParams = "2 + 3"
        // when
        val actual = calculatorExpression.split(testParams)
        // then
        assertEquals(
            listOf("2", "+", "3"),
            actual
        )
    }

    @Test
    fun `2 빼기 3`() {
        // given
        val testParams = "2 - 3"
        // when
        val actual = calculatorExpression.split(testParams)
        // then
        assertEquals(
            listOf("2", "-", "3"),
            actual
        )
    }

    @Test
    fun `2 곱하기 3`() {
        // given
        val testParams = "2 * 3"
        // when
        val actual = calculatorExpression.split(testParams)
        // then
        assertEquals(
            listOf("2", "*", "3"),
            actual
        )
    }

    @Test
    fun `2 나누기 3`() {
        // given
        val testParams = "2 / 3"
        // when
        val actual = calculatorExpression.split(testParams)
        // then
        assertEquals(
            listOf("2", "/", "3"),
            actual
        )
    }

    @Test
    fun `문자열에 빈값이 입력하는 경우 IllegalArgumentException 을 던진다1`() {
        val testParams = listOf("2", "", "3")
        assertThrows(IllegalArgumentException::class.java) {
            calculatorExpression.validate(testParams)
        }
    }

    @Test
    fun `문자열에 빈값이 입력하는 경우 IllegalArgumentException 을 던진다2`() {
        val testParams = listOf("", "", "3")
        assertThrows(IllegalArgumentException::class.java) {
            calculatorExpression.validate(testParams)
        }
    }

    @Test
    fun `문자열에 빈값이 입력하는 경우 IllegalArgumentException 을 던진다3`() {
        val testParams = listOf("", "", "")
        assertThrows(IllegalArgumentException::class.java) {
            calculatorExpression.validate(testParams)
        }
    }

    @Test
    fun `빈 문자을 입력하는 경우 IllegalArgumentException 을 던진다`() {
        val testParams = ""
        assertThrows(IllegalArgumentException::class.java) {
            calculatorExpression.split(testParams)
        }
    }

    @Test
    fun `빈 문자열을 입력하는 경우 IllegalArgumentException 을 던진다`() {
        val testParams = "   "
        assertThrows(IllegalArgumentException::class.java) {
            calculatorExpression.split(testParams)
        }
    }
}