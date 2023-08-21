/**
 * @author Daewon on 21,August,2023
 *
 */

package camp.nextstep.edu.calculator

import com.example.domain.Calculator
import com.example.domain.Expression
import com.example.domain.InputState


class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private val calculator = Calculator
    private val expression = Expression
    private fun getOperand() = expression.currentOperandList.joinToString(" ")

    override fun clickButton(input: String) {
        if (addToInput(input)) {
            view.showResult(getOperand())
        }
    }

    override fun addToInput(input: String): Boolean {
        return when (expression.lastInputState) {
            InputState.Init -> {
                expression.currentInitStateInput(input)
            }

            InputState.Operand -> {
                expression.currentOperandStateInput(input)
            }

            InputState.Operator -> {
                expression.currentOperatorStateInput(input)
            }

            else -> false
        }
    }

    override fun removeLast() {
        expression.removeLastInput()
        view.showResult(getOperand())
    }

    override fun calculate() {
        runCatching {
            expression.isValidExpression()
            calculator.calculate(getOperand())
        }.onSuccess { result ->
            view.showResult(result.toString())
            clearCurrentOperandList()
        }.onFailure { throwable ->
            view.showError(throwable.message ?: "에러")
        }
    }

    override fun clearCurrentOperandList() {
        expression.clearCurrentOperandList()
    }
}
