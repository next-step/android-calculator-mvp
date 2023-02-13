package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ExpressionTest {

    @Test
    fun `1이_있을때_더하기_버튼을_누르면_1_더하기가_된다`() {
        var actualExpression = Expression(listOf(1))
        actualExpression = actualExpression.appendOperator(Operator.PLUS)

        assertEquals(Expression(listOf(1, Operator.PLUS)), actualExpression)
    }

    @Test
    fun `1_더하기가_있을때_빼기_버튼을_누르면_1_빼기가_된다`() {
        var actualExpression = Expression(listOf(1, Operator.PLUS))
        actualExpression = actualExpression.appendOperator(Operator.MINUS)

        assertEquals(Expression(listOf(1, Operator.MINUS)), actualExpression)
    }

    @Test
    fun `1_더하기가_있을때_2를_누르면_1_더하기_2가_된다`() {
        var actualExpression = Expression(listOf(1, Operator.PLUS))
        actualExpression = actualExpression.appendOperand(3)

        assertEquals(Expression(listOf(1, Operator.PLUS, 3)), actualExpression)
    }

    @Test
    fun `8이_있을때_9를_누르면_89가_된다`() {
        var actualExpression = Expression(listOf(8))
        actualExpression = actualExpression.appendOperand(9)

        assertEquals(Expression(listOf(89)), actualExpression)
    }

    @Test
    fun `공백일때_1을_누르면_1이_된다`() {
        var actualExpression = Expression(listOf())
        actualExpression = actualExpression.appendOperand(1)

        assertEquals(Expression(listOf(1)), actualExpression)
    }

    @Test
    fun `공백일때_더하기_버튼을_누르면_공백이_된다`() {
        var actualExpression = Expression(listOf())
        actualExpression = actualExpression.appendOperator(Operator.PLUS)
        assertEquals(Expression(listOf()), actualExpression)
    }

    @Test
    fun `공백일때_지우기_버튼을_누르면_공백이_된다`() {
        var actualExpression = Expression(listOf())
        actualExpression = actualExpression.removeLastValue()
        assertEquals(Expression(listOf()), actualExpression)
    }

    @Test
    fun `89가_있을때_지우기_버튼을_누르면_8이_된다`() {
        var actualExpression = Expression(listOf(89))
        actualExpression = actualExpression.removeLastValue()

        assertEquals(Expression(listOf(8)), actualExpression)
    }

    @Test
    fun `1_더하기_2가_있을때_1_+_2가_된다`() {
        val actualExpression = Expression(listOf(1, Operator.PLUS, 2))
        assertEquals(actualExpression.getExpressions(), "1 + 2")
    }
}