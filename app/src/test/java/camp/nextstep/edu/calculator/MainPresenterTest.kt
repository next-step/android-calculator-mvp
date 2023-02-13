package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.calculator.Num
import camp.nextstep.edu.domain.calculator.Operators
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
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
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        every { view.showExpression("1") } answers { nothing }

        // when
        presenter.appendExpression(Num(1))

        // then
        verify { view.showExpression("1") }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendExpression(Num(1))
        presenter.appendExpression(Operators.Plus)

        // then
        val actual = expressionSlot.captured
        assertEquals(actual, "1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendExpression(Num(1))
        presenter.removeLastExpression()

        // then
        val actual = expressionSlot.captured
        assertEquals(actual, "")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.appendExpression(Num(1))
        presenter.appendExpression(Operators.Plus)
        presenter.appendExpression(Num(2))

        // when
        presenter.calculate(onError = mockk())

        // then
        val actual = expressionSlot.captured
        assertEquals(actual, "3")
        verify { view.showExpression("3") }
    }
}
