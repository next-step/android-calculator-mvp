package camp.nextstep.edu.calculator

import com.example.domain.Expression
import com.example.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
        presenter.clearCurrentOperandList()
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.clickButton("1")

        // then
        val actual = expressionSlot.captured
        assertEquals("1", actual)
        verify { view.showResult(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.clickButton("1")
        presenter.clickButton(Operator.PLUS.operator)

        // then
        val actual = expressionSlot.captured
        assertEquals("1 +", actual)
        verify { view.showResult(actual) }
    }

    @Test
    fun `지우기가 실행되면 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.clickButton("1")
        presenter.removeLast()

        // then
        val actual = expressionSlot.captured
        assertEquals("", actual)
        verify { view.showResult(actual) }
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }
        presenter.clickButton("3")
        presenter.clickButton(Operator.PLUS.operator)
        presenter.clickButton("5")

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertEquals("8", actual)
        verify { view.showResult(actual) }
    }
}
