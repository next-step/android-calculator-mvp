package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MemoryTest {

    @Test
    fun `빈 메모리일때, 메모리에 값을 추가 할 수 있어야 한다`() {
        // given
        val memory = Memory.EMPTY
        val item = "3 + 5" to 8

        // when
        val actual = memory + item

        // then
        assertThat(actual.toString()).isEqualTo(item)
    }

    @Test
    fun `메모리에 값이 있는 경우, 메모리에 값 추가시 연속적으로 추가될 수 있어야 한다`() {
        // given
        val memory = Memory(listOf("3 + 5" to 8))
        val item = ("6 / 2" to 3)

        // when
        val actual = memory + item

        // then
        assertThat(actual.toString()).isEqualTo("3 + 5\n= 8\n\n6 / 2\n= 3\n")
    }
}