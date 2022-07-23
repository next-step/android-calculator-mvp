package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var view: MainContract.View
    private lateinit var presenter: MainPresenter

    @BeforeEach
    fun setup() {
        view = mockk(relaxUnitFun = true)
        presenter = MainPresenter(view)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    fun `display operand added expression when add operand on empty expression`(operand: Int) {
        // when
        presenter.addToExpression(operand)

        // then
        val expected = operand.toString()
        verify { view.showExpression(expected) }
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `display operator added expression when add operator on empty expression`(operator: Operator) {
        // when
        presenter.addToExpression(operator)

        // then
        val expected = ""
        verify { view.showExpression(expected) }
    }

    @Test
    fun `display last piece deleted expression when delete`() {
        // given "12 +"
        presenter.addToExpression(1)
        presenter.addToExpression(2)
        presenter.addToExpression(Operator.Plus)

        // when
        presenter.removeLast()

        // then
        val expected = "12"
        verify { view.showExpression(expected) }
    }

    @Test
    fun `display final answer when calculate`() {
        // given "1 + 3 * 6"
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Multiply)
        presenter.addToExpression(6)

        // when
        presenter.calculate()

        // then
        val expected = "24"
        verify { view.showExpression(expected) }
    }

    @Test
    fun `show fail message and do nothing with display when calculate on uncompleted expression`() {
        // given "1 *"
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Multiply)
        verify(exactly = 2) { view.showExpression(any()) }

        // when
        presenter.calculate()

        // then
        verify { view.showCalculationFailMessage() }
        verify(exactly = 2) { view.showExpression(any()) }
    }
}
