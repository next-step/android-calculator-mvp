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
        MockKAnnotations.init(this)
    }

    @Test
    fun `빈 수식에 숫자가 입력되면 수식에 추가되고 뷰에게 수식 갱신을 요청을 호출한다`() {
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
    fun `빈 수식에 연산자가 입력되면 수식 갱신 요청이 호출 되지 않는다`() {
        // given
        every { view.refreshExpression(any()) } answers { nothing }
        // when :
        presenter.addToExpressionElement(Operator.Plus)
        // then :
        verify(exactly = 0) { view.refreshExpression(any()) }
    }
}
