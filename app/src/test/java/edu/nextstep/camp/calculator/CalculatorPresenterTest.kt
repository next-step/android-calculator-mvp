package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CalculatorPresenterTest {
    @MockK
    private lateinit var view: CalculatorContract.View

    @InjectMockKs
    private lateinit var presenter: CalculatorPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `빈 수식에 숫자가 입력되면 수식에 추가되고 뷰에게 수식 갱신하는 함수를 호출한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when
        presenter.addExpressionElement(1)
        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `빈 수식에 연산자가 입력되면 수식 갱신하는 함수를 호출하지 않는다`() {
        // when :
        presenter.addExpressionElement(Operator.Plus)
        // then :
        verify(exactly = 0) { view.refreshExpression(any()) }
    }

    @Test
    fun `1 이 입력된 수식에 2를 추가하면 뷰에 12 라는 수식으로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter.addExpressionElement(1)
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.addExpressionElement(2)
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("12")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `1 이 입력된 수식에 + 를 추가하면 뷰에 1 + 라는 수식으로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter.addExpressionElement(1)
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.addExpressionElement(Operator.Plus)
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1 +")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `1 + 가 입력된 수식에서 - 를 추가하면 뷰에 1 - 라고 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter.addExpressionElement(1)
        presenter.addExpressionElement(Operator.Plus)
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.addExpressionElement(Operator.Minus)
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1 -")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `12가 입력된 수식에서 마지막을 제거하면 뷰에 1 로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter.addExpressionElement(1)
        presenter.addExpressionElement(2)
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.removeLastExpressionElement()
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `1 +가 입력된 수식에서 마지막을 제거하면 뷰에 1 로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter.addExpressionElement(1)
        presenter.addExpressionElement(Operator.Plus)
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.removeLastExpressionElement()
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `빈 수식에서 마지막을 제거하면 뷰에 갱신 함수를 호출하지 않는다`() {
        // given :
        // when :
        presenter.removeLastExpressionElement()
        // then :
        verify(exactly = 0) { view.refreshExpression(any()) }
    }
}
