package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth

class HistoryTest {

    @org.junit.Test
    fun `히스토리가 비어있는 상태에서 레코드를 추가하면 히스토리에 추가되어야 한다`() {
        // given
        val history = History.EMPTY

        // when
        val actual = history + Record("1 + 2", "3")

        // then
        Truth.assertThat(actual.toString()).isEqualTo("1 + 2\n3\n")
    }

    @org.junit.Test
    fun `히스토리가 있는 상태에서 레코드를 추가하면 히스토리에 추가되어야 한다`() {
        // given
        val history = History(listOf(Record("1 + 2", "3")))

        // when
        val actual = history + Record("5 - 3", "2")

        // then
        Truth.assertThat(actual.toString()).isEqualTo("1 + 2\n3\n5 - 3\n2\n")
    }
}
