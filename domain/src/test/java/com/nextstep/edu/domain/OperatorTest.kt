package com.nextstep.edu.domain

import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException

class OperatorTest {
    @Test
    fun `입력 기호가 사칙연산 기호가 아닌 경우 IllegalArgumentException 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Operator.of("@")
        }
    }
}