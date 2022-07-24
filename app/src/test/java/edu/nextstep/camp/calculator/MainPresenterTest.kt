package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainConstract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
    }

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    internal fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`(value: Int) {
        //given
        presenter = MainPresenter(view)

        //when
        val expected = Expression(listOf(value))
        presenter.addToExpression(value)


        //then
        verify { view.succeedCalculate(expected) }
    }

    @ParameterizedTest
    @EnumSource(value = Operator::class)
    internal fun `숫자 뒤에 연산자가 입력되면 연산자가 추가되고 변경된 수식을 보여줘야 한다`(value: Operator) {
        //given
        presenter = MainPresenter(view, Expression(listOf(1)))
        val expected = Expression(listOf(1, value))

        //when
        presenter.addToExpression(value)

        //then
        verify { view.succeedCalculate(expected) }
    }

    @Test
    internal fun `숫자 뒤에 숫자가 입력되면 변경된 숫자를 보여줘야 한다`() {
        //given
        presenter = MainPresenter(view, Expression(listOf(123)))
        val expected = Expression(listOf(1239))

        //when
        presenter.addToExpression(9)

        //then
        verify { view.succeedCalculate(expected) }
    }


    @Test
    internal fun `연산자가 입력된 상태에서 연산자를 추가 입력하면 마지막으로 입력된 연산자로 변경된다`() {
        //given
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Plus)))
        val expected = Expression(listOf(1, Operator.Multiply))

        //when
        presenter.addToExpression(Operator.Minus)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(Operator.Divide)
        presenter.addToExpression(Operator.Multiply)

        //then
        verify { view.succeedCalculate(expected) }
    }

    @Test
    internal fun `수식이 완성되지 않은 상태에서 계산을 하면 변화가 없다`() {
        //given
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Multiply, 90, Operator.Minus)))

        //when
        presenter.calculate()

        //then
        verify { view.failedCalculate() }
    }

    @Test
    internal fun `수식이 완성된 상태에서 계산을 하면 수식의 결과를 보여줘야 한다`() {
        //given
        val expected = Expression(listOf(270))
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Multiply, 90)))

        //when
        presenter.calculate()

        //then
        verify { view.succeedCalculate(expected) }
    }

    @Test
    internal fun `수식이 입력된 상태에서 삭제 버튼을 누르면 마지막 입력된 숫자 또는 연산자가 삭제된다`() {
        //given
        val expected = Expression(listOf(1, Operator.Plus, 3))
        presenter = MainPresenter(view, Expression(listOf(1, Operator.Plus, 32)))

        //when
        presenter.removeLast()

        //then
        verify { view.succeedCalculate(expected) }
    }
}