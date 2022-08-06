package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionHistoryUseCaseTest {

    private val expressionHistoryStorage = ExpressionHistoryStorage()

    @Test
    fun 초기값은_비어있는_상태이다() {
        //when
        val actual = expressionHistoryStorage.getHistories()
        //then
        assertThat(actual).isEmpty()
    }

    @Test
    fun 하나의_히스토리를_저장할_수_있다() {
        //when
        val expected = listOf(ExpressionHistory("1 + 2", 3))
        expressionHistoryStorage.saveHistory(expected.first())
        //then
        val actual = expressionHistoryStorage.getHistories()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 다수의_히스토리를_저장할_수_있다() {
        //when
        val expected = listOf(
            ExpressionHistory("1 + 2", 3),
            ExpressionHistory("2 + 6", 8)
        )
        expected.forEach { expression -> expressionHistoryStorage.saveHistory(expression) }
        //then
        val actual = expressionHistoryStorage.getHistories()
        assertThat(actual).isEqualTo(expected)
    }
}