package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

fun String.toOperator(): Operator {
    return Operator.getByPrime(this)
}

fun Int.toOperand(): Operand {
    return Operand(this)
}

class OperationParserTest {

    @Test
    fun `입력값이_빈_문자열_이면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse(" ")
        }
        assertEquals("빈 공백 혹은 문자열은 입력하실 수 없습니다", exception.message)
    }

    @Test
    fun `입력값이_빈_공백_이면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse(" ")
        }
        assertEquals("빈 공백 혹은 문자열은 입력하실 수 없습니다", exception.message)
    }

    @Test
    fun `항의_수가_짝수이면_완성되지_않은_수식이므로_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse("2 +")
        }
        assertEquals("완성되지 않은 수식입니다.", exception.message)
    }

    @Test
    fun `숫자를_연속으로_넣으면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse("2 2 2")
        }
        assertEquals("구현되지 않은 기호입니다.", exception.message)
    }

    @Test
    fun `기호를_연속으로_넣으면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse("2 + +")
        }
        assertEquals("숫자로 변환 불가능한 문자입니다.", exception.message)
    }

    @Test
    fun `2 더하기 3 곱하기 4 나누기 2 파싱`() {
        val result = OperationParser.parse("2 + 3 * 4 / 2")

        assertEquals(
            listOf(
                2.toOperand(),
                "+".toOperator(),
                3.toOperand(),
                "*".toOperator(),
                4.toOperand(),
                "/".toOperator(),
                2.toOperand()
            ),
            result
        )
    }
}