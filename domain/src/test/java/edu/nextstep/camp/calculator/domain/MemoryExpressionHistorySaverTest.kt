package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


internal class MemoryExpressionHistorySaverTest {

    private val saver: ExpressionHistory = MemoryExpressionHistory()

    @Test
    fun `load nothing on empty history`() {
        // when
        val actual = saver.loadHistories()

        // then
        assertThat(actual).isEmpty()
    }


    @Test
    fun `save one pair of expression and result`() {
        // when
        saver.save("3 + 5", 8)

        // then
        val expected = listOf(
            "3 + 5" to 8,
        )
        assertThat(saver.loadHistories()).isEqualTo(expected)
    }

    @Test
    fun `save multiple pairs of expression and result`() {
        // when
        saver.save("3 + 5", 8)
        saver.save("10 * 6", 60)

        // then
        val expected = listOf(
            "3 + 5" to 8,
            "10 * 6" to 60,
        )
        assertThat(saver.loadHistories()).isEqualTo(expected)
    }
}
