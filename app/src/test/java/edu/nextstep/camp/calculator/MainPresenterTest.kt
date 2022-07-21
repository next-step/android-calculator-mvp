package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    @MockK private lateinit var view: MainContract.View
    @InjectMockKs private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `0부터 9까지 각 버튼을 누르면 화면에 해당 숫자가 표시된다`() {
        (0..9).forEach { number ->
            // given
            presenter = MainPresenter(view)

            // when
            presenter.enterNumber(number)

            // then
            verify { view.showExpression("$number") }
        }
    }

    @Test
    fun `숫자가 입력되어 있을 때 기호를 입력하면 화면에 표시된다`() {
        listOf(
            Operator.Plus to "+",
            Operator.Minus to "-",
            Operator.Multiply to "*",
            Operator.Divide to "/",
        ).forEach { (operator, sign) ->
            // given
            presenter = MainPresenter(view)
            presenter.enterNumber(1)

            // when
            presenter.enterOperator(operator)

            // then
            verify { view.showExpression("1 $sign") }
        }
    }

    @Test
    fun `숫자가 입력되어 있을 때 숫자를 입력하면 화면에 표시된다`() {
        // given
        presenter.enterNumber(1)

        // when
        presenter.enterNumber(2)

        // then
        verify { view.showExpression("12") }
    }

    @Test
    fun `아무것도 입력하지 않았을 때 기호를 누르면 화면에 빈 값이 표시된다`() {
        // when
        presenter.enterOperator(Operator.Plus)

        // then
        verify { view.showExpression("") }
    }
}
