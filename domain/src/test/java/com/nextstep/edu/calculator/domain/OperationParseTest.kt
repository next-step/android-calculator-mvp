package com.nextstep.edu.calculator.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class OperationParseTest {

    @Test
    fun `정상적인 입력 테스트`() {
        assertEquals(3, OperationParse.parse("3"))
    }

    @Test
    fun `음수 입력 테스트`() {
        assertThrows(IllegalArgumentException::class.java) { OperationParse.parse("-3") }
    }

    @Test
    fun `숫자가 아닌 문자 입력 테스트`() {
        assertThrows(IllegalArgumentException::class.java) { OperationParse.parse("%^") }

    }
}