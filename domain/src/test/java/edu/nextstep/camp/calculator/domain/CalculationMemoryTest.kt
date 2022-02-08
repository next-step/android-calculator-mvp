package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.*
import org.junit.jupiter.api.Test

class CalculationMemoryTest {
    @Test
    fun `메모리에 계산 기록을 추가하면 기록이 추가되어 저장된다`() {
        // given :
        val calculationMemory = CalculationMemory()
        val newExpression = Expression(listOf(1, Operator.Plus, 2))
        val newExpressionResult = 3
        // when :
        calculationMemory.addRecord(newExpression, newExpressionResult)
        // then :
        val expectedExpression = Expression(listOf(1, Operator.Plus, 2))
        assertThat(calculationMemory.records.lastOrNull()?.expression).isEqualTo(expectedExpression)
        assertThat(calculationMemory.records.lastOrNull()?.result).isEqualTo(3)
    }
}
