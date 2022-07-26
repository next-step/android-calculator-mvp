package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ExpressionHistoryStorageTest{

    lateinit var historyStorage: ExpressionHistoryStorage
    lateinit var calculator: Calculator

    @BeforeEach
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun 계산된_식을_정상적으로_저장하는지_확인한다() {
        //given
        val expected = 1
        val expression = Expression(listOf(1, Operator.Plus, 2))
        val history = ExpressionHistory(expression, 3)
        historyStorage = ExpressionHistoryStorage()

        //when
        historyStorage += history

        //then
        val actual = historyStorage.getHistory().size
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 동일한_식을_저장할_수_있는지_확인한다() {
        //given
        val expected = 2
        val expression = Expression(listOf(1, Operator.Plus, 2))
        val history = ExpressionHistory(expression, 3)
        historyStorage = ExpressionHistoryStorage(listOf(history))

        //when
        historyStorage += ExpressionHistory(expression, 3)

        //then
        val actual = historyStorage.getHistory().size
        assertThat(actual).isEqualTo(expected)
    }
}