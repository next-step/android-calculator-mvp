package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.CalculateHistory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CalculatorHistoryTest {
    private lateinit var calculateHistory: CalculateHistory

    @BeforeEach
    fun setup() {
        calculateHistory = CalculateHistory()
    }

    @Test
    fun `수식과 결과가 입력되면 수식과 결과가 저장된 객체를 생성하여 저장한다`() {
        calculateHistory.putCalculateHistory(Expression(listOf(1, Operator.Plus, 1)), 2)
        calculateHistory.putCalculateHistory(Expression(listOf(5, Operator.Minus, 3)), 2)
        calculateHistory.putCalculateHistory(Expression(listOf(2, Operator.Multiply, 3)), 6)

        assertAll({
            assertThat(calculateHistory.calculateHistories[0].expression.toString()).isEqualTo("1 + 1")
            assertThat(calculateHistory.calculateHistories[0].result).isEqualTo(2)
        }, {
            assertThat(calculateHistory.calculateHistories[1].expression.toString()).isEqualTo("5 - 3")
            assertThat(calculateHistory.calculateHistories[1].result).isEqualTo(2)

        }, {
            assertThat(calculateHistory.calculateHistories[2].expression.toString()).isEqualTo("2 * 3")
            assertThat(calculateHistory.calculateHistories[2].result).isEqualTo(6)
        })
    }
}
