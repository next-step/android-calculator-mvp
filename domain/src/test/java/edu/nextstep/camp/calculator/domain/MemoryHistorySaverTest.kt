package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


internal class MemoryHistorySaverTest {

    private val saver: CalculatorHistorySaver = MemoryHistorySaver()

    @Test
    fun `save one pair of expression and result`() {
        // when
        saver.save("3 + 5", "8")

        // then
        val expected = """
3 + 5
= 8
        """.trimIndent()
        assertThat(saver.loadAll()).isEqualTo(expected)
    }

    @Test
    fun `save multiple pairs of expression and result`() {
        // when
        saver.save("3 + 5", "8")
        saver.save("10 * 6", "60")

        // then
        val expected = """
3 + 5
= 8

10 * 6
= 60
        """.trimIndent()
        assertThat(saver.loadAll()).isEqualTo(expected)
    }

}