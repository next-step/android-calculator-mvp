package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

internal class CalculatorMemoryTest {
    private lateinit var calculatorMemory: CalculatorMemory

    @Test
    internal fun `완성된 수식이 주어질 때 계산 기록이 저장되어야 한다`() {
        //given
        calculatorMemory = CalculatorMemory()
        val expression = Expression(listOf(3, Operator.Plus, 5))
        val result = 8

        //when
        calculatorMemory.saveExpressionRecord(expression, result)

        //then
        Truth.assertThat(calculatorMemory.toString()).isEqualTo("3 + 5\n= 8\n")
    }

    @Test
    internal fun `저장된 계산 수식을 가져올 수 있어야 한다`() {
        //given
        val expression1 = CalculatorMemory.SAVE_FORMAT.format(Expression(listOf(3, Operator.Plus, 5)), 8)
        val expression2 = CalculatorMemory.SAVE_FORMAT.format(Expression(listOf(4, Operator.Plus, 2)), 6)
        calculatorMemory = CalculatorMemory(mutableListOf(expression1, expression2))
        val expected = listOf(expression1, expression2)

        //when
        val actual = calculatorMemory.loadExpressionRecords().toList()

        //then
        Truth.assertThat(actual).isEqualTo(expected)
    }
}