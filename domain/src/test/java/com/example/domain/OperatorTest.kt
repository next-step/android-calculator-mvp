package com.example.domain

import org.junit.Assert.*
import org.junit.Test

class OperatorTest {

    @Test
    fun `없는 사칙연산 기호일 경우 예외처리`() {
        assertThrows(IllegalArgumentException::class.java) { Operator.of("?") }
    }

    @Test
    fun `있는 사칙연산 기호일 경우`() {
        // when
        val actual = Operator.of("+")

        // then
        assertEquals(Operator.PLUS, actual)
    }

    @Test
    fun `덧셈 테스트`() {
        // when
        val actual = Operator.of("+").evaluator(1, 2)

        // then
        assertEquals(3, actual)
    }

    @Test
    fun `뺄셈 테스트`() {
        // when
        val actual = Operator.of("-").evaluator(1, 2)

        // then
        assertEquals(-1, actual)
    }

    @Test
    fun `곱셈 테스트`() {
        // when
        val actual = Operator.of("*").evaluator(1, 2)

        // then
        assertEquals(2, actual)
    }

    @Test
    fun `나눗셈 테스트`() {
        // when
        val actual = Operator.of("/").evaluator(1, 2)

        // then
        assertEquals(0, actual)
    }
}