package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MemoryCalculateStorageTest {
    private lateinit var storage: MemoryCalculateStorage

    @BeforeEach
    fun setUp() {
        storage = MemoryCalculateStorage()
    }

    @DisplayName("수식과 계산된 결과가 '수식\n= 결과' 형태로 정상 저장된다")
    @Test
    fun saveTest() {
        val formula = Expression(listOf(1, Operator.Plus, 5))
        val result = Expression(listOf(6))

        storage.save(formula, result)

        val actual = storage.history.first().toString()
        assertThat(actual).isEqualTo("1 + 5\n= 6")
    }

    @DisplayName("저장된 결과가 오래된 것이 가장 먼저 나오도록 조회된다")
    @Test
    fun multipleSaveTest() {
        initStorageBySave()

        val actual = storage.history.map { it.toString() }
        assertThat(actual).containsExactly(
            "1 + 5\n= 6",
            "10 - 2 * 5\n= 40",
            "1 + 5\n= 6"
        )
    }

    private fun initStorageBySave() =
        storage.apply {
                save(Expression(listOf(1, Operator.Plus, 5)), Expression(listOf(6)))
                save(Expression(listOf(10, Operator.Minus, 2, Operator.Multiply, 5)), Expression(listOf(40)))
                save(Expression(listOf(1, Operator.Plus, 5)), Expression(listOf(6)))
        }
}