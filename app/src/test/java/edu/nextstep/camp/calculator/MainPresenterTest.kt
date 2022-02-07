package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var mockView: MainContract.View

    @BeforeEach
    fun setUp() {
        mockView = mockk()
        presenter = MainPresenter(mockView)
    }

    @ParameterizedTest(name = "{displayName}")
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    @DisplayName("초기상태에서 {0}숫자를 입력하면 입력한 {0} 숫자가 보인다.")
    fun whenDefaultStateInputOperandTest(operand: Int) {
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        presenter.addToExpression(operand)

        assertThat(expressionSlot.captured.toString()).isEqualTo(operand.toString())
    }

    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = ["+", "/", "*", "-"])
    @DisplayName("초기상태에서 {0}연산자를 입력하면 초기상태를 유지한다")
    fun whenDefaultStateInputOperatorTest(operatorArg: String) {
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        presenter.addToExpression(Operator.of(operatorArg)!!)

        assertThat(expressionSlot.captured).isEqualTo(Expression.EMPTY)
    }
}