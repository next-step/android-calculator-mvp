package edu.nextstep.camp.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

@Suppress("NonAsciiCharacters")
class CalculationHistoryManagerTest {

    private lateinit var calculationHistoryManager: CalculationHistoryManager

    @Before
    fun setup() {
        calculationHistoryManager = CalculationHistoryManager()
    }

    @Test
    fun `저장한 기록을 불러올 수 있어야한다`() {
        // given : 연산식과 연산 결과를 저장하고
        val calculationExpression = Expression.EMPTY + 1 + Operator.Plus + 2
        val calculationResult = 3

        calculationHistoryManager.saveCalculationHistory(calculationExpression, calculationResult)

        // when : 연산 기록을 다시 불러오면
        val result = calculationHistoryManager.getCalculationHistoryList()

        // then : 저장한 연산 기록을 리스트 형태로 반환해야 한다.
        assertThat(result).isEqualTo(listOf(CalculationHistory(calculationExpression, calculationResult)))
    }

    @Test
    fun `연산 기록을 순서대로 저장해야한다`() {
        // given : 여러 개의 연산식과 연산 결과를 저장하고
        val calculationExpression1 = Expression.EMPTY + 1 + Operator.Plus + 2
        val calculationResult1 = 3
        calculationHistoryManager.saveCalculationHistory(calculationExpression1, calculationResult1)

        val calculationExpression2 = Expression.EMPTY + 1 + Operator.Multiply + 2
        val calculationResult2 = 2
        calculationHistoryManager.saveCalculationHistory(calculationExpression2, calculationResult2)

        val calculationExpression3 = Expression.EMPTY + 1 + Operator.Minus + 2
        val calculationResult3 = -1
        calculationHistoryManager.saveCalculationHistory(calculationExpression3, calculationResult3)

        // when : 연산 기록을 다시 불러오면
        val result = calculationHistoryManager.getCalculationHistoryList()

        // then : 연산 기록이 저장한 순서대로 반환되어야 한다.
        val expected = listOf(
            CalculationHistory(calculationExpression1, calculationResult1),
            CalculationHistory(calculationExpression2, calculationResult2),
            CalculationHistory(calculationExpression3, calculationResult3)
        )
        assertThat(result).isEqualTo(expected)
    }

}