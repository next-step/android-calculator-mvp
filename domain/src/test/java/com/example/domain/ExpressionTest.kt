package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ExpressionTest {
    private var expression = Expression()

    @Test
    fun `아무런 입력 값이 없는 상태에서 피연산자가 들어올 경우, 피연산자 값이 만들어진다`() {
        // given
        expression = expression.plus(3)

        // then
        assertEquals("3", expression.value())
    }

    @Test
    fun `아무런 입력 값이 없는 상태에서 연산자가 들어올 경우, 값이 없다`() {
        // given
        expression = expression.plus(Operator.MINUS)

        // then
        assertEquals("", expression.value())
    }

    @Test
    fun `피연산자가 있는 상태에서 피연산자가 들어올 경우, 피연산자 두 개가 붙어 하나의 피연산자가 만들어진다`() {
        // given
        expression = expression.plus(3)

        // when
        expression = expression.plus(3)

        // then
        assertEquals("33", expression.value())
    }

    @Test
    fun `피연산자가 있는 상태에서 연산자가 들어올 경우, 연산자가 만들어진다`() {
        // given
        expression = expression.plus(3)

        // when
        expression = expression.plus(Operator.MULTI)

        // then
        assertEquals("3 *", expression.value())
    }

    @Test
    fun `피연산자, 연산자가 있는 상태에서 연산자가 들어올 경우, 이전 연산자가 사라지고 새로운 연산자가 만들어진다`() {
        // given
        expression = expression.plus(3)
        expression = expression.plus(Operator.PLUS)

        // when
        expression = expression.plus(Operator.MULTI)

        // then
        assertEquals("3 *", expression.value())
    }

    @Test
    fun `아무런 값이 없는 상태에서 지우기를 하는 경우, 변함없이 아무런 값이 없다`() {
        // when
        expression = expression.removeLastValue()

        // then
        assertEquals("", expression.value())
    }

    @Test
    fun `피연산자와 연산자가 있는 상태에서 지우기를 하는 경우, 연산자가 지워지고 피연산자만 남는다`() {
        // given
        expression = expression.plus(3)
        expression = expression.plus(Operator.PLUS)

        // given
        expression = expression.removeLastValue()

        // then
        assertEquals("3", expression.value())
    }

    @Test
    fun `피연산자와 피연산자가 있는 상태에서 지우기를 하는 경우, 피연산자 하나만 남는다`() {
        // given
        expression = expression.plus(3)
        expression = expression.plus(3)

        // given
        expression = expression.removeLastValue()

        // then
        assertEquals("3", expression.value())
    }

    @Test
    fun `피연산자와 연산자와 피연산자가 있는 상태에서 지우기를 하는 경우, 마지막 피연산자 하나만 지워진다`() {
        // given
        expression = expression.plus(3)
        expression = expression.plus(Operator.PLUS)
        expression = expression.plus(3)

        // given
        expression = expression.removeLastValue()

        // then
        assertEquals("3 +", expression.value())
    }
}