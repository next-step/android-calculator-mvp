package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.HistoryData
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
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 화면에 보여준다`() {
        // When
        presenter.appendOperand(3)

        // Then
        verify { view.showExpression("3") }

    }

    @Test
    fun `연산자가 입력되면 화면에 보여준다`() {
        // When
        presenter.appendOperand(3)
        presenter.appendOperator(Operator.Plus)

        // Then
        verify { view.showExpression("3 +") }
    }

    @Test
    fun `삭제를 하면 마지막 문자를 지우고 화면에 보여준다`() {
        // When
        presenter.appendOperand(3)
        presenter.appendOperator(Operator.Plus)
        presenter.deleteLast()

        // Then
        verify { view.showExpression("3") }
    }

    @Test
    fun `계산을 하면 연산된 결과를 화면에 보여준다`() {
        // Given
        presenter = MainPresenter(view, expression = Expression(listOf(3, Operator.Plus, 3)))

        // When
        presenter.calculate()

        // Then
        verify { view.showExpression("6") }
    }

    @Test
    fun `완성되지 않은 표현식으로 계산을 실행하면 에러 토스트를 보여준다`() {
        // When
        presenter.appendOperand(3)
        presenter.appendOperator(Operator.Plus)
        presenter.calculate()

        // Then
        verify { view.showErrorToast() }
    }

    @Test
    fun `히스토리 보기 토글을 실행하면 누적된 히스토리 내역을 보여준다`() {

        // When
        presenter.appendOperand(3)
        presenter.appendOperator(Operator.Plus)
        presenter.appendOperand(3)
        presenter.calculate()

        presenter.appendOperator(Operator.Plus)
        presenter.appendOperand(6)
        presenter.calculate()

        presenter.toggleHistoryViewMode()
        presenter.toggleHistoryViewMode()

        // Then
        verify { view.showHistoryView(listOf(
            HistoryData(Expression(listOf(3, Operator.Plus, 3)), 6),
            HistoryData(Expression(listOf(6, Operator.Plus, 6)), 12))) }
        verify { view.hideHistoryView() }
    }
}