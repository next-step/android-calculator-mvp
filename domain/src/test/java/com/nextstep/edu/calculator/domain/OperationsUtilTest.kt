package com.nextstep.edu.calculator.domain

import com.nextstep.edu.calculator.domain.OperationsUtil.deleteOperations
import com.nextstep.edu.calculator.domain.OperationsUtil.setOperationsNumber
import com.nextstep.edu.calculator.domain.OperationsUtil.setOperationsOperator
import junit.framework.Assert.assertEquals
import org.junit.Test

class OperationsUtilTest {

    @Test
    fun `입력된 피연산자가 없을 때 피연산자 입력`() {
        // when
        var result = setOperationsNumber("", "1")

        // then
        assertEquals("1", result)

        // when
        result = setOperationsNumber("5 + ", "1")

        // then
        assertEquals("5 + 1", result)
    }

    @Test
    fun `입력된 피연산자가 있을 때 피연산자 입력`() {
        // when
        val result = setOperationsNumber("8", "9")

        // then
        assertEquals("89", result)
    }

    @Test
    fun `입력된 피연산자가 없을 때 연산자 입력`() {
        //when
        val result = setOperationsOperator("", "+")

        //then
        assertEquals("", result)
    }

    @Test
    fun `입력된 피연산자가 있을 때 연산자 입력`() {
        //when
        var result = setOperationsOperator("1", "+")

        //then
        assertEquals("1 + ", result)
        //when
        result = setOperationsOperator("1 +", "-")

        //then
        assertEquals("1 - ", result)
    }

    @Test
    fun `입력된 수식이 없을 때 지우기를 하는 경우 아무런 변화가 없어야 한다`() {
        // when
        val result = deleteOperations("")
        //then

        assertEquals("", result)
    }

    @Test
    fun `입력된 수식이 있을 때 지우기를 하는 경우 연산자 혹은 피연산자가 지워져야 한다`() {
        // when
        var result = "32 + 1"
        result = deleteOperations(result)

        //then
        assertEquals("32 + ", result)


        // when
        result = deleteOperations(result)

        //then
        assertEquals("32", result)


        // when
        result = deleteOperations(result)

        //then
        assertEquals("3", result)


        // when
        result = deleteOperations(result)

        //then
        assertEquals("", result)


        // when
        result = deleteOperations(result)

        //then
        assertEquals("", result)
    }
}