package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
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
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given & when
        presenter.input(1)

        // then
        verify { view.showExpression("1") }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        presenter = MainPresenter(view, Expression(listOf(1)))
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.input(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기 액션이 입력되면 수식의 마지막을 제거한다`() {
        // given
        val expressionSlot = slot<String>()
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Plus)))
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.removeLast()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `식의 결과를 호출했을 때 수식의 결과값을 반환한다`() {
        // given
        val expressionSlot = slot<String>()
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Plus, 1)))
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `식의 결과를 호출했을 때 수식에는 최종 결과만 남는다`() {
        // given
        val expressionSlot = slot<String>()
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Plus, 1)))
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("2")
        verify { view.showExpression(actual) }
    }


    @Test
    fun `정상적인 수식일 때 수식과 계산 결과를 저장한다`() {
        // given & when
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Plus, 2)))
        presenter.calculate()
        presenter.input(2)
        presenter.input(Operator.Multiply)
        presenter.input(3)
        presenter.input(Operator.Plus)
        presenter.input(7)
        presenter.calculate()

        // then
        assertThat(presenter.calculateHistory.calculateHistories[0].expression.toString()).isEqualTo("1 + 2")
        assertThat(presenter.calculateHistory.calculateHistories[0].result).isEqualTo(3)
        assertThat(presenter.calculateHistory.calculateHistories[1].expression.toString()).isEqualTo("32 * 3 + 7")
        assertThat(presenter.calculateHistory.calculateHistories[1].result).isEqualTo(103)
    }
}
