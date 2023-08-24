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

    override fun clickButton(input: String) {
        addToInput(input)
            .onSuccess {
                view.showResult(expression.showExpression())
            }
            .onFailure {
                view.showError(it.message ?: DEFAULT_ERROR_MSG)
            }
    }

    override fun addToInput(input: String): Result<Unit> {
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

            else -> Result.failure(IllegalArgumentException(DEFAULT_ERROR_MSG))
        }
    }

    override fun removeLast() {
        expression.removeLastInput()
        view.showResult(expression.showExpression())
    }

    override fun calculate() {
        runCatching {
            expression.isValidExpression()
            calculator.calculate(expression.showExpression())
        }.onSuccess { result ->
            view.showResult(result.toString())
            clearCurrentOperandList()
        }.onFailure { throwable ->
            view.showError(throwable.message ?: DEFAULT_ERROR_MSG)
        }
    }

    override fun clearCurrentOperandList() {
        expression.clearCurrentOperandList()
    }
}

const val DEFAULT_ERROR_MSG = "오류가 발생했습니다. 계산기를 초기화해주세요."
