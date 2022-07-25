package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    @DisplayName("피연산자가 입력이 된다면, 해당 값을 수식에 추가하고, 화면에 그 수식을 보여줘야한다.")
    internal fun addOperand(operand: Int) {
        // given
        val expression = slot<Expression>()
        every { view.showCurrentExpression(capture(expression)) } answers { nothing }

        // when
        presenter.addOperand(operand)

        // then
        val actual = expression.captured
        assertThat(actual.toString()).isEqualTo(operand.toString())
        verify { view.showCurrentExpression(actual) }
    }

    @ParameterizedTest
    @EnumSource(value = Operator::class)
    @DisplayName("숫자 뒤 연산자를 입력하면 연산자가 수식에 추가되고, 변경된 수식이 화면에 보여줘야한다.")
    internal fun addOperator(operator: Operator) {
        // given
        val expression = slot<Expression>()
        every { view.showCurrentExpression(capture(expression)) } answers { nothing }
        presenter.addOperand(1)

        // when
        presenter.addOperator(operator)

        // then
        val actual = expression.captured
        assertThat(actual.toString()).isEqualTo("1 ${operator.sign}")
        verify { view.showCurrentExpression(actual) }
    }

    @Test
    @DisplayName("숫자 뒤에 숫자가 추가 된다면 수식에 이어 붙여 화면에 보여준다")
    internal fun addOperandAddOperand() {
        // given
        val expression = slot<Expression>()
        every { view.showCurrentExpression(capture(expression)) } answers { nothing }
        presenter.addOperand(1)

        // when
        presenter.addOperand(2)
        presenter.addOperand(3)

        // then
        val actual = expression.captured
        assertThat(actual.toString()).isEqualTo("123")
        verify { view.showCurrentExpression(actual) }
    }

    @Test
    @DisplayName("= 버튼을 누를 시, 수식이 계산이 된다.")
    internal fun calculate() {
        //given
        val expressionSlot = slot<Expression>()
        every { view.showCalculateValue(capture(expressionSlot)) } answers { nothing }
        presenter.addOperand(1)
        presenter.addOperator(Operator.Multiply)
        presenter.addOperand(2)
        presenter.addOperand(3)

        //when
        presenter.calculate()

        //then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("23")
        verify { view.showCalculateValue(actual) }
    }

}