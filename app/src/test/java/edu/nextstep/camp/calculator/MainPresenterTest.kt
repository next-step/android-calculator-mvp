package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.*
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
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `수식이 주어졌을 때, 화살표 기호가 입력되면 수식의 마지막 원소를 지워야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(11)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(32)

        // when
        presenter.removeLast()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("11 + 3")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `수식이 비어있을 때, 화살표 기호가 입력되면 아무 변화가 없어야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.removeLast()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `유효한 수식이 주어졌을 때, 기호 = 가 입력되면 수식의 결과값을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(32)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(41)

        // when
        presenter.evaluate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("73")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `유효하지 않은 수식이 주어졌을 때, 기호 = 가 입력되면 토스트를 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(32)
        presenter.addToExpression(Operator.Plus)

        // when
        presenter.evaluate()

        // then
        verify(exactly = 1) { view.showToast() }
    }
}
