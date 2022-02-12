package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    internal fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면, 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `입력된 피연산자가 있을 때, 연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식의 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(32)
        presenter.addToExpression(Operator.Plus)

        // when
        presenter.removeLastInExpression()

        // then
        var actual = expressionSlot.captured
        assertThat(actual).isEqualTo("32")
        verify { view.showExpression(actual) }

        // when
        presenter.removeLastInExpression()

        // then
        actual = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify { view.showExpression(actual) }

        // when
        presenter.removeLastInExpression()

        // then
        actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `입력된 수식이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)

        // when
        presenter.evaluateExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("5")
        verify { view.showExpression(actual) }
    }
}