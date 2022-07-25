package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
    }

    @Test
    fun `입력된 피연산자가 없을 때 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression.EMPTY)
        val expression = Expression(listOf(1))

        // when
        presenter.addToExpression(1)

        // then
        verify { view.showExpression(expression) }
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 버튼을 누르면 해당 기호가 화면에 보여야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1)))
        val expression = Expression(listOf(1, Operator.Plus))

        // when
        presenter.addToExpression(Operator.Plus)

        // then
        verify { view.showExpression(expression) }
    }

    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1, Operator.Plus, 2)))
        val expression = Expression(listOf(1, Operator.Plus))

        // when
        presenter.removeLast()

        // then
        verify { view.showExpression(expression) }
    }

    @Test
    fun `입력된 수신이 완전할 때, 등호 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1, Operator.Plus, 2)))
        val expression = Expression(listOf(3))

        // when
        presenter.calculate()

        // then
        verify { view.showExpression(expression) }
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 등호 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1, Operator.Plus)))
        val expression = Expression(listOf(1, Operator.Plus))

        // when
        presenter.calculate()

        // then
        verify { view.showIncompleteExpression() }
    }
}