package com.nextstep.edu.calculator.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class OperatorTest {

    @Test
    fun `사칙연산이 아닌 경우 테스트`() {
        assertThrows(IllegalArgumentException::class.java) { Operator.find("&") }
    }

    @Test
    fun `더하기 테스트`() {
        val operator: Operator = Operator.find("+")
        val testResult = operator.operate(1, 1)
        assertEquals(2, testResult)
    }

    @Test
    fun `빼기 테스트`() {
        val operator: Operator = Operator.find("-")
        val testResult = operator.operate(8, 10)
        assertEquals(-2, testResult)
    }

    @Test
    fun `곱하기 테스트`() {
        val operator: Operator = Operator.find("*")
        val testResult = operator.operate(7, 7)
        assertEquals(49, testResult)
    }

    @Test
    fun `나누기 테스트`() {
        val operator: Operator = Operator.find("/")
        val testResult = operator.operate(8, 4)
        assertEquals(2, testResult)
    }
}