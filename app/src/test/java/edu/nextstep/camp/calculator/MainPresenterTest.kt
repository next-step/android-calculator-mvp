package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    @MockK private lateinit var view: MainContract.View
    @MockK private lateinit var calculator: Calculator
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `숫자가 입력되어 있을 때 숫자를 입력하면 화면에 표시된다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY + 1)

        // when
        presenter.enterNumber(2)

        // then
        verify { view.showExpression("12") }
    }

    @Test
    fun `아무것도 입력하지 않았을 때 기호를 누르면 화면에 빈 값이 표시된다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY)

        // when
        presenter.enterOperator(Operator.Plus)

        // then
        verify { view.showExpression("") }
    }

    @Test
    fun `숫자가 입력되어 있을 때 지우기를 하면 한 자리가 지워진다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY + 12)

        // when
        presenter.removeLast()

        // then
        verify { view.showExpression("1") }
    }

    @Test
    fun `아무 입력이 없을 때 지우기를 누르면 빈 값이 표시된다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY)

        // when
        presenter.removeLast()

        // then
        verify { view.showExpression("") }
    }

    @Test
    fun `1 더하기 2 나누기 3 곱하기 4가 입력되어 있을 때 계산을 하면 4가 표시된다`() {
        // given
        presenter = MainPresenter(
            view = view,
            calculator = calculator,
            expression = Expression.EMPTY + 1 + Operator.Plus + 2 + Operator.Divide + 3 + Operator.Multiply + 4)

        // when
        presenter.calculate()

        // then
        verify { view.showExpression("4") }
    }

    @Test
    fun `완성되지 않은 수식을 계산하면 오류 메세지를 표시한다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY + 1 + Operator.Plus)

        // when
        presenter.calculate()

        // then
        verify { view.showIncomplete() }
    }
}
