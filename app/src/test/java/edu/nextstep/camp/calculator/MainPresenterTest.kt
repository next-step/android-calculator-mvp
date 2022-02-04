package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `빈 화면에서 5를 클릭하면 5가 보여야 한다`() {
        // given
        val expression = Expression.EMPTY

        // when
        presenter.addNumber(expression, "5")

        // then
        val expected = Expression(listOf(5))
        verify { view.onViewUpdated(expected) }
    }

    @Test
    fun `5 더하기 1이 보이는 화면에서 빼기를 클릭하면 5 더하기 1 빼기가 보여야 한다`() {
        // given
        val expression = Expression(listOf(5, Operator.Plus, 1))

        // when
        presenter.addOperator(expression, Operator.Minus)

        // then
        val expected = Expression(listOf(5, Operator.Plus, 1, Operator.Minus))
        verify { view.onViewUpdated(expected) }
    }

    @Test
    fun `6 곱하기가 보이는 화면에서 지우기 버튼을 누르면 6이 보여야 한다`() {
        // given
        val expression = Expression(listOf(6, Operator.Multiply))

        // when
        presenter.delete(expression)

        // then
        val expected = Expression(listOf(6))
        verify { view.onViewUpdated(expected) }
    }

    @Test
    fun `2 더하기 10을 계산하면 12가 보여야 한다`() {
        // given
        val expression = Expression(listOf(2, Operator.Plus, 10))

        // when
        presenter.calculate(expression)

        // then
        val expected = Expression(listOf(12))
        verify { view.onViewUpdated(expected) }
    }

    @Test
    fun `viewType을 toggle하면 viewType이 바뀌고 onViewTypeChanged() 콜백이 호출된다`() {
        // when
        presenter.toggleViewType()

        // then
        val expectedViewType = MemoryView
        val memory = Memory()
        verify { view.onViewTypeChanged(expectedViewType, memory) }
        //Verification failed: call 1 of 1: View(#1).onViewTypeChanged(eq(edu.nextstep.camp.calculator.MemoryView@791cd2fb), eq())). Only one matching call to View(#1)/onViewTypeChanged(CalculatorViewType, Memory) happened, but arguments are not matching:
        //[0]: argument: edu.nextstep.camp.calculator.MemoryView@791cd2fb, matcher: eq(edu.nextstep.camp.calculator.MemoryView@791cd2fb), result: +
        //[1]: argument: , matcher: eq(), result: -
        // 콜백으로 받는 memory가 일치하지 않아서 실패하는 듯 함
    }
}