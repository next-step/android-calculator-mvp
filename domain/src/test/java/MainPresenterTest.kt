import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.MainContract
import edu.nextstep.camp.calculator.domain.MainPresenter
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var view: MainContract.View
    private lateinit var presenter: MainPresenter

    @BeforeEach
    fun setup() {
        view = mockk(relaxUnitFun = true)
        presenter = MainPresenter(view)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    fun `display_operand_added_expression_when_add_operand_on_empty_expression`(operand: Int) {
        // when
        presenter.addToExpression(operand)

        // then
        val expected = Expression.EMPTY + operand
        verify { view.showExpression(expected) }
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `display_operator_added_expression_when_add_operator_on_empty_expression`(operator: Operator) {
        // when
        presenter.addToExpression(operator)

        // then
        val expected = Expression.EMPTY + operator
        verify { view.showExpression(expected) }
    }

    @Test
    fun `display_last_piece_deleted_expression_when_delete`() {
        // given "12 +"
        presenter.addToExpression(1)
        presenter.addToExpression(2)
        presenter.addToExpression(Operator.Plus)

        // when
        presenter.delete()

        // then
        val expected = Expression.EMPTY + 1 + 2
        verify { view.showExpression(expected) }
    }

    @Test
    fun `display_final_answer_when_calculate`() {
        // given "1 + 3 * 6"
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Multiply)
        presenter.addToExpression(6)

        // when
        presenter.calculate()

        // then
        val expected = Expression.newInstance(24)
        verify { view.showExpression(expected) }
    }

    @Test
    fun `show_fail_message_and_do_nothing_with_display_when_calculate_on_uncompleted_expression`() {
        // given "1 *"
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Multiply)
        verify(exactly = 2) { view.showExpression(any()) }

        // when
        presenter.calculate()

        // then
        verify { view.showCalculationFailMessage() }
        verify(exactly = 2) { view.showExpression(any()) }
    }

}