package com.nextstep.edu.calculator.domain

import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import org.junit.Before
import org.junit.Test

class ExpressionTest {
    private lateinit var expression: Expression

    @Before
    fun setUp() {
        expression = Expression()
    }

    @Test
    fun `입력된 피연산자가 없을 때 피연산자 입력`() {
        // when
        expression = expression.addOperand(5)
        // then
        assertThat(expression.toString()).isEqualTo("5")

        // when
        expression = expression.addOperator(Operator.Plus)
        // then
        assertThat(expression.toString()).isEqualTo("5 +")

        // when
        expression = expression.addOperand(1)
        // then
        assertThat(expression.toString()).isEqualTo("5 + 1")
    }

    @Test
    fun `입력된 피연산자가 있을 때 피연산자 입력`() {
        // when
        expression = expression.addOperand(8)
        expression = expression.addOperand(9)
        // then
        assertThat(expression.toString()).isEqualTo("89")
    }

    @Test
    fun `입력된 피연산자가 없을 때 연산자 입력`() {
        //when
        expression = expression.addOperator(Operator.Plus)
        //then
        assertThat(expression.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 피연산자가 있을 때 연산자 입력`() {
        //when
        expression = expression.addOperand(1)
        expression = expression.addOperator(Operator.Plus)
        //then
        assertThat(expression.toString()).isEqualTo("1 +")


        //when
        expression = expression.addOperator(Operator.Minus)
        //then
        assertThat(expression.toString()).isEqualTo("1 -")
    }

    @Test
    fun `입력된 수식이 없을 때 지우기를 하는 경우 아무런 변화가 없어야 한다`() {
        // when
        expression = expression.deleteOperations()
        //then
        assertThat(expression.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때 지우기를 하는 경우 연산자 혹은 피연산자가 지워져야 한다`() {
        // when
        expression = expression.addOperand(3)
        expression = expression.addOperand(2)
        expression = expression.addOperator(Operator.Plus)
        expression = expression.addOperand(1)
        expression = expression.deleteOperations()
        //then
        assertThat(expression.toString()).isEqualTo("32 +")


        // when
        expression = expression.deleteOperations()
        //then
        assertThat(expression.toString()).isEqualTo("32")

        // when
        expression = expression.deleteOperations()
        //then
        assertThat(expression.toString()).isEqualTo("3")

        // when
        expression = expression.deleteOperations()
        //then
        assertThat(expression.toString()).isEqualTo("")

        // when
        expression = expression.deleteOperations()
        //then
        assertThat(expression.toString()).isEqualTo("")
    }
}