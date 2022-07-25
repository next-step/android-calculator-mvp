package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

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
        presenter = MainPresenter(view, calculator, Expression(listOf(1)))

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
        presenter = MainPresenter(view, calculator, Expression(listOf(12)))

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
            view,
            calculator,
            Expression(listOf(1, Operator.Plus, 2, Operator.Divide, 3, Operator.Multiply, 4))
        )
        every { calculator.calculate(any()) } returns 4

        // when
        presenter.calculate()

        // then
        verify { view.showExpression("4") }
    }

    @Test
    fun `완성되지 않은 수식을 계산하면 오류 메세지를 표시한다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression(listOf(1, Operator.Plus)))
        every { calculator.calculate(any()) } returns null

        // when
        presenter.calculate()

        // then
        verify { view.showIncomplete() }
    }

    @Test
    fun `계산된 기록이 있을 때 버튼을 누르면 화면에 표시한다`() {
        // given
        presenter = MainPresenter(view, calculator, Expression.EMPTY)

        val historyList = listOf(History("3 + 5", 8), History("10 - 3", 7))
        every { calculator.historyList } returns historyList

        // when
        presenter.toggleHistory()

        // then
        verify { view.showHistory(historyList) }
    }
}

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
