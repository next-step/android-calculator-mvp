package com.example.domain

import com.example.util.ExceptionMessage.INCORRECT_EXPRESSION
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class ExpressionTest {

    @Test
    @Parameters("+", "-", "/", "*")
    fun `틀린 표현식 - 첫 표현식 글자가 사칙 연산자 인 경우 `(input: String) {
        // given
        val expression = Expression()

        // when
        val error = assertThrows(IllegalArgumentException::class.java) {
            expression.append(input)
        }.message

        // then
        assertEquals(error, INCORRECT_EXPRESSION)
    }

    @Test
    fun `표현식만들기 - 피연산자 추가되는 경우`() {
        // given
        val input = 3

        // when
        val expression = Expression()
        expression.append(input)

        // then
        val expected = "3"
        assertEquals(expected, expression.value())
    }

    @Test
    fun `표현식만들기 - 연산자 추가되는 경우`() {
        // given
        val expression = Expression()
        expression.append(3)
        val input = "+"

        // when
        expression.append(input)

        // then
        val expected = "3 +"
        assertEquals(expected, expression.value())
    }
}