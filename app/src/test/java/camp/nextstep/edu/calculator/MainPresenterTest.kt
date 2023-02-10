package camp.nextstep.edu.calculator

import com.example.domain.Calculator
import com.example.domain.Expression
import com.example.domain.Operator
import io.mockk.*
import org.junit.Assert.assertEquals
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
    fun `빈 수식에 숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot: CapturingSlot<Expression> = slot()

        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.appendOperandInExpression(1)

        // then
        val actual = expressionSlot.captured
        assertEquals("1", actual.value())
        verify { view.showExpression(actual) }
    }

    @Test
    fun `피연산자 뒤에 연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot: CapturingSlot<Expression> = slot()

        every { view.showExpression(capture(expressionSlot)) } just Runs
        presenter.appendOperandInExpression(3)

        // when
        presenter.appendOperatorInExpression(Operator.PLUS)

        // then
        val actual = expressionSlot.captured
        assertEquals("3 +", actual.value())
        verify { view.showExpression(actual) }
    }

    @Test
    fun `수식이 완료되지 않을 상태에서 이퀄사인 버튼을 누를 경우 토스트 메세지를 보여야 한다`() {
        // given
        val expressionSlot: CapturingSlot<Expression> = slot()

        every { view.showExpression(capture(expressionSlot)) } just Runs
        every { view.showToastMessage(capture(expressionSlot)) } just Runs
        presenter.appendOperandInExpression(3)
        presenter.appendOperatorInExpression(Operator.PLUS)

        // when
        presenter.returnResult()

        // then
        verify { view.showToastMessage(capture(expressionSlot)) }
    }

    @Test
    fun `옳바른 수식 상태에서 이퀄사인 버튼을 누를 경우 수식을 계산한 결과 값이 보여야 한다`() {
        // given
        val resultSlot = slot<String>()
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } just Runs
        every { view.showResult(capture(resultSlot)) } just Runs
        presenter.appendOperandInExpression(3)
        presenter.appendOperatorInExpression(Operator.PLUS)
        presenter.appendOperandInExpression(3)

        // when
        presenter.returnResult()

        // then
        val result = resultSlot.captured
        assertEquals("6", result)
        verify { view.showResult(result) }
    }

    @Test
    fun `수식 작성 중에 지우기 버튼을 누를 경우 마지막 값이 지워지고 남은 수식이 보여야 한다`() {
        // given
        val expressionSlot: CapturingSlot<Expression> = slot()

        every { view.showExpression(capture(expressionSlot)) } just Runs

        presenter.appendOperandInExpression(3)
        presenter.appendOperatorInExpression(Operator.PLUS)

        // when
        presenter.removeLastValueInExpression()

        // then
        val actual = expressionSlot.captured
        assertEquals("3", actual.value())
        verify{ view.showExpression(actual) }
    }
}