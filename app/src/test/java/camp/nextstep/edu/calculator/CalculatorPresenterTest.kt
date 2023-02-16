package camp.nextstep.edu.calculator

import com.nextstep.edu.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CalculatorPresenterTest {
    private lateinit var presenter: CalculatorPresenter
    private lateinit var view: CalculatorContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = CalculatorPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showCalculationExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendExpression(1)

        // then
        val actual = expressionSlot.captured
        assertEquals("1", actual)
        verify { view.showCalculationExpression(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showCalculationExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendExpression(1)
        presenter.appendExpression(Operator.ADDITION)

        // then
        val actual = expressionSlot.captured
        assertEquals("1 +", actual)
        verify { view.showCalculationExpression(actual) }
    }

    @Test
    fun `완전한 수식을 입력하고 계산을 실행하면 결과를 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showCalculationExpression(capture(expressionSlot)) } answers { nothing }

        presenter.appendExpression(10)
        presenter.appendExpression(Operator.ADDITION)
        presenter.appendExpression(45)

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertEquals("55", actual)
        verify { view.showCalculationExpression(actual) }
    }

    @Test
    fun `지우기를 실행 시 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showCalculationExpression(capture(expressionSlot)) } answers { nothing }

        presenter.appendExpression(10)
        presenter.appendExpression(Operator.ADDITION)
        presenter.appendExpression(15)

        // when
        presenter.removeExpression()

        // then
        val actual = expressionSlot.captured
        assertEquals("10 + 1", actual)
        verify { view.showCalculationExpression(actual) }
    }
}