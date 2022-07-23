package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MainPresenterOperatorTest(
    private val operator: Operator,
    private val sign: String,
) {

    @MockK private lateinit var view: MainContract.View
    @MockK private lateinit var calculator: Calculator
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `숫자가 입력되어 있을 때 기호를 입력하면 화면에 표시된다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY + 1)

        // when
        presenter.enterOperator(operator)

        // then
        verify { view.showExpression("1 $sign") }
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getTestParameters(): List<Array<Any>> = listOf(
            arrayOf(Operator.Plus, "+"),
            arrayOf(Operator.Minus, "-"),
            arrayOf(Operator.Multiply, "*"),
            arrayOf(Operator.Divide, "/"),
        )
    }
}
