package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var view: MainContract.View
    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        view = mockk<MainActivity>()
        presenter = MainPresenter(view)
    }

    @Test
    fun `피연산자가_입력되면_수식에_추가되고_화면에_보여야_한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        val expected = "23"
        presenter.onOperandClicked("2")
        presenter.onOperandClicked("3")

        // then
        val actual = expressionSlot.captured
        assertEquals(expected, actual)
        verify { view.showExpression(actual) }
    }

    @Test
    fun `연산자가_입력되면_수식에_추가되고_화면에_보여야_한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot))} answers { nothing }

        // when
        val expected = "1 + "
        presenter.onOperandClicked("1")
        presenter.onOperatorClicked(ArithmeticOperator.PLUS)

        // then
        val actual = expressionSlot.captured
        assertEquals(expected, actual)
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기가_입력되면_수식_마지막항을_지우고_화면에_보여야_한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot))} answers { nothing }

        // when
        val expected = "1 + 2 - "
        presenter.onOperandClicked("1")
        presenter.onOperatorClicked(ArithmeticOperator.PLUS)
        presenter.onOperandClicked("2")
        presenter.onOperatorClicked(ArithmeticOperator.MINUS)
        presenter.onOperandClicked("3")
        presenter.onDeleteClicked()

        // then
        val actual = expressionSlot.captured
        assertEquals(expected, actual)
        verify { view.showExpression(actual) }
    }

    @Test
    fun `대입연산자가_입력되면_수식을_계산하고_화면에_보여야_한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot))} answers { nothing }

        // when
        val expected = "18"
        presenter.onOperandClicked("3")
        presenter.onOperatorClicked(ArithmeticOperator.MULTIPLY)
        presenter.onOperandClicked("5")
        presenter.onOperatorClicked(ArithmeticOperator.PLUS)
        presenter.onOperandClicked("3")
        presenter.onEqualsClicked()

        // then
        val actual = expressionSlot.captured
        assertEquals(expected, actual)
        verify { view.showExpression(actual) }
    }
}
