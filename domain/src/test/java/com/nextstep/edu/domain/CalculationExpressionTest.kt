package com.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import java.lang.IllegalArgumentException

class CalculationExpressionTest {
    @Test
    fun `입력 값을 공백 기준으로 분리한다`() {
        val calculationExpression = CalculationExpression("1 + 2")
        val splitInput = calculationExpression.value
        assertThat(splitInput.size).isEqualTo(3)
    }

    @Test
    fun `입력 값이 null일 경우 IllegalArgumentException이 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            CalculationExpression(null)
        }
    }

    @Test
    fun `입력 값이 공백일 경우 IllegalArgumentException이 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            CalculationExpression("")
        }
    }

    @Test
    fun `처음 값이 숫자가 아닐 경우 IllegalArgumentException이 발생한다`() {
        CalculationExpression("+")
    }

    @Test
    fun `마지막 값이 숫자가 아닐 경우 IllegalArgumentException이 발생한다`() {
        CalculationExpression("1 +")
    }
}