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
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `사용자가 연산자 버튼을 누르면 해당 기호가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)
        presenter.removeLast()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `등호 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("3")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 등호 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        every { view.showIncompleteExpressionToast() } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 +")
        verify { view.showIncompleteExpressionToast() }
    }
}