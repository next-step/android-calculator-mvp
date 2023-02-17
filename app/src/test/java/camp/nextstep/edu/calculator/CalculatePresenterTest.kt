package camp.nextstep.edu.calculator

import com.example.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatePresenterTest {
    private lateinit var presenter: CalculatePresenter
    private lateinit var view: CalculateContract.View

    @Before
    fun `Presenter 인스턴스 생성`() {
        view = mockk()
        presenter = CalculatePresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        every { view.showExpression("1") } answers { nothing }

        // when
        presenter.appendOperand(1)

        // then
        verify(exactly = 1) { view.showExpression("1") }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendOperand(1)
        presenter.appendOperator(Operator.PLUS)

        // then
        val actual = expressionSlot.captured
        assertEquals("1 +", actual)
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendOperand(12)
        presenter.removeLastValue()

        // then
        val actual = expressionSlot.captured
        assertEquals("1", actual)
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.appendOperand(1)
        presenter.appendOperator(Operator.PLUS)
        presenter.appendOperand(2)

        // when
        presenter.calculateExpression()

        // then
        val actual = expressionSlot.captured
        assertEquals("3", actual)
        verify(exactly = 1) { view.showExpression(actual) }
    }
}