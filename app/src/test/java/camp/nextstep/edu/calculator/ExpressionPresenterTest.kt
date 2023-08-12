package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.ExpressionItem
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation
import com.google.common.truth.Truth.assertThat
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ExpressionPresenterTest {
    private lateinit var presenter: ExpressionPresenter
    private lateinit var view: Contract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = ExpressionPresenter(view)
    }

    @Test
    fun `1 입력하면 1이출력된다`() {
        every { view.display("1") } just Runs

        presenter.addOperandExpression(Operand(1))

        verify { view.display("1") }
    }

    @Test
    fun `5 + 1 수식을 입력하면 5 + 1 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(5), Operation.Addition, Operand(1)))

        `수식을 확인한다`("5 + 1", expressionSlot.captured)
    }

    @Test
    fun `89를 입력하면_89가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(8), Operand(9)))

        `수식을 확인한다`("89", expressionSlot.captured)
    }

    @Test
    fun `+ 입력하면 빈문자가 출력된다`() {
        every { view.display("") } just Runs

        `수식을 입력한다`(listOf(Operation.Addition))

        verify { view.display("") }
    }

    @Test
    fun `1 + 입력하면 1 + 가출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(1), Operation.Addition))

        `수식을 확인한다`("1 + ", expressionSlot.captured)
    }

    @Test
    fun `1 + 입력하고 - 를 입력하면 1 - 가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(1), Operation.Addition, Operation.Subtraction))

        `수식을 확인한다`("1 - ", expressionSlot.captured)
    }

    @Test
    fun `삭제를 호출하면 빈문자가 출력된다`() {
        every { view.display("") } just Runs

        presenter.removeExpressionItem()

        verify { view.display("") }
    }

    @Test
    fun `32 + 1 입력하고 삭제를 호출하면 32 + 가출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(3), Operand(2), Operation.Addition, Operand(1)))
        // 삭제한다
        presenter.removeExpressionItem()

        `수식을 확인한다`("32 + ", expressionSlot.captured)
    }

    @Test
    fun `32 + 입력하고 삭제를 호출하면 32 가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(3), Operand(2), Operation.Addition))
        // 삭제한다
        presenter.removeExpressionItem()

        `수식을 확인한다`("32", expressionSlot.captured)
    }

    @Test
    fun `32 입력하고 삭제를 호출하면 3 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(3), Operand(2)))
        // 삭제한다
        presenter.removeExpressionItem()

        `수식을 확인한다`("3", expressionSlot.captured)
    }

    @Test
    fun `3 + 2 입력하고 계산을 호출하면 5 가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf(Operand(3), Operation.Addition, Operand(2)))
        // 계산한다
        presenter.calculate()

        `수식을 확인한다`("5", expressionSlot.captured)
    }

    @Test
    fun `3 + 입력하고 계산을 호출하면 수식이 실패했는지 확인한다`() {
        `뷰호출을 정의한다`()
        every { view.displayExpressionError() } just Runs

        `수식을 입력한다`(listOf(Operand(3), Operation.Addition))
        presenter.calculate()

        verify { view.displayExpressionError() }
    }

    private fun `뷰호출을 정의한다`(): CapturingSlot<String> {
        val expressionSlot = slot<String>()
        every { view.display(capture(expressionSlot)) } just Runs
        return expressionSlot
    }

    private fun `수식을 입력한다`(expressions: List<ExpressionItem>) {
        for (expression in expressions) {
            when (expression) {
                is Operand -> {
                    presenter.addOperandExpression(expression)
                }

                is Operation -> {
                    presenter.addOperationExpression(expression)
                }
            }
        }
    }

    private fun `수식을 확인한다`(expected: String, actual: String) {
        assertThat(actual).isEqualTo(expected)
        verify { view.display(actual) }
    }
}
