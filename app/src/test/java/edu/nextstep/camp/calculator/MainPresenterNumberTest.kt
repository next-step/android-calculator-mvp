package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MainPresenterNumberTest(private val number: Int) {

    @MockK private lateinit var view: MainContract.View
    @MockK private lateinit var calculator: Calculator
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `0부터 9까지 각 버튼을 누르면 화면에 해당 숫자가 표시된다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY)

        // when
        presenter.enterNumber(number)

        // then
        verify { view.showExpression("$number") }
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getTestParameters(): List<Array<Any>> = listOf(
            arrayOf(0),
            arrayOf(1),
            arrayOf(2),
            arrayOf(3),
            arrayOf(4),
            arrayOf(5),
            arrayOf(6),
            arrayOf(7),
            arrayOf(8),
            arrayOf(9),
        )
    }
}
