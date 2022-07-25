package edu.nextstep.camp.calculator.main

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainPresenterTest {
    lateinit var view: MainContract.View
    lateinit var presenter: MainPresenter

    @BeforeEach
    fun setUp() {
        view = mockk()
    }

    @Test
    fun 피연산자를_입력하면_수식에_추가되고_수식이_보여야한다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs
        presenter = MainPresenter(view)

        //when
        presenter.addOperand(1)

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 피연산자가_입력되어_있는_상태에서_다시_피연산자가_입력되면_피연산자가_변경되고_수식이_보여아한다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter = MainPresenter(view, expression = Expression(listOf(1)))

        //when
        presenter.addOperand(2)

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("12")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 피연산자가_입력되어있고_연산자가_입력되면_연산자가_추가되고_수식이_보여야한다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter = MainPresenter(view, expression = Expression(listOf(1)))

        //when
        presenter.addOperator(Operator.Plus)

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 수식이_비어있을_때_연산자를_입력하면_작동하지_않는다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter = MainPresenter(view)

        //when
        presenter.addOperator(Operator.Minus)

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 마지막에_연산자가_입력되어_있으면_연산자를_교체한다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter = MainPresenter(view, expression = Expression(listOf(9, Operator.Plus)))

        //when
        presenter.addOperator(Operator.Minus)

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("9 -")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 수식이_입력되어_있을_때_마지막_문자를_지울수_있어야_한다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter = MainPresenter(view, expression = Expression(listOf(10, Operator.Multiply, 3)))

        //when
        presenter.removeLast()
        presenter.removeLast()

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("10")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 완성되지_않은_수식을_계산하면_에러문구를_보여준다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        every { view.showIncompleteExpression() } answers { nothing }
        presenter = MainPresenter(view, expression = Expression(listOf(10, Operator.Multiply)))

        //when
        presenter.expressionCalculate()

        //then
        verify { view.showIncompleteExpression() }
    }

    @Test
    fun 올바른_수식일_때_계산한_결과값을_보여준다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        every { view.showResult(20) } answers { nothing }
        presenter = MainPresenter(view, expression = Expression(listOf(10, Operator.Multiply, 2)))

        //when
        presenter.expressionCalculate()

        //then
        verify { view.showResult(20) }
    }
}