package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
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
    fun 숫자가_입력되면_수식에_추가하고_변경된_수식을_보여줘야_한다() {
        //given
        val expressionSlot = slot<Expression>()
        //when
        presenter.addToExpression(1)
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEqualTo("1")
    }

    @Test
    fun 숫자가_입력되지_않은_상태에서_연산자가_입력되면_비어있는_수식을_보여줘야_한다() {
        //given
        val expressionSlot = slot<Expression>()
        //when
        presenter.addToExpression(Operator.Plus)
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEmpty()
    }

    @Test
    fun 숫자가_입력된_상태에서_연산자가_입력되면_수식에_추가하고_변경된_수식을_보여줘야_한다() {
        //given
        val expressionSlot = slot<Expression>()
        presenter = MainPresenter(view = view, expression = Expression(listOf(1)))
        //when
        presenter.addToExpression(Operator.Plus)
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEqualTo("1 +")
    }

    @Test
    fun `10_이상의_숫자에서_제거_버튼을_누르면_일의_자리_숫자만_제거하고_변경된_수식을_보여줘야_한다`() {
        //given
        val expressionSlot = slot<Expression>()
        presenter = MainPresenter(view = view, expression = Expression(listOf(10)))
        //when
        presenter.removeLastFromExpression()
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEqualTo("1")
    }

    @Test
    fun `수식이_비어있지_않은_상태에서_제거_버튼을_누르면_마지막_토큰을_지우고_변경된_수식을_보여줘야_한다`() {
        //given
        val expressionSlot = slot<Expression>()
        presenter = MainPresenter(view = view, expression = Expression(listOf(10, Operator.Plus)))
        //when
        presenter.removeLastFromExpression()
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEqualTo("10")
    }

    @Test
    fun `수식이_비어있는_상태에서_제거_버튼을_누르면_비어있는_수식을_보여줘야_한다`() {
        //given
        val expressionSlot = slot<Expression>()
        presenter = MainPresenter(view = view)
        //when
        presenter.removeLastFromExpression()
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEmpty()
    }

    @Test
    fun `완성된_수식이라면_수식을_계산하고_계산된_값을_보여줘야_한다`() {
        //given
        val expressionSlot = slot<Expression>()
        presenter = MainPresenter(view = view, expression = Expression(listOf(1, Operator.Plus, 2, Operator.Divide, 2)))
        //when
        presenter.calculateExpression()
        //then
        verify { view.showExpression(capture(expressionSlot)) }
        assertThat(expressionSlot.captured.toString()).isEqualTo("1")
    }

    @Test
    fun `완성되지_않은_수식이라면_토스트를_보여줘야_한다`() {
        //given
        presenter = MainPresenter(view = view, expression = Expression(listOf(1, Operator.Plus, 2, Operator.Divide)))
        //when
        presenter.calculateExpression()
        //then
        verify { view.showCalculateFailedToast() }
    }
}
