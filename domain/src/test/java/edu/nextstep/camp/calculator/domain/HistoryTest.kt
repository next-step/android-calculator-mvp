package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HistoryTest {

    private lateinit var calculator: Calculator
    private lateinit var history: History

    @BeforeEach
    fun setUp() {
        calculator = Calculator()
        history = History()
    }

    @Test
    fun `계산결과를 추가 할 때마다 데이터가 누적된다`() {
        // Given
        val expression1 = Expression(listOf(32,
            Operator.Plus,
            1))
        val expression2 = Expression(listOf(33,
            Operator.Minus,
            3))

        // When
        history.addHistory(expression1, calculator.calculate(expression1.toString())?:0)
        history.addHistory(expression2, calculator.calculate(expression2.toString())?:0)
        val actual = history.getHistories()

        // Then
        assertThat(actual).isEqualTo(listOf(
            HistoryData(expression1, calculator.calculate(expression1.toString())?:0),
            HistoryData(expression2, calculator.calculate(expression2.toString())?:0)))
    }
}