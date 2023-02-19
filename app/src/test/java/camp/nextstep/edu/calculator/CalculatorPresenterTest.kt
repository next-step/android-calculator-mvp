package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Test

class CalculatorPresenterTest {
    private lateinit var presenter: CalculatorPresenter
    private lateinit var view: CalculatorContract.View

    @Before
    fun `presenter 생성`() {
        view = mockk()
        presenter = CalculatorPresenter(view)
    }

    @Test
    fun `피연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpressions(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.appendExpression("1")

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpressions(actual) }
    }

    @Test
    fun `피연산자가 입력된 상태에서 연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpressions(capture(expressionSlot)) } answers { nothing }

        presenter.appendExpression("1")

        // when
        presenter.appendExpression(plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 + ")
        verify { view.showExpressions(actual) }
    }

    @Test
    fun `수식을 입력 후 계산 실행 시 결과를 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        val resultSlot = slot<Double>()

        every { view.showExpressions(capture(expressionSlot)) } answers { nothing }
        every {
            view.showCalculationResult(
                capture(expressionSlot),
                capture(resultSlot)
            )
        } answers { "${expressionSlot.captured} = ${resultSlot.captured}" }

        presenter.appendExpression("1")
        presenter.appendExpression(plus)
        presenter.appendExpression("2")

        // when
        presenter.calculate()

        // then
        val expression = expressionSlot.captured
        val result = resultSlot.captured
        assertThat(view.showCalculationResult(expression, result)).isEqualTo("1 + 2 = 3.0")
        verify { view.showCalculationResult(expression, result) }
    }

    @Test
    fun `지우기 버튼을 누르면 마지막 피연산자가 지워져야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpressions(capture(expressionSlot)) } answers { nothing }

        presenter.appendExpression("1")
        presenter.appendExpression(plus)
        presenter.appendExpression("2")

        // when
        presenter.removeExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 + ")
        verify { view.showExpressions(actual) }
    }

    @Test
    fun `지우기 버튼을 누르면 마지막 연산자가 지워져야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpressions(capture(expressionSlot)) } answers { nothing }

        presenter.appendExpression("1")
        presenter.appendExpression(plus)
        presenter.appendExpression("2")
        presenter.appendExpression(minus)

        // when
        presenter.removeExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 + 2")
        verify { view.showExpressions(actual) }
    }
}
