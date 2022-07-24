package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


internal class ExpressionHistorySaverTest {

    private val expressionHistory = ExpressionHistory()

    @Test
    fun `load nothing on empty history`() {
        // when
        val actual = expressionHistory.loadHistories()

        // then
        assertThat(actual).isEmpty()
    }

    @Test
    fun `save one pair of expression and result`() {
        // when
        expressionHistory.save("3 + 5", 8)

        // then
        val expected = listOf(
            ExpressionHistoryItem("3 + 5", 8)
        )
        assertThat(expressionHistory.loadHistories()).isEqualTo(expected)
    }

    @Test
    fun `save multiple pairs of expression and result`() {
        // when
        expressionHistory.save("3 + 5", 8)
        expressionHistory.save("10 * 6", 60)

        // then
        val expected = listOf(
            ExpressionHistoryItem("3 + 5", 8),
            ExpressionHistoryItem("10 * 6", 60)
        )
        assertThat(expressionHistory.loadHistories()).isEqualTo(expected)
    }
}
