package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
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
        view = mockk()
        presenter = MainPresenter(view)
    }

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    internal fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`(value: Int) {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing}

        //when
        presenter.addToExpression(value)

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo(value.toString())
        verify { view.showExpression(actual) }
    }

    @ParameterizedTest
    @EnumSource(value = Operator::class)
    internal fun `숫자 뒤에 연산자가 입력되면 연산자가 추가되고 변경된 수식을 보여줘야 한다`(value: Operator) {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing}
        presenter.addToExpression(1)

        //when
        presenter.addToExpression(value)

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 $value")
        verify { view.showExpression(actual) }
    }

    @Test
    internal fun `숫자 뒤에 숫자가 입력되면 변경된 숫자를 보여줘야 한다`() {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing}
        presenter.addToExpression(1)

        //when
        presenter.addToExpression(2)
        presenter.addToExpression(3)
        presenter.addToExpression(9)

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1239")
        verify { view.showExpression(actual) }
    }


    @Test
    internal fun `연산자가 입력된 상태에서 연산자를 추가 입력하면 마지막으로 입력된 연산자로 변경된다`() {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        //when
        presenter.addToExpression(Operator.Minus)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(Operator.Divide)
        presenter.addToExpression(Operator.Multiply)

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 *")
        verify { view.showExpression(actual) }
    }

    @Test
    internal fun `수식이 완성되지 않은 상태에서 계산을 하면 변화가 없다`() {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showToast(R.string.incomplete_expression) } answers { nothing }
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Multiply)
        presenter.addToExpression(9)
        presenter.addToExpression(0)
        presenter.addToExpression(Operator.Minus)

        //when
        presenter.calculate()

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("3 * 90 -")
        verify { view.showToast(R.string.incomplete_expression) }
    }

    @Test
    internal fun `수식이 완성된 상태에서 계산을 하면 수식의 결과를 보여줘야 한다`() {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Multiply)
        presenter.addToExpression(9)
        presenter.addToExpression(0)

        //when
        presenter.calculate()

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("270")
        verify { view.showExpression(actual) }
    }

    @Test
    internal fun `수식이 입력된 상태에서 삭제 버튼을 누르면 마지막 입력된 숫자 또는 연산자가 삭제된다`() {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        //when
        presenter.removeLast()

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1")
        verify { view.showExpression(actual) }
    }
}