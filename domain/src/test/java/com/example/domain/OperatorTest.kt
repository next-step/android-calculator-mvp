package com.example.domain

import com.example.util.ExceptionMessage.OPERATOR_NOT_EXIST
import org.junit.Assert.*
import org.junit.Test

class OperatorTest {

    @Test
    fun `없는 사칙연산으로 표현식이 만들어진 경우 예외처리`() {
        // when
        val error = assertThrows(IllegalArgumentException::class.java) {
            Operator.valueOf(1, 2, "%")
        }.message

        // then
        assertEquals(error, OPERATOR_NOT_EXIST)
    }
}