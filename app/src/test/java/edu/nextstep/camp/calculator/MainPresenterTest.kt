package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addOperand(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `마지막 수식이 연산자일때, 연산자를 입력하면, 마지막 연산자만 수식에 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view = view, expression = Expression(listOf(1, Operator.Plus)))
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addOperator(Operator.Minus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 -")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `수식이 비어있을때, 연산자를 입력하면, 빈 값을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view = view, expression = Expression.EMPTY)
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addOperator(Operator.Minus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `수식을 삭제하면, 마지막 글자가 지워져야 한다`() {
        // given
        presenter =
            MainPresenter(view = view, expression = Expression(listOf(3, Operator.Plus, 44)))
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.removeLastExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("3 + 4")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `완성된 표현식일 경우, 결과버튼을 누르면, 연산 결과가 표시되야한다`() {
        // given
        presenter =
            MainPresenter(view = view, expression = Expression(listOf(3, Operator.Multiply, 4)))
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("12")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `완성되지 않은 표현식일 경우, 결과버튼을 누르면, 에러메세지가 표시되야한다`() {
        // given
        presenter =
            MainPresenter(view = view, expression = Expression(listOf(3, Operator.Multiply)))
        every { view.showErrorMsg() } answers { nothing }

        // when
        presenter.calculate()

        // then
        verify { view.showErrorMsg() }
    }

    @Test
    fun `계산 기록 버튼을 누르면, 계산기록이 표시되어야한다`() {
        // given
        val expression = listOf(3, Operator.Multiply, 4)
        val histories = listOf(CalculationResult(expression.joinToString(" "), 12))
        val calculator = Calculator(CalculationHistories(histories))
        presenter = MainPresenter(view = view, calculator = calculator)
        val expressionSlot = slot<List<CalculationResult>>()
        every { view.showHistory(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.loadHistory()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo(histories)
        verify { view.showHistory(histories) }
    }
}