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
        historyStorage = ExpressionHistoryStorage()
    }

    @Test
    fun 계산된_식을_정상적으로_저장하는지_확인한다() {
        //given
        val expression = Expression(listOf(2, Operator.Plus, 3))
        val result = calculator.calculate(expression.toString()) ?: throw IllegalArgumentException("식이 잘못되었습니다.")

        //when
        historyStorage.store(expression, result)

        //then
        assertThat(historyStorage.getHistory().size).isEqualTo(1)
    }
}