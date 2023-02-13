package camp.nextstep.edu.calculator

import io.mockk.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorPresenterTest {
    private lateinit var presenter: CalculatorPresenter
    private lateinit var view: CalculatorContract.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = CalculatorPresenter(view)
    }

    @After
    fun tear() {
        clearAllMocks()
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expected = "1"

        // when
        presenter.updateExpressions(expected)

        // then
        verify { view.showExpression(expected) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.updateExpressions("1 +")

        // then
        val actual = expressionSlot.captured
        assertEquals("1 +", actual)
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.updateExpressions("1")
        presenter.deleteLast()

        // then
        val actual = expressionSlot.captured
        assertEquals("", actual)
        verify { view.showExpression(actual) }
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        presenter.updateExpressions("1 + 2")

        // when
        presenter.calculateAndReset()

        // then
        verify {
            view.showExpression("1 + 2")
            view.showExpression("3")
        }
    }
}