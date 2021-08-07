package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private val view: MainContract.View = mockk(relaxed = true)

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        val presenter = MainPresenter(view = view) as MainContract.Presenter

        presenter.formatExpression(number = 1)

        assertThat(presenter.expression.toString()).isEqualTo("1")
        verify {
            view.showExpression(presenter.expression)
        }
    }

    @Test
    fun `빈 수식에 연산자가 입력되면 수식에 추가되지 않습니다`() {
        val presenter = MainPresenter(view = view) as MainContract.Presenter

        presenter.formatExpression(operator = Operator.Plus)

        assertThat(presenter.expression).isEqualTo(Expression.EMPTY)
        verify {
            view.showExpression(presenter.expression)
        }
    }
}
