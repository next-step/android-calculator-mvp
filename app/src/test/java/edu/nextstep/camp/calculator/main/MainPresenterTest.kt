package edu.nextstep.camp.calculator.main

import com.google.common.truth.Truth.assertThat
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
        presenter = MainPresenter(view)
    }

    @Test
    fun 피연산자를_입력하면_수식에_추가되고_수식이_보여야한다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

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
        presenter.addOperand(1)

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
        presenter.addOperand(1)

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
        presenter.addOperand(9)
        presenter.addOperator(Operator.Plus)

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
        presenter.addOperand(10)
        presenter.addOperator(Operator.Multiply)
        presenter.addOperand(3)

        //when
        presenter.removeLast()
        presenter.removeLast()

        //then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("10")
        verify { view.showExpression(actual) }
    }

    @Test
    fun 잘못된_수식을_계산하려고_했을_때_계산이_수행되지_않는다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        every { view.showResult(null) } answers { nothing }

        presenter.addOperand(10)
        presenter.addOperator(Operator.Multiply)

        //when
        presenter.expressionCalculate()

        //then
        verify { view.showResult(null) }
    }

    @Test
    fun 올바른_수식일_때_계산한_결과값을_보여준다() {
        //given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        every { view.showResult(20) } answers { nothing }

        presenter.addOperand(10)
        presenter.addOperator(Operator.Multiply)
        presenter.addOperand(2)

        //when
        presenter.expressionCalculate()

        //then
        verify { view.showResult(20) }
    }
}