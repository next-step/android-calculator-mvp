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
        assertThat(calculationMemory.records.size).isEqualTo(1)
    }

    @Test
    fun `비어있지 않은 계산 기록에 기록을 추가하면 기록이 추가되어 저장된다`() {
        // given :
        val oldExpression = Expression(listOf(1, Operator.Plus, 2))
        val oldExpressionResult = 3
        val oldRecord = CalculationMemory.Record(oldExpression, oldExpressionResult)
        val calculationMemory = CalculationMemory(oldRecord)
        // when :
        val newExpression = Expression(listOf(2, Operator.Minus, 1))
        val newExpressionResult = 1
        calculationMemory.addRecord(newExpression, newExpressionResult)
        // then :
        val expectedExpression = Expression(listOf(2, Operator.Minus, 1))
        assertThat(calculationMemory.records.lastOrNull()?.expression).isEqualTo(expectedExpression)
        assertThat(calculationMemory.records.lastOrNull()?.result).isEqualTo(1)
        assertThat(calculationMemory.records.size).isEqualTo(2)
    }
}
