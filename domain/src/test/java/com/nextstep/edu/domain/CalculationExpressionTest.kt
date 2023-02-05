package com.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculationExpressionTest {
    @Test
    fun `입력 값을 공백 기준으로 분리한다`() {
        val calculationExpression = CalculationExpression()
        val testInput = "1 + 2"
        val splitInput = calculationExpression.split(testInput)
        assertThat(splitInput.size).isEqualTo(3)
    }
}