package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringExpressionState
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @AfterEach
    fun clearUp() {
        confirmVerified(view)
    }

    @ParameterizedTest(name = "숫자 {1}가 입력되면 수식 {0}에 추가되고 변경된 수식 {2}을 보여줘야 한다")
    @CsvSource(
        "1 +, 2, 1 + 2",
        "1 - 2, 3, 1 - 23",
        "1 / 2 + 3, 45, 1 / 2 + 345",
    )
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`(given: String, operandNumber: Int, expected: String) {
        // given
        val operand = Operand(operandNumber)
        val stateSlot = slot<StringExpressionState>()
        every { view.setExpression(capture(stateSlot)) } answers { nothing }

        // when
        presenter.addElement(given, operand)

        // then
        val actual = stateSlot.captured
        assertThat(actual.toString()).isEqualTo(expected)
        verify(exactly = 1) { view.setExpression(actual) }
    }

    @ParameterizedTest(name = "연산자 {1}가 입력되면 수식 {0}에 추가되고 변경된 수식 {2}을 보여줘야 한다")
    @CsvSource(
        "1, /, 1 /",
        "1 / 21 + 3, *, 1 / 21 + 3 *",
        "'', +, ''",
    )
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`(
        given: String,
        operatorSymbol: String,
        expected: String
    ) {
        // given
        val operator = Operator.of(operatorSymbol)
        val stateSlot = slot<StringExpressionState>()
        every { view.setExpression(capture(stateSlot)) } answers { nothing }

        // when
        presenter.addElement(given, operator)

        // then
        val actual = stateSlot.captured
        assertThat(actual.toString()).isEqualTo(expected)
        verify(exactly = 1) { view.setExpression(actual) }
    }

    @ParameterizedTest(name = "{0} 수식의 마지막 요소 제거가 요청되면 제거한 후 변경된 수식 {1}을 보여줘야 한다")
    @CsvSource(
        "9, ''",
        "123 -, 123",
        "'', ''",
    )
    fun `수식의 마지막 요소 제거가 요청되면 제거한 후 변경된 수식을 보여줘야 한다`(given: String, expected: String) {
        // given
        val stateSlot = slot<StringExpressionState>()
        every { view.setExpression(capture(stateSlot)) } answers { nothing }

        // when
        presenter.removeElement(given)

        // then
        val actual = stateSlot.captured
        assertThat(actual.toString()).isEqualTo(expected)
        verify(exactly = 1) { view.setExpression(actual) }
    }

    @ParameterizedTest(name = "적절한 형식의 {0} 수식의 계산 결과가 요청되면 계산된 수식 {1}을 보여줘야 한다")
    @CsvSource(
        "1 + 2 + 3 + 4 + 5, 15",
        "1004 - 4 / 2, 500",
        "10 * 5 / 10, 5",
    )
    fun `적절한 형식의 수식 계산 결과가 요청되면 계산된 수식을 보여줘야 한다`(given: String, expected: String) {
        // when
        presenter.calculate(given)

        // then
        verify(exactly = 1) { view.setCalculationResult(Operand.of(expected)) }
    }

}
