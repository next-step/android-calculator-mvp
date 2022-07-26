package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorHistoryTest {
    private lateinit var calculatorHistory: CalculatorHistory

    @BeforeEach
    fun setUp() {
        calculatorHistory = CalculatorHistory()
    }

    @Test
    fun `계산 기록이 저장되어야 한다`() {
        // given
        val historyList = listOf(History("3 + 5", 8), History("10 - 3", 7))

        // when
        calculatorHistory.addCalculatorHistory(historyList)

        // then
        val actual = calculatorHistory.historyList
        assertThat(actual).isEqualTo(historyList)
    }
}