package com.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import java.lang.IllegalArgumentException

class OperatorTest {
    @Test
    fun `입력 기호가 사칙연산 기호가 아닌 경우 IllegalArgumentException 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Operator.of("@")
        }
    }

    @Test
    fun `입력 기호가 + 기호인 경우 ADDITION을 반환한다`() {
        assertThat(Operator.of("+")).isEqualTo(Operator.ADDITION)
    }
}