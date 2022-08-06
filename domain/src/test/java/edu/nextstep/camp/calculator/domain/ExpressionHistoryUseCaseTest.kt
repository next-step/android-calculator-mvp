package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionHistoryUseCaseTest {

    private val expressionHistoryUseCase = ExpressionHistoryUseCase()

    @Test
    fun 초기값은_비어있는_상태이다() {
        //when
        val actual = expressionHistoryUseCase.getHistories()
        //then
        assertThat(actual).isEmpty()
    }

    @Test
    fun 하나의_히스토리를_저장할_수_있다() {
        //when
        val expected = listOf(ExpressionHistory("1 + 2", 3))
        expressionHistoryUseCase.saveHistory(expected.first())
        //then
        val actual = expressionHistoryUseCase.getHistories()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 다수의_히스토리를_저장할_수_있다() {
        //when
        val expected = listOf(
            ExpressionHistory("1 + 2", 3),
            ExpressionHistory("2 + 6", 8)
        )
        expected.forEach { expression -> expressionHistoryUseCase.saveHistory(expression) }
        //then
        val actual = expressionHistoryUseCase.getHistories()
        assertThat(actual).isEqualTo(expected)
    }
}