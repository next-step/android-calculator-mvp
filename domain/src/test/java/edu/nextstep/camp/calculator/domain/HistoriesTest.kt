package edu.nextstep.camp.calculator.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

import org.junit.jupiter.api.Test

internal class HistoriesTest {
    private val calculator = Calculator()
    private lateinit var histories: Histories

    @BeforeEach
    fun setup() {
        histories = Histories()
    }

    @DisplayName("3 + 5 = 와 10 - 3 = 를 누른 결과에 대한 기록을 조회할 수 있어야 한다.")
    @Test
    fun add() {
        // given
        listOf("3 + 5", "10 - 3").forEach {
            histories.add(it, calculator.calculate(it))
        }

        // when
        val actual = histories.toList()
        val expected = listOf(
            History("3 + 5", 8),
            History("10 - 3", 7)
        )

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
