package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.StringExpressionState
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
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
    }
}
