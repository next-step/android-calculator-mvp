package com.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import java.lang.IllegalArgumentException

class CalculationExpressionTest {
    @Test
    fun `입력 값을 공백 기준으로 분리한다`() {
        val calculationExpression = CalculationExpression()
        val testInput = "1 + 2"
        val splitInput = calculationExpression.split(testInput)
        assertThat(splitInput?.size).isEqualTo(3)
    }

    @Test
    fun `입력 값이 null일 경우 IllegalArgumentException이 발생한다`() {
        val calculationExpression = CalculationExpression()
        val testInput = null

        assertThrows(IllegalArgumentException::class.java) {
            calculationExpression.split(testInput)
        }
    }

    @Test
    fun `입력 값이 공백일 경우 IllegalArgumentException이 발생한다`() {
        val calculationExpression = CalculationExpression()
        val testInput = "1 + 2 - "
        val splitInput = calculationExpression.split(testInput)

        assertThrows(IllegalArgumentException::class.java) {
            calculationExpression.validate(splitInput)
        }
    }
}