package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View
    private lateinit var expression: Expression

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        expression = Expression()
        presenter = MainPresenter(view, expression)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    @DisplayName("피연산자가 입력이 된다면, 해당 값을 수식에 추가하고, 화면에 그 수식을 보여줘야한다.")
    internal fun addOperand(operand: Int) {
        // when
        presenter.addOperand(operand)

        // then
        verify(exactly = 1) { view.showCurrentExpression(any()) }
    }

    @ParameterizedTest
    @EnumSource(value = Operator::class)
    @DisplayName("숫자 뒤 연산자를 입력하면 연산자가 수식에 추가되고, 변경된 수식이 화면에 보여줘야한다.")
    internal fun addOperator(operator: Operator) {
        // given
        presenter.addOperand(1)

        // when
        presenter.addOperator(operator)

        // then
        verify(exactly = 2) { view.showCurrentExpression(any()) }
    }

    @Test //
    @DisplayName("숫자 뒤에 숫자가 추가 된다면 수식에 이어 붙여 화면에 보여준다")
    internal fun addOperandAddOperand() {
        // given
        presenter.addOperand(1)

        // when
        presenter.addOperand(2)
        presenter.addOperand(3)

        // then
        verify(exactly = 3) { view.showCurrentExpression(any()) }
    }

    @Test
    @DisplayName("= 버튼을 누를 시, 수식이 계산이 된다.")
    internal fun calculate() {
        //given
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Multiply, 23)))

        //when
        presenter.calculate()

        //then
        verify(exactly = 1) { view.showCalculateValue(any()) }
    }

    @Test
    @DisplayName("계산기록이 안 보여지고 있을 떄, buttonMemory 버튼을 클릭 시 계산 기록 보여주기 기능 테스트")
    internal fun showHistory() {
        // given
        expression = Expression(listOf(1, Operator.Multiply, 23))
        presenter = MainPresenter(view, expression)
        presenter.calculate()

        // when
        presenter.toggleHistory()

        // then
        verify(exactly = 1) { view.showHistory(any()) }
    }

    @Test
    @DisplayName("계산기록이 보여지고 있는 상태에서 buttonMemory 버튼을 클릭 시 계산 기록 숨기기 기능 테스트")
    internal fun hideHistory() {
        // given
        expression = Expression(listOf(1, Operator.Multiply, 23))
        presenter = MainPresenter(view, expression)
        presenter.calculate()
        presenter.toggleHistory()

        // when
        presenter.toggleHistory()

        // then
        verify(exactly = 1) { view.hideHistory() }
    }

}