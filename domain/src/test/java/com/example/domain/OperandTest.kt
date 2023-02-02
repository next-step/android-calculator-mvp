package com.example.domain

import org.junit.Assert.*
import org.junit.Test

class OperandTest {

    @Test
    fun `양수를_통해_Operand_를_생성할_수_있다`() {
        val operand = Operand(1)

        assertTrue(operand is Operand)
        assertEquals(Operand(1), operand)
    }

    @Test
    fun `양수_문자열을_통해_Operand_를_생성할_수_있다`() {
        val operand = Operand.fromTerm("1")

        assertTrue(operand is Operand)
        assertEquals(Operand(1), operand)
    }

    @Test
    fun `음수를_넣으면_IllegalArgumentException_을_던진다`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Operand(-1)
        }
        assertEquals("음수를 지원 하지 않습니다.", exception.message)
    }
}