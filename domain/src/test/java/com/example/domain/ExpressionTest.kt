package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Test
class ExpressionTest {
    private var expression = Expression()
    @Test
    fun `Expression {피연산자, 연산자} 에서 피연산자가 들어올 경우`() {
        // given
        expression = expression.append(3)
        expression = expression.append(Operator.PLUS)

        // when
        expression = expression.append(3)

        // then
        assertEquals(listOf(3, Operator.PLUS, 3), expression.value())
    }

    @Test
    fun `Expression {피연산자} 에서 피연산자가 들어올 경우`() {
        // given
        expression = expression.append(3)

        // when
        expression = expression.append(3)

        // then
        assertEquals(listOf(33), expression.value())
    }

    @Test
    fun `Expression {연산자} 에서 피연산자가 들어올 경우`() {
        // given
        expression = expression.append(Operator.PLUS)
        // when
        expression = expression.append(1)

        // then
        assertEquals(listOf(1), expression.value())
    }

    @Test
    fun `Expression {피연산자, 연산자} 에서 연산자가 들어올 경우`() {
        // given
        expression = expression.append(3)
        expression = expression.append(Operator.PLUS)
        // when
        expression = expression.append(Operator.MULTI)

        // then
        assertEquals(listOf(3, Operator.MULTI), expression.value())
    }

    @Test
    fun `Expression {연산자} 에서 연산자가 들어올 경우`() {
        // given
        expression = expression.append(Operator.PLUS)

        // when
        expression = expression.append(Operator.MULTI)

        // then
        assertEquals(listOf(Operator.MULTI), expression.value())
    }

    @Test
    fun `Expression {피연산자} 에서 연산자가 들어올 경우`() {
        // given
        expression = expression.append(3)

        // when
        expression = expression.append(Operator.MULTI)

        // then
        assertEquals(listOf(3, Operator.MULTI), expression.value())
    }
}